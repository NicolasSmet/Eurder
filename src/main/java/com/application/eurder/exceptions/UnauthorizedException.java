package com.application.eurder.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(){super("You are not authorized to perform that action.");}
}
