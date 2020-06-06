package com.fizzbuzz.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FizzBuzzService {

    /** Takes a number and generates fizzbuzz sequence up to that number
     *
     * @param number the last number in the sequence
     * @return List of fizzbuzz results
     */
    public List<String> generateFizzBuzzUpToNumber(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Input shouldn't be less than zero");
        }
        List<String> fizzBuzzes = new ArrayList<>();
        for (long i = 1L; i <= number; i++) {
            fizzBuzzes.add(convertNumberToFizzBuzz(i));
        }
        return fizzBuzzes;
    }

    /** Takes a number and converts it to the fizzbuzz equivalent
     *
     * @param number to be converted
     * @return fizz if number is divisable by 3, buzz if number is dividable by 5, and fizzbuzz if dividable by both.
     * Returns the number if none of the above conditions apply.
     */
    public String convertNumberToFizzBuzz(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Input shouldn't be less than or equal to zero");
        }
        String result = "";
        if (number % 3 == 0) {
            result += "fizz";
        }
        if (number % 5 == 0) {
            result += "buzz";
        }
        if (result.length() == 0) {
            result = String.valueOf(number);
        }
        return result;
    }
}
