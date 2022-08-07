package com.example.watermgmt.repository;

import com.example.watermgmt.entity.AllotWater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllotWaterRepositoryTest {
    @Autowired
    AllotWaterRepository allotWaterRepository;

    @Test
    public void testRepoConnection() {
        Assert.assertNotNull(allotWaterRepository);
    }

    @Test
    public void testSave() {
        AllotWater allotWater = new AllotWater();
        allotWater.setAptType(2);
        allotWater.setCorpWaterRatio(1);
        allotWater.setBorewellRatio(2);
        AllotWater allotWater1 = allotWaterRepository.save(allotWater);
        System.out.print(allotWater1);
        Assert.assertNotNull(allotWater1);
        Assert.assertFalse(allotWaterRepository.findById(allotWater1.getId()).isEmpty());
    }

    @Test
    public void testUpdate() {
        AllotWater allotWater = new AllotWater();
        allotWater.setAptType(3);
        allotWater.setCorpWaterRatio(2);
        allotWater.setBorewellRatio(1);
        AllotWater allotWater1 = allotWaterRepository.save(allotWater);

        Optional<AllotWater> optionalAllotWater = allotWaterRepository.findById(allotWater1.getId());
        optionalAllotWater.map(allotWater2 -> {
            allotWater2.setGuestCount(2);
            return allotWater2;
        });
        AllotWater allotWater3 = allotWaterRepository.save(optionalAllotWater.get());
        Assert.assertEquals(2, allotWater3.getGuestCount());

        allotWaterRepository.deleteById(allotWater3.getId());
        Assert.assertTrue(allotWaterRepository.findById(allotWater3.getId()).isEmpty());
    }
}