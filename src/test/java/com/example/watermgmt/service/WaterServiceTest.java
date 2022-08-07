package com.example.watermgmt.service;

import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.repository.AllotWaterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaterServiceTest {
    @InjectMocks
    WaterService waterService;
    @Mock
    AllotWaterRepository allotWaterRepository;
    @Captor
    ArgumentCaptor<com.example.watermgmt.entity.AllotWater> allotWaterCaptor;
    @Test
    public void testProcessAllotWater(){
        waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build());
//        Assert.assertEquals(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build(),waterService.);
    }

    @Test
    public void testProcessAllotWaterTestGuestCount(){
//        waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build());
        com.example.watermgmt.entity.AllotWater allotWaterMock = new com.example.watermgmt.entity.AllotWater();
        allotWaterMock.setGuestCount(1);
        List<com.example.watermgmt.entity.AllotWater> list = new ArrayList<>();
        list.add(allotWaterMock);
        when(allotWaterRepository.findAll()).thenReturn(list);
        waterService.processAddGuests(1);
        Assert.assertEquals(2,waterService.processAddGuests(1));
        //when the process methods don't return any value, then we can use captor to verify the state of the entity object.
        Mockito.verify(allotWaterRepository).save(allotWaterCaptor.capture());
        Assert.assertEquals(2,allotWaterCaptor.getValue().getGuestCount());
    }

    @Test
    public void testProcessAllotWaterTestGuestCount_WhenAllotWaterNotDone(){
//        waterService.processAllotWater(AllotWater.builder().apartmentType("2").corpWaterRatio(1).borewellRatio(2).build());
        com.example.watermgmt.entity.AllotWater allotWaterMock = new com.example.watermgmt.entity.AllotWater();
        allotWaterMock.setGuestCount(1);
        List<com.example.watermgmt.entity.AllotWater> list = new ArrayList<>();
        list.add(allotWaterMock);
//        when(allotWaterRepository.findAll()).thenReturn(list);
//        Exception exception = Assert.assertThrows(RuntimeException.class, () -> {
//            Integer.parseInt("1a");
//        });
        Exception exc1 =  Assert.assertThrows(RuntimeException.class, this::run);
        Assert.assertEquals("Allot Water Not Done",exc1.getMessage());
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

    private void run() {
        waterService.processAddGuests(1);
    }
}
