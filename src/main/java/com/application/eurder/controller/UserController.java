package com.application.eurder.controller;

import com.application.eurder.dto.CreateUserDTO;
import com.application.eurder.dto.UserDTO;
import com.application.eurder.exceptions.EmailNotValidException;
import com.application.eurder.exceptions.FieldNotValidException;
import com.application.eurder.exceptions.RoleNotValidException;
import com.application.eurder.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class UserController {
    public UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody CreateUserDTO newUser){
        return userService.create(newUser);
    }
    @ExceptionHandler(FieldNotValidException.class)
    public void fieldNotValidException(FieldNotValidException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
    }
    @ExceptionHandler(EmailNotValidException.class)
    public void emailNotValidException(EmailNotValidException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
    }
    @ExceptionHandler(RoleNotValidException.class)
    public void roleNotValidException(RoleNotValidException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
    }
}
