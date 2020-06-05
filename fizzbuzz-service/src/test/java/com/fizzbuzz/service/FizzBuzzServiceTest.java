package com.fizzbuzz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

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
        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("convertNumberToFizzBuzz shouldn't accept negative numbers")
    public void test2() {
        // GIVEN
        long input = -1L;

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.convertNumberToFizzBuzz(input));
    }
}
