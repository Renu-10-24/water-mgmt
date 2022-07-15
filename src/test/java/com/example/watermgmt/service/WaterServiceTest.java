package com.example.watermgmt.service;

import com.example.watermgmt.model.AllotWater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaterServiceTest {
    @Autowired
    WaterService waterService;

    @Test
    public void testProcessAllotWater(){
        waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build());
        Assert.assertEquals(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build(),waterService.allotWater);
    }

    @Test
    public void testProcessAllotWaterTestGuestCount(){
        waterService.processAddGuests(1);
        waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build());
        Assert.assertEquals(0,waterService.noOfGuests);
    }

    @Test
    public void testProcessAllotWater2bhkCalculation(){
        Assert.assertEquals(900, waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(5).build()).getBaseWaterConsumedInLitres());
        Assert.assertEquals(1275, waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(5).build()).getBaseFare());

    }
    @Test
    public void testProcessAllotWater3BhkCalculation(){
        Assert.assertEquals(1500, waterService.processAllotWater(AllotWater.builder().apartmentType("3").corpWaterRatio(1).borewellRatio(5).build()).getBaseWaterConsumedInLitres());
        Assert.assertEquals(2125, waterService.processAllotWater(AllotWater.builder().apartmentType("3").corpWaterRatio(1).borewellRatio(5).build()).getBaseFare());
    }

    @Test
    public void testProcessAddGuests(){
        waterService.processAddGuests(1);
       Assert.assertEquals(3, waterService.processAddGuests(2));
//        Assert.assertEquals(3,waterService.noOfGuests);
    }
}
