package com.application.eurder.domain.userdetails;

import com.application.eurder.exceptions.FieldNotValidException;
import com.application.eurder.validation.EmptyStringValidator;

public class Address {
    private final String street;
    private final String number;
    private final String zipCode;
    private final String town;

    public Address(String street, String number, String zipCode, String town) {
        validateStreet(street);
        validateNumber(number);
        validateZipcode(zipCode);
        validateTown(town);

        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getTown() {
        return town;
    }

    public void validateStreet(String street) {
        if (EmptyStringValidator.stringNullOrEmpty(street)) {
            throw new FieldNotValidException("street");
        }
    }

    public void validateNumber(String number) {
        if (EmptyStringValidator.stringNullOrEmpty(number)) {
            throw new FieldNotValidException("number");
        }
    }

    public void validateZipcode(String zipCode) {
        if (EmptyStringValidator.stringNullOrEmpty(zipCode)) {
            throw new FieldNotValidException("zipcode");
        }
    }

    public void validateTown(String town) {
        if (EmptyStringValidator.stringNullOrEmpty(town)) {
            throw new FieldNotValidException("town");
        }
    }

    @Override
    public String toString() {
        return "Address: " + street + " " + number + " " + zipCode + " " + town;
    }
}
