package com.application.eurder.dto;

import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.security.Role;

public class CreateUserDTO {
    private final String password;
    private final ContactDetails contactDetails;
    private final Name name;
    private final Role role;
    public CreateUserDTO(String password, ContactDetails contactDetails, Name name, Role role) {
        this.password = password;
        this.contactDetails = contactDetails;
        this.name = name;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public Name getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
