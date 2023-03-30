package com.application.eurder.exceptions;

public class RoleNotValidException extends ExceptionInInitializerError{
    public RoleNotValidException(){super("You did not specify a role: a role can either be CUSTOMER or ADMIN.");}
}
