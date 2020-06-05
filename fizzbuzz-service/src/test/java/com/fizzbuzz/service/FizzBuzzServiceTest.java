package com.fizzbuzz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FizzBuzzServiceTest {


    @InjectMocks
    private FizzBuzzService underTest;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("convertNumberToFizzBuzz shouldn't return null")
    public void test1() {
        // GIVEN
        long input = 10L;

        // WHEN
        String result = underTest.convertNumberToFizzBuzz(input);

        // THEN
        assertNotNull(result);
    }

    @Test
    @DisplayName("convertNumberToFizzBuzz shouldn't accept negative numbers")
    public void test2() {
        // GIVEN
        long input = -1L;

        // THEN
        assertThrows(IllegalArgumentException.class, () -> underTest.convertNumberToFizzBuzz(input));
    }

    @Test
    @DisplayName("convertNumberToFizzBuzz should throw illegal argument exception if parameter is zero")
    public void test3() {
        // GIVEN
        long input = 0L;

        // THEN
        assertThrows(IllegalArgumentException.class, () -> underTest.convertNumberToFizzBuzz(input));
    }

    @ParameterizedTest
    @DisplayName("convertNumberToFizzBuzz should return the number if it is not divisible by three or five")
    @ValueSource(longs = {1, 2, 4, 7, 8, 11, 13})
    public void test4(long input) {
        // GIVEN as parameter

        // WHEN
        String result = underTest.convertNumberToFizzBuzz(input);

        // THEN
        assertEquals(String.valueOf(input), result);
    }

    @ParameterizedTest
    @DisplayName("convertNumberToFizzBuzz should return fizz if number is divisible by 3")
    @ValueSource(longs = {3, 6, 9, 12, 18, 21, 24})
    public void test5(long input) {
        // GIVEN as parameter

        // WHEN
        String result = underTest.convertNumberToFizzBuzz(input);

        // THEN
        assertEquals("fizz", result);
    }

    @ParameterizedTest
    @DisplayName("convertNumberToFizzBuzz should return buzz if number is divisible by 5")
    @ValueSource(longs = {5, 10, 20, 25, 35, 40, 50})
    public void test6(long input) {
        // GIVEN as parameter

        // WHEN
        String result = underTest.convertNumberToFizzBuzz(input);

        // THEN
        assertEquals("buzz", result);
    }

    @ParameterizedTest
    @DisplayName("convertNumberToFizzBuzz should return fizzbuzz if number is divisible by both 3 and 5")
    @ValueSource(longs = {15, 30, 45, 60, 75, 90, 105})
    public void test7(long input) {
        // GIVEN as parameter

        // WHEN
        String result = underTest.convertNumberToFizzBuzz(input);

        // THEN
        assertEquals("fizzbuzz", result);
    }

    @Test
    @DisplayName("generateFizzBuzzUpToNumber shouldn't return null")
    public void test8() {
        // GIVEN
        long input = 10L;

        // WHEN
        List<String> result = underTest.generateFizzBuzzUpToNumber(input);

        // THEN
        assertNotNull(result);
    }

    @Test
    @DisplayName("generateFizzBuzzUpToNumber shouldn't accept negative numbers")
    public void test9() {
        // GIVEN
        long input = -1L;

        // THEN
        assertThrows(IllegalArgumentException.class, () -> underTest.generateFizzBuzzUpToNumber(input));
    }

    @Test
    @DisplayName("generateFizzBuzzUpToNumber should return empty array if input is 0")
    public void test10() {
        // GIVEN
        long input = 0L;

        // WHEN
        List<String> result = underTest.generateFizzBuzzUpToNumber(input);

        // THEN
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("generateFizzBuzzUpToNumber should call generateFizzBuzzUpToNumber")
    public void test11() {
        // GIVEN
        FizzBuzzService fizzBuzzService = spy(FizzBuzzService.class);
        long input = 10;

        // WHEN
        fizzBuzzService.generateFizzBuzzUpToNumber(input);

        // THEN
        verify(fizzBuzzService).convertNumberToFizzBuzz(anyLong());
    }

    @Test
    @DisplayName("generateFizzBuzzUpToNumber should return the correct size of list based on the input")
    @ValueSource(longs = {5, 20, 1000})
    public void test12(int number) {
        // GIVEN
        long input = 20L;

        // WHEN
        List<String> result = underTest.generateFizzBuzzUpToNumber(input);

        // THEN
        assertEquals(number, result.size());
    }
}
