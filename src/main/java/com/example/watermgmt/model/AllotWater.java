package com.example.watermgmt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllotWater {
    private String apartmentType;
    private int corpWaterRatio;
    private int borewellRatio;
}

