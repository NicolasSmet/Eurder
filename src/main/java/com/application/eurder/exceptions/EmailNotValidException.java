package com.application.eurder.exceptions;

public class EmailNotValidException extends IllegalArgumentException {
    public EmailNotValidException() {
        super("Given email is not valid.");
    }
}
