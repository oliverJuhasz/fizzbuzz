package com.fizzbuzz.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class FizzBuzzRequest {

    @Positive
    @NotNull
    private long highestNumber;

}
