package com.application.eurder.dto;

import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;

import java.util.UUID;

public record UserDTO(UUID id, ContactDetails contactDetails, Name name) {
}
