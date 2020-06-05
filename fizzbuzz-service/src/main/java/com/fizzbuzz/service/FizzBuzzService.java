package com.fizzbuzz.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FizzBuzzService {


    public List<String> generateFizzBuzzUpToNumber(long number) {
        return null;
    }

    public String convertNumberToFizzBuzz(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Input shouldn't be less than or equal to zero");
        }
        if (number % 3 == 0) {
            return "fizz";
        }
        else if (number % 5 == 0) {
            return "buzz";
        } else {
            return String.valueOf(number);
        }
    }

}
