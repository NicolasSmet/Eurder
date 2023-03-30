package com.application.eurder.exceptions;

public class FieldNullOrEmptyException extends IllegalArgumentException {
    public FieldNullOrEmptyException(String message) {
        super(String.format("Field %s can't be empty.", message));
    }
}
