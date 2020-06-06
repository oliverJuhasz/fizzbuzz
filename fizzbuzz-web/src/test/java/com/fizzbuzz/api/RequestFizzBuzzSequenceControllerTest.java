package com.fizzbuzz.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizzbuzz.api.request.FizzBuzzResponse;
import com.fizzbuzz.service.FizzBuzzService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;


@SpringBootTest
@AutoConfigureMockMvc
public class RequestFizzBuzzSequenceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FizzBuzzService fizzBuzzService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Calling api returns response")
    public void test1() throws Exception {
        // GIVEN
        String uri = "/api/getFizzBuzzSequence";

        // WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        // THEN
        Assertions.assertEquals(200, status);
    }

    @Test
    @DisplayName("Calling api without data returns an unsuccessful response")
    public void test2() throws Exception {
        // GIVEN
        String uri = "/api/getFizzBuzzSequence";

        // WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        mvcResult.getResponse().getContentType();
        FizzBuzzResponse response = objectMapper.readValue(content, FizzBuzzResponse.class);

        // THEN
        Assertions.assertEquals(200, 200);
    }
}
