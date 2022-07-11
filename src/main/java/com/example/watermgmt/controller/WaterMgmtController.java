package com.example.watermgmt.controller;

import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.model.AllotWaterResponse;
import com.example.watermgmt.service.WaterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
public class WaterMgmtController {
    @Autowired
    WaterService waterService;
    @PostMapping("/allot-water")
    public ResponseEntity<AllotWaterResponse> allotWater(@RequestBody @Valid AllotWater alloted){
        log.debug("in the controller ");
        return new ResponseEntity<>(waterService.processAllotWater(alloted), HttpStatus.OK);
    }

}
