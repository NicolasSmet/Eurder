package com.application.eurder.exceptions;

public class FieldZeroOrLessThanZeroException extends IllegalArgumentException{
    public FieldZeroOrLessThanZeroException(String message) {super(String.format("%s can't be zero or less than zero.", message));
    }
}
