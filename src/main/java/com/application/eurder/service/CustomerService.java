package com.application.eurder.service;

import com.application.eurder.domain.User;
import com.application.eurder.dto.CreateUserDTO;
import com.application.eurder.dto.UserDTO;
import com.application.eurder.mapper.UserMapper;
import com.application.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final UserRepository repository;
    private final UserMapper mapper;
    public CustomerService(UserRepository userRepository, UserMapper mapper){
        this.repository = userRepository;
        this.mapper = mapper;
    }

    public UserDTO create(CreateUserDTO newUser) {
        User userToSave = mapper.toDomain(newUser);
        return mapper.toDTO(repository.create(userToSave));
    }
}
