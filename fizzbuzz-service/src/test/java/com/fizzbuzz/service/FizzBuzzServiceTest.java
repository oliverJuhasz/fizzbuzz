package com.fizzbuzz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("convertNumberToFizzBuzz should return the number if it is not devisable by three or five")
    @ValueSource(longs = {1, 2, 4, 7, 8, 11, 13})
    public void test4(long input) {
        // GIVEN as parameter

        // WHEN
        String result = underTest.convertNumberToFizzBuzz(input);

        // THEN
        assertEquals(String.valueOf(input), result);
    }
}
