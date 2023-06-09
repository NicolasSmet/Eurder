package com.application.eurder.dto;

import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.security.Role;

public record CreateUserDTO(String password, ContactDetails contactDetails, Name name, Role role) {
}
