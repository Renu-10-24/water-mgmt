package com.example.watermgmt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterManagementResponse {
    private int baseWaterConsumed;
    private int baseFare;
}
