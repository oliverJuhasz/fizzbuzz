package com.fizzbuzz.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FizzBuzzService {


    public List<String> generateFizzBuzzUpToNumber(long number) {
        return null;
    }

    public String convertNumberToFizzBuzz(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Input shouldn't be less than zero");
        }
        return "not null";
    }

}
