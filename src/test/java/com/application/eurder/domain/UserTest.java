package com.application.eurder.domain;

import com.application.eurder.domain.userdetails.Address;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.exceptions.FieldNotValidException;
import com.application.eurder.exceptions.RoleNotValidException;
import com.application.eurder.security.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    private final Address validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
    private final ContactDetails validContactDetails = new ContactDetails("nicolassmet2@gmail.com", validAddress, "0472454710");
    private final Name validName = new Name("Nicolas","Smet");

    @Test
    @DisplayName("When the password is empty a FieldNotValidException is thrown")
    void givenEmptyPassword_whenCreatingUser_thenFieldNotValidExceptionIsThrown() {
        String password = "";
        Assertions.assertThatExceptionOfType(FieldNotValidException.class)
                .isThrownBy(() -> new User(password,validName,validContactDetails,Role.CUSTOMER));
    }
    @Test
    @DisplayName("When the password is null a FieldNotValidException is thrown")
    void givenNullPassword_whenCreatingUser_thenFieldNotValidExceptionIsThrown() {
        String password = null;
        Assertions.assertThatExceptionOfType(FieldNotValidException.class)
                .isThrownBy(() -> new User(password,validName,validContactDetails,Role.CUSTOMER));
    }
    @Test
    @DisplayName("When the role is null a RoleNotValidException is thrown")
    void givenNullRole_whenCreatingUser_thenFieldNotValidExceptionIsThrown() {
        Role role = null;
        Assertions.assertThatExceptionOfType(RoleNotValidException.class)
                .isThrownBy(() -> new User("validPassword",validName,validContactDetails,role));
    }
}