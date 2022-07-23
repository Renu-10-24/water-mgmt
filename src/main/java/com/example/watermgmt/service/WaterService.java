package com.example.watermgmt.service;

import com.example.watermgmt.enums.ApartmentType;
import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.model.AllotWaterResponse;
import com.example.watermgmt.model.WaterManagementResponse;
import org.springframework.stereotype.Service;

@Service
public class WaterService {
public AllotWater allotWater;
public int noOfGuests=0;
    public AllotWaterResponse processAllotWater(AllotWater allotWater){
        int bhk=ApartmentType.aprtTypesMap.get(Integer.parseInt(allotWater.getApartmentType())).getPersonCount();
        int baseWaterConsumption = bhk*10*30;
        int x= baseWaterConsumption/(allotWater.getCorpWaterRatio()+allotWater.getBorewellRatio());
        int baseFare = (int)((x*allotWater.getCorpWaterRatio()*1)+(x*allotWater.getBorewellRatio()*1.5));
        this.noOfGuests = 0;
        return AllotWaterResponse.builder().baseWaterConsumedInLitres(baseWaterConsumption).baseFare(baseFare).build();
    }

    public int processAddGuests(int noOfGuests){
//        int currGuestNum =noOfGuests+this.noOfGuests;
//        this.noOfGuests = currGuestNum;
        this.noOfGuests += noOfGuests;
        return this.noOfGuests;
    }
//•. 0 to 500L - Rs. 2 per litre
//•. 501L to 1500L - Rs. 3 per litre
//•. 1501 to 3000L - Rs. 5 per litre
//•. 3001L+ - Rs. 8 per litre
    public int processBill(){
        int waterConsumedByGuests = this.noOfGuests*10*30;
        int guestBill = 0;
        int t = 0;

//            case (t<=500) :  return guestBill = 500*2;
        return 0;
        }
    }
