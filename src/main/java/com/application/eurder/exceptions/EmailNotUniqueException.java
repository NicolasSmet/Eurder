package com.application.eurder.exceptions;

public class EmailNotUniqueException extends IllegalArgumentException{
    public EmailNotUniqueException(){super("The provided email address is not unique. Please provide a unique email address.");}
}
