package com.application.eurder.domain.userdetails;

import com.application.eurder.exceptions.EmailNotValidException;
import com.application.eurder.exceptions.FieldNullOrEmptyException;
import com.application.eurder.validation.NullOrEmptyValidator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactDetails {
    private final String email;
    private final Address address;
    private final String phoneNumber;
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public ContactDetails(String email, Address address, String phoneNumber) {
        checkEmailFormat(email);
        checkPhoneNumber(phoneNumber);

        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void checkEmailFormat(String email){
        if(email == null || !isValidEmail(email)){
            throw new EmailNotValidException();
        }
    }

    public boolean isValidEmail(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);

        return matcher.matches();
    }
    public void checkPhoneNumber(String phoneNumber){
        if (NullOrEmptyValidator.stringNullOrEmpty(phoneNumber)){
            throw new FieldNullOrEmptyException("phone number");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, address, phoneNumber);
    }
}
