package com.application.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldNotValidException extends IllegalArgumentException {
    public FieldNotValidException(String message) {
        super(String.format("Field %s can't be empty.", message));
    }
}
