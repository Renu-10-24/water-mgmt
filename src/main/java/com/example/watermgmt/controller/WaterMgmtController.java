package com.example.watermgmt.controller;

import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.model.AllotWaterResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
public class WaterMgmtController {
    @PostMapping("/allot-water")

    public ResponseEntity<AllotWaterResponse> allotWater(@RequestBody @Valid AllotWater alloted){
        log.debug("in the controller ");
        return new ResponseEntity<>(AllotWaterResponse.builder().build(), HttpStatus.OK);
    }

}
