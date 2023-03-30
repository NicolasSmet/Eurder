package com.application.eurder.domain;

import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.exceptions.FieldNotValidException;
import com.application.eurder.exceptions.RoleNotValidException;
import com.application.eurder.security.Feature;
import com.application.eurder.security.Role;
import com.application.eurder.validation.EmptyStringValidator;

import java.util.UUID;

public class User {
    private final UUID id;
    private String password;
    private final Name name;
    private final ContactDetails contactdetails;
    private final Role role;

    public User(String password, Name name, ContactDetails contactdetails, Role role) {
        checkPassword(password);
        checkRole(role);

        this.id = UUID.randomUUID();
        this.password = password;
        this.name = name;
        this.contactdetails = contactdetails;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    public ContactDetails getContactdetails() {
        return contactdetails;
    }

    public Role getRole() {
        return role;
    }

    public void checkPassword(String password) {
        if (EmptyStringValidator.stringNullOrEmpty(password)) {
            throw new FieldNotValidException("password");
        }
    }
    public void checkRole(Role role){
        if(role == null){
            throw new RoleNotValidException();
        }
    }

    public boolean hasAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }
}

