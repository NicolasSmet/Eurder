package com.application.eurder.domain.userdetails;

import com.application.eurder.exceptions.FieldNullOrEmptyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddressTest {
    @Test
    @DisplayName("when providing an empty street when creating an address an AddressFieldNotValidException is thrown")
    void givenEmptyStreet_whenCreatingAnAddress_thenAddressFieldNotValidExceptionIsThrown() {
        String street = "";
        String number = "9";
        String zipCode = "9150";
        String town = "Rupelmonde";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Address(street,number,zipCode,town))
                .withMessageContaining("street");
    }
    @Test
    @DisplayName("when providing an empty number when creating an address an AddressFieldNotValidException is thrown")
    void givenEmptyNumber_whenCreatingAnAddress_thenAddressFieldNotValidExceptionIsThrown() {
        String street = "Gravin Margaretalaan";
        String number = "";
        String zipCode = "9150";
        String town = "Rupelmonde";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Address(street,number,zipCode,town))
                .withMessageContaining("number");
    }
    @Test
    @DisplayName("when providing an empty zipcode when creating an address an AddressFieldNotValidException is thrown")
    void givenEmptyZipCode_whenCreatingAnAddress_thenAddressFieldNotValidExceptionIsThrown() {
        String street = "Gravin Margaretalaan";
        String number = "9";
        String zipCode = "";
        String town = "Rupelmonde";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Address(street,number,zipCode,town))
                .withMessageContaining("zipcode");
    }
    @Test
    @DisplayName("when providing an empty town when creating an address an AddressFieldNotValidException is thrown")
    void givenEmptyTown_whenCreatingAnAddress_thenAddressFieldNotValidExceptionIsThrown() {
        String street = "Gravin Margaretalaan";
        String number = "9";
        String zipCode = "9150";
        String town = "";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Address(street,number,zipCode,town))
                .withMessageContaining(town);
    }
    @Test
    @DisplayName("when providing a null value for street when creating an address an AddressFieldNotValidException is thrown")
    void givenStreetNull_whenCreatingAnAddress_thenAddressFieldNotValidExceptionIsThrown() {
        String street = null;
        String number = "9";
        String zipCode = "9150";
        String town = "Rupelmonde";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Address(street,number,zipCode,town))
                .withMessageContaining("street");
    }
}