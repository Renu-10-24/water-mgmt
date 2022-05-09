package com.example.watermgmt.controller;

import com.example.watermgmt.model.AllotWater;
import com.example.watermgmt.service.WaterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    private ObjectMapper objMapper= new ObjectMapper();

    @Test
    public void testMethod() throws Exception {
        //accept user input and give 200 success status
        when()
        mockMvc.perform(post("/allot-water").contentType(MediaType.APPLICATION_JSON)
                        .content(objMapper.writeValueAsString(AllotWater.builder().apartmentType("2").corpWaterRatio(2).borewellRatio(3).build())))
                                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseWaterConsumedInLitres").value("900"))
                .andExpect(jsonPath("$.baseFare").value("900"));
    }
}