package com.fizzbuzz.api.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class FizzBuzzResponse {


    private LocalDateTime timestamp;
    private boolean success;
    private String message;
    private List<String> data;
}
