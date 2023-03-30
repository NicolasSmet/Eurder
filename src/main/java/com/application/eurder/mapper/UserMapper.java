package com.application.eurder.mapper;

import com.application.eurder.domain.User;
import com.application.eurder.dto.CreateUserDTO;
import com.application.eurder.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toDomain(CreateUserDTO createUserDTO){
        return new User(createUserDTO.password(), createUserDTO.name(), createUserDTO.contactDetails(), createUserDTO.role());
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(),user.getContactdetails(),user.getName());
    }
}
