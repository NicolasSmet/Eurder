package com.application.eurder.exceptions;

public class FieldFormatNotValidException extends IllegalArgumentException {
    public FieldFormatNotValidException(String message) {
        super(String.format("%s was not formatted properly.", message));
    }
}
