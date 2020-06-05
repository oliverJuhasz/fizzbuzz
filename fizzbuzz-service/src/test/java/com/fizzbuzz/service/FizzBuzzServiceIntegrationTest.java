package com.fizzbuzz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzServiceIntegrationTest {


    @InjectMocks
    private FizzBuzzService underTest;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("generateFizzBuzzUpToNumber should return correct output for input 15")
    public void test1() {
        // GIVEN
        long input = 15L;
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("fizz");
        expected.add("4");
        expected.add("buzz");
        expected.add("fizz");
        expected.add("7");
        expected.add("8");
        expected.add("fizz");
        expected.add("buzz");
        expected.add("11");
        expected.add("fizz");
        expected.add("13");
        expected.add("14");
        expected.add("fizzbuzz");

        // WHEN
        List<String> result = underTest.generateFizzBuzzUpToNumber(input);

        // THEN
        assertEquals(expected, result);
    }
}
