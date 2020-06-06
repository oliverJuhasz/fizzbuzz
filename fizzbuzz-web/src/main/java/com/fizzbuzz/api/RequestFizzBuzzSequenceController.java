package com.fizzbuzz.api;

import com.fizzbuzz.api.request.FizzBuzzRequest;
import com.fizzbuzz.api.request.FizzBuzzResponse;
import com.fizzbuzz.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@Validated
public class RequestFizzBuzzSequenceController {


    private FizzBuzzService fizzBuzzService;

    @Autowired
    public RequestFizzBuzzSequenceController(FizzBuzzService fizzBuzzService) {
        this.fizzBuzzService = fizzBuzzService;
    }

    /** Get mapping for fizzbuzz sequence. Requires a JSON object with "highestNumber" key.
     *
     * @param request Target of data binding
     * @param bindingResult Result of data binding
     * @return FizzBuzzResponse
     */
    @GetMapping("/getFizzBuzzSequence")
    public FizzBuzzResponse getFizzBuzzSequence(@RequestBody @Valid FizzBuzzRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createFailedFizzBuzzResponse();
        }
        return createSuccessfulFizzBuzzResponse(request);
    }

    /** Get mapping for fizzbuzz sequence. Takes a path variable.
     *
     * @param highestNumber The last element of the fizzbuzz sequence
     * @return FizzBuzzResponse
     */
    @GetMapping("/getFizzBuzzSequence/{highestNumber}")
    public FizzBuzzResponse getFizzBuzzSequenceFromPath(@PathVariable("highestNumber") @Min(1) long highestNumber) {
        FizzBuzzRequest fizzBuzzRequest = new FizzBuzzRequest();
        fizzBuzzRequest.setHighestNumber(highestNumber);
        return createSuccessfulFizzBuzzResponse(fizzBuzzRequest);
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

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class, javax.validation.ConstraintViolationException.class})
    private FizzBuzzResponse handleException() {
        return createFailedFizzBuzzResponse();
    }

}
