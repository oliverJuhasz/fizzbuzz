package com.fizzbuzz.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FizzBuzzService {


    public List<String> generateFizzBuzzUpToNumber(long number) {
        List<String> fizzBuzzes = new ArrayList<>();
        return fizzBuzzes;
    }

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
