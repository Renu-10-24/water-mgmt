package com.example.watermgmt.repository;

import com.example.watermgmt.entity.AllotWater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "allotWaterRepository")
public interface AllotWaterRepository extends JpaRepository<AllotWater,Integer> {

}
