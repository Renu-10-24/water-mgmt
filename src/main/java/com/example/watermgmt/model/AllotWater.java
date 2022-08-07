package com.example.watermgmt.model;

import com.example.watermgmt.enums.ApartmentType;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@Builder
public class AllotWater {
//    private int apartmentNumber;
    @NotNull
    @Pattern(regexp = "2|3", message = "Not valid apartment type")
    private String apartmentType;
    @NotNull
    @Range(min=1,max=5,message = "Invalid Corp Ratio")
    private int corpWaterRatio;
    @NotNull
    @Range(min=1,max=5,message = "Invalid Bore Ratio")
    private int borewellRatio;
}

