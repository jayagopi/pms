package com.kyro.pms.controller;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private HttpStatus httpStatus;

    private LocalDateTime timeStamp;

    private String message;

    private String details;

    public ErrorModel(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
