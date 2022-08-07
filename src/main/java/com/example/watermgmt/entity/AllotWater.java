package com.example.watermgmt.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AllotWater")
@Data
public class AllotWater{
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "APARTMENT_TYPE")
    private int aptType;
    @Column(name = "CORP_RATIO")
    private int corpWaterRatio;
    @Column(name = "BOREWELL_RATIO")
    private int borewellRatio;
    @Column(name = "GUEST_COUNT")
    private int guestCount;
    @Column(name = "BASE_FARE")
    private int baseFare;
    @Column(name="BASE_WATER_CONSUMPTION")
    private int baseWaterConsumption;
    @Column(name="TOTAL_WATER_CONSUMPTION")
    private int totalWaterConsumption;
    @Column(name = "TOTAL_BILL")
    private int totalBill;
    public AllotWater() {
        super();
    }
}
