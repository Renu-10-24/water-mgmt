package com.example.watermgmt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllotWaterResponse {
    private int baseWaterConsumedInLitres;
    private int baseFare;
    private int totalBill;
}
