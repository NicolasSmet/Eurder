package com.application.eurder.controller;

import com.application.eurder.exceptions.FieldNullOrEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler{
    @ExceptionHandler(FieldNullOrEmptyException.class)
    public void fieldNotValidException(FieldNullOrEmptyException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
    }
}
