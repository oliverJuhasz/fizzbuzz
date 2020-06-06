package com.fizzbuzz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizzbuzz.api.request.FizzBuzzRequest;
import com.fizzbuzz.api.request.FizzBuzzResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestFizzBuzzSequenceControllerTest {


    public static final String API_GET_FIZZ_BUZZ_SEQUENCE = "/api/getFizzBuzzSequence";
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public RequestFizzBuzzSequenceControllerTest(ObjectMapper objectMapper, MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Calling api returns response")
    public void test1() throws Exception {
        // GIVEN

        // WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(API_GET_FIZZ_BUZZ_SEQUENCE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        // THEN
        Assertions.assertEquals(200, status);
    }

    @Test
    @DisplayName("Calling api without data returns an unsuccessful response")
    public void test2() throws Exception {
        // GIVEN

        // WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(API_GET_FIZZ_BUZZ_SEQUENCE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        FizzBuzzResponse response = objectMapper.readValue(content, FizzBuzzResponse.class);

        // THEN
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    @DisplayName("Calling api with valid request returns a successful response")
    public void test3() throws Exception {
        // GIVEN
        FizzBuzzRequest fizzBuzzRequest = new FizzBuzzRequest();
        fizzBuzzRequest.setHighestNumber(5L);
        String fizzBuzzRequestJSON = objectMapper.writeValueAsString(fizzBuzzRequest);

        // WHEN
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(API_GET_FIZZ_BUZZ_SEQUENCE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(fizzBuzzRequestJSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        FizzBuzzResponse response = objectMapper.readValue(content, FizzBuzzResponse.class);

        // THEN
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    @DisplayName("Calling api with invalid request returns an unsuccessful response")
    public void test4() throws Exception {
        // GIVEN
        FizzBuzzRequest fizzBuzzRequest = new FizzBuzzRequest();
        fizzBuzzRequest.setHighestNumber(-5L);
        String fizzBuzzRequestJSON = objectMapper.writeValueAsString(fizzBuzzRequest);

        // WHEN
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(API_GET_FIZZ_BUZZ_SEQUENCE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(fizzBuzzRequestJSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        FizzBuzzResponse response = objectMapper.readValue(content, FizzBuzzResponse.class);

        // THEN
        Assertions.assertFalse(response.isSuccess());
    }
}
