package com.application.eurder.domain.userdetails;

import com.application.eurder.exceptions.FieldNullOrEmptyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    @DisplayName("when creating a name with valid first name and last name both can be returned.")
    void givenValidFirstNameAndLastName_whenCreatingName_thenValidNameIsCreated() {
        //GIVEN
        String firstName = "Nicolas";
        String lastName = "Smet";
        //WHEN
        Name name = new Name(firstName,lastName);
        //THEN
        Assertions.assertThat(name.toString()).isEqualTo("Name: Nicolas Smet");
    }

    @Test
    @DisplayName("when creating a name with and empty firstname a fieldnotvalidexception is thrown")
    void givenEmptyFirstName_whenCreatingName_thenFieldNotValidExceptionIsThrown() {
        //GIVEN
        String firstName = "";
        String lastName = "Smet";
        //WHEN
        //THEN
        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Name(firstName,lastName))
                .withMessageContaining("first name");
    }
    @Test
    @DisplayName("when creating a name with an empty lastname a fieldnotvalidexception is thrown")
    void givenEmptyLastName_whenCreatingName_thenFieldNotValidExceptionIsThrown() {
        //GIVEN
        String firstName = "Nicolas";
        String lastName = "";
        //WHEN
        //THEN
        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Name(firstName,lastName))
                .withMessageContaining("last name");
    }
    @Test
    @DisplayName("when creating a name with null firstName a fieldnotvalidexception is thrown")
    void givenNullFirstName_whenCreatingName_thenFieldNotValidExceptionIsThrown() {
        //GIVEN
        String firstName = null;
        String lastName = "Smet";
        //WHEN
        //THEN
        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Name(firstName,lastName))
                .withMessageContaining("first name");
    }
    @Test
    @DisplayName("when creating a name with null lastname a fieldnotvalidexception is thrown")
    void givenNullLastName_whenCreatingName_thenFieldNotValidExceptionIsThrown() {
        //GIVEN
        String firstName = "Nicolas";
        String lastName = null;
        //WHEN
        //THEN
        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Name(firstName,lastName))
                .withMessageContaining("last name");
    }
}