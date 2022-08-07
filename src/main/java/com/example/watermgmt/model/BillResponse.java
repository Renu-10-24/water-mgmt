package com.example.watermgmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillResponse {
    private int totalWaterConsumed;
    private int totalBill;
}
