package com.example.watermgmt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllotWaterResponse {
    private String baseWaterConsumedInLitres;
    private String baseFare;
}
