package com.application.eurder.exceptions;

public class WrongPasswordException extends IllegalArgumentException {
    public WrongPasswordException(){super("Password incorrect.");}
}
