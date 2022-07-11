package com.example.watermgmt.controller;

import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.model.AllotWaterResponse;
import com.example.watermgmt.service.WaterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class WaterMgmtControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WaterService waterService;

    private final ObjectMapper objMapper= new ObjectMapper();

    @Test
    public void testMethod() throws Exception {
        //accept user input and give 200 success status
        Mockito.when(waterService.processAllotWater(Mockito.any())).thenReturn(AllotWaterResponse.builder().baseWaterConsumedInLitres(900).baseFare(900).build());
        mockMvc.perform(post("/allot-water").contentType(MediaType.APPLICATION_JSON)
                        .content(objMapper.writeValueAsString(AllotWater.builder().apartmentType("2").corpWaterRatio(2).borewellRatio(3).build())))
                                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseWaterConsumedInLitres").value("900"))
                .andExpect(jsonPath("$.baseFare").value("900"));
    }

    @Test
    public void testMethod_invalidApartmentType() throws Exception {
        //accept user input and give 400 failure status
        mockMvc.perform(post("/allot-water").contentType(MediaType.APPLICATION_JSON)
                        .content(objMapper.writeValueAsString(AllotWater.builder().apartmentType("10").corpWaterRatio(2).borewellRatio(3).build())))
                .andExpect(status().isBadRequest());
//                .andExpect(jsonPath("$.message").value("Not valid apartment type"));
    }
}