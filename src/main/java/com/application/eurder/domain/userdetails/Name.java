package com.application.eurder.domain.userdetails;

import com.application.eurder.exceptions.FieldNullOrEmptyException;
import com.application.eurder.validation.NullOrEmptyValidator;

import java.util.Objects;

public class Name {
    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        checkfirstName(firstName);
        checkLastName(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }
    private void checkfirstName(String firstName){
        if (NullOrEmptyValidator.stringNullOrEmpty(firstName)){
            throw new FieldNullOrEmptyException("first name");
        }
    }
    private void checkLastName(String lastName){
        if (NullOrEmptyValidator.stringNullOrEmpty(lastName)){
            throw new FieldNullOrEmptyException("last name");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
