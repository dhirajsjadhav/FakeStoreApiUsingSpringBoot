package com.example.practice.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.practice.dtos.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Null Pointer Exception");
        errorDto.setStatus("Failure");
        return errorDto;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus("Failure");
        return errorDto;
    }
}
