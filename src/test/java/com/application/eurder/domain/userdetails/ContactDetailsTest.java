package com.application.eurder.domain.userdetails;

import com.application.eurder.exceptions.EmailNotValidException;
import com.application.eurder.exceptions.FieldNotValidException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ContactDetailsTest {
    @Nested
    @DisplayName("Tests regarding email validity")
    public class EmailTests {
        private Address validAddress;
        private String validPhoneNumber;

        @BeforeEach
        void setUpEmailTests() {
            validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
            validPhoneNumber = "0472/45.47.10";

        }

        @Test
        @DisplayName("creating contact details with a valid email address")
        void givenAValidEmailAddress_whenCreatingContactDetails_thenEmailCanBeReturned() {
            String email = "nicolassmet2@gmail.com";

            ContactDetails contactDetails = new ContactDetails(email, validAddress, validPhoneNumber);

            Assertions.assertThat(contactDetails.isValidEmail("nicolassmet2@gmail.com")).isTrue();
            Assertions.assertThat(contactDetails.getEmail()).isEqualTo(email);
        }

        @Test
        @DisplayName("Creating contact details with an invalid email address")
        void givenAnInValidEmailAddress_whenCreatingContactDetails_thenEmailNotValidExceptionIsThrown() {
            String email = "invalid";

            Assertions.assertThatExceptionOfType(EmailNotValidException.class).isThrownBy(() -> new ContactDetails(email, validAddress, validPhoneNumber));
        }

        @Test
        @DisplayName("Creating contact details with an empty email address results in an emailnotvalidexception")
        void givenEmptyEmailAddress_whenCreatingContactDetails_thenEmailNotValidExceptionIsThrown() {
            String email = "";

            Assertions.assertThatExceptionOfType(EmailNotValidException.class).isThrownBy(() -> new ContactDetails(email, validAddress, validPhoneNumber));
        }

        @Test
        @DisplayName("Creating contact details with null for email address results in an emailnotvalidexception")
        void givenNullForEmail_whenCreatingContactDetails_thenEmailNotValidExceptionIsThrown() {
            String email = null;

            Assertions.assertThatExceptionOfType(EmailNotValidException.class).isThrownBy(() -> new ContactDetails(email, validAddress, validPhoneNumber));
        }
    }

    @Nested
    @DisplayName("Tests regarding the validity of the phone number.")
    public class PhoneNumberTests {
        private String validEmail;
        private Address validAddress;

        @BeforeEach
        void setUpPhoneNumberTests() {
            validEmail = "nicolassmet2@gmail.com";
            validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");

        }

        @Test
        @DisplayName("Creating contact details with a valid phone number")
        void givenValidPhoneNumber_whenCreatingTheContactDetails_thenPhoneNumberCanBeReturned() {
            //GIVEN
            String phoneNumber = "0472454710";
            //WHEN
            ContactDetails contactDetails = new ContactDetails(validEmail,validAddress,phoneNumber);
            //THEN
            Assertions.assertThat(contactDetails.getPhoneNumber()).isEqualTo(phoneNumber);
        }

        @Test
        @DisplayName("Creating contact details with empty phone number")
        void givenEmptyPhoneNumber_whenCreatingContactDetails_thenFieldNotValidExceptionIsThrown() {
            //GIVEN
            String phoneNumber = "";
            //WHEN
            //THEN
            Assertions.assertThatExceptionOfType(FieldNotValidException.class)
                    .isThrownBy(() -> new ContactDetails(validEmail,validAddress,phoneNumber))
                    .withMessageContaining("phone number");
        }
        @Test
        @DisplayName("Creating contact details with null phone number")
        void givenNullPhoneNumber_whenCreatingContactDetails_thenFieldNotValidExceptionIsThrown() {
            //GIVEN
            String phoneNumber = null;
            //WHEN
            //THEN
            Assertions.assertThatExceptionOfType(FieldNotValidException.class)
                    .isThrownBy(() -> new ContactDetails(validEmail,validAddress,phoneNumber))
                    .withMessageContaining("phone number");
        }
    }

}