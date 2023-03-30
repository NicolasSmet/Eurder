package com.application.eurder.exceptions;

public class FieldNotValidException extends IllegalArgumentException {
    public FieldNotValidException(String message) {
        super(String.format("Field %s can't be empty.", message));
    }
}
