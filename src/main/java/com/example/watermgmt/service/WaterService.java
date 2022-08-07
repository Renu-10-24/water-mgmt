package com.example.watermgmt.service;

import com.example.watermgmt.enums.ApartmentType;
import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.model.AllotWaterResponse;
import com.example.watermgmt.model.BillResponse;
import com.example.watermgmt.model.WaterManagementResponse;

import com.example.watermgmt.repository.AllotWaterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterService {
    @Autowired
    AllotWaterRepository allotWaterRepository;

    public AllotWaterResponse processAllotWater(AllotWater allotWater) {
        int bhk, baseWaterConsumption, baseFare, x;
        com.example.watermgmt.entity.AllotWater allotWaterEntity = new com.example.watermgmt.entity.AllotWater();
        BeanUtils.copyProperties(allotWater, allotWaterEntity);
        List<com.example.watermgmt.entity.AllotWater> allotWaterList = allotWaterRepository.findAll();
        if (allotWaterList.isEmpty()) {
            bhk = ApartmentType.aprtTypesMap.get(Integer.parseInt(allotWater.getApartmentType())).getPersonCount();
            baseWaterConsumption = bhk * 10 * 30;
            x = baseWaterConsumption / (allotWater.getCorpWaterRatio() + allotWater.getBorewellRatio());
            baseFare = (int) ((x * allotWater.getCorpWaterRatio() * 1) + (x * allotWater.getBorewellRatio() * 1.5));
            allotWaterEntity.setBaseFare(baseFare);
            allotWaterEntity.setBaseWaterConsumption(baseWaterConsumption);
            allotWaterRepository.save(allotWaterEntity);
        } else {
            throw new RuntimeException("AllotWater done, please try adding guests and / or generate bill");
        }
        return AllotWaterResponse.builder().baseWaterConsumedInLitres(baseWaterConsumption).baseFare(baseFare).build();
    }

    public int processAddGuests(int noOfGuests) {
        List<com.example.watermgmt.entity.AllotWater> allotWaterList = allotWaterRepository.findAll();
        if (!allotWaterList.isEmpty()) {
            com.example.watermgmt.entity.AllotWater allotWater1 = allotWaterList.get(0);
            allotWater1.setGuestCount(allotWater1.getGuestCount() + noOfGuests);
            allotWaterRepository.save(allotWater1);
            return allotWater1.getGuestCount();
        } else {
            throw new RuntimeException("Allot Water Not Done");
        }
    }

    //•. 0 to 500L - Rs. 2 per litre
//•. 501L to 1500L - Rs. 3 per litre
//•. 1501 to 3000L - Rs. 5 per litre
//•. 3001L+ - Rs. 8 per litre
    public BillResponse processBill() {
        List<com.example.watermgmt.entity.AllotWater> allotWaterList = allotWaterRepository.findAll();
        com.example.watermgmt.entity.AllotWater allotWater1 = allotWaterList.get(0);
        int guestNumber = allotWater1.getGuestCount();
        int baseFare = allotWater1.getBaseFare();
        int waterConsumedByGuests = guestNumber * 10 * 30;
        int guestBill = 0;

        if (waterConsumedByGuests > 3000) {
            guestBill += (waterConsumedByGuests - 3000) * 8;
            waterConsumedByGuests = 3000;
        }
        if (waterConsumedByGuests > 1500) {
            guestBill += (waterConsumedByGuests - 1500) * 5;
            waterConsumedByGuests = 1500;
        }
        if (waterConsumedByGuests > 500) {
            guestBill += (waterConsumedByGuests - 500) * 3;
            waterConsumedByGuests = 500;
        }
        guestBill += (waterConsumedByGuests) * 2;
        int totalBill = baseFare + guestBill;
        allotWater1.setTotalWaterConsumption(waterConsumedByGuests + allotWater1.getBaseWaterConsumption());
        allotWater1.setTotalBill(totalBill);
        allotWaterRepository.save(allotWater1);
        return new BillResponse((waterConsumedByGuests + allotWater1.getBaseWaterConsumption()), totalBill);
    }
}
