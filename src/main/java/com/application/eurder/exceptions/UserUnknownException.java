package com.application.eurder.exceptions;

public class UserUnknownException extends RuntimeException {
    public UserUnknownException(){super("The provided user is not known, please provide a known user.");}
}
