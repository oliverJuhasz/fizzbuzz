package com.fizzbuzz.api;

import com.fizzbuzz.api.request.FizzBuzzRequest;
import com.fizzbuzz.api.request.FizzBuzzResponse;
import com.fizzbuzz.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class RequestFizzBuzzSequenceController {


    private FizzBuzzService fizzBuzzService;

    @Autowired
    public RequestFizzBuzzSequenceController(FizzBuzzService fizzBuzzService) {
        this.fizzBuzzService = fizzBuzzService;
    }

    @GetMapping("/getFizzBuzzSequence")
    public FizzBuzzResponse getFizzBuzzSequence(@RequestBody @Valid FizzBuzzRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createFailedFizzBuzzResponse();
        }
        return createSuccessfulFizzBuzzResponse(request);
    }

    private FizzBuzzResponse createSuccessfulFizzBuzzResponse(FizzBuzzRequest request) {
        return FizzBuzzResponse.builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(fizzBuzzService.generateFizzBuzzUpToNumber(request.getHighestNumber()))
                .message("Sequence generated successfully")
                .build();
    }

    private FizzBuzzResponse createFailedFizzBuzzResponse() {
        return FizzBuzzResponse.builder()
                .data(new ArrayList<>())
                .timestamp(LocalDateTime.now())
                .message("Invalid input received - please make sure to request a number from 1 to 9,223,372,036,854,775,807")
                .success(false)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private FizzBuzzResponse handleException() {
        return createFailedFizzBuzzResponse();
    }

}
