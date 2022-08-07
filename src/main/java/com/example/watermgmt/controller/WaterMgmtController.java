package com.example.watermgmt.controller;

import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.model.AllotWaterResponse;
import com.example.watermgmt.model.BillResponse;
import com.example.watermgmt.service.WaterService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.websocket.server.PathParam;

@RestController
@Log4j2
@Validated
public class WaterMgmtController {
    @Autowired
    WaterService waterService;
    @PostMapping("/allot-water")
    public ResponseEntity<AllotWaterResponse> allotWater(@RequestBody @Valid AllotWater alloted){
        log.debug("in the controller ");
        return new ResponseEntity<>(waterService.processAllotWater(alloted), HttpStatus.OK);
    }

//    @PostMapping(value = {"/guests/{noOfGuests}","/guests"})
@PostMapping(value = {"/guests/{noOfGuests}"})
//    by default path variable is required. (required = true)
    public ResponseEntity<Integer> guests(@PathVariable("noOfGuests") @Valid @Range(min=1, message = "Please enter value between 1 and 10") int noOfGuests){
        log.debug("in the controller ");
        return new ResponseEntity<>(waterService.processAddGuests(noOfGuests), HttpStatus.OK);
    }

    @GetMapping(value = {"/bill"})
//    by default path variable is required. (required = true)
    public ResponseEntity<BillResponse> bill(){
        log.debug("in the controller ");
        return new ResponseEntity<>(waterService.processBill(), HttpStatus.OK);
    }

}
