package com.example.practice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String status;
    private String message;
}
