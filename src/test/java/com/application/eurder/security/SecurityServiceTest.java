package com.application.eurder.security;

import com.application.eurder.domain.User;
import com.application.eurder.domain.userdetails.Address;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.exceptions.FieldFormatNotValidException;
import com.application.eurder.exceptions.UnauthorizedException;
import com.application.eurder.exceptions.UserUnknownException;
import com.application.eurder.exceptions.WrongPasswordException;
import com.application.eurder.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SecurityServiceTest {

    @Nested
    @DisplayName("Tests regarding getting credentials from the header")
    public class CredentialTests{
        SecurityService securityService = new SecurityService(new UserRepository());
        String validEmail = "nicolassmet2@gmail.com";
        String validPassword = "test:";

        @Test
        @DisplayName("when providing a valid authorization string both the valid email and password can be extracted.")
        void givenValidAuthorizationString_whenGetCredentialsIsCalled_thenValidEmailAndPasswordAreExtracted() {
            String validAuthorization = "Basic bmljb2xhc3NtZXQyQGdtYWlsLmNvbTp0ZXN0Og==";

            Credentials credentials = securityService.getCredentialsFromAuthorization(validAuthorization);

            Assertions.assertThat(credentials.getEmail())
                    .isEqualTo(validEmail);
            Assertions.assertThat(credentials.getPassword())
                    .isEqualTo(validPassword);
        }
        @Test
        @DisplayName("When providing an empty authorization string a FieldNullOrEmptyException is thrown")
        void givenEmptyAuthorizationString_whenGetCredentialsIsCalled_thenFieldNullOrEmptyExceptionIsThrown() {
            String emptyAuthorization = "";

            Assertions.assertThatExceptionOfType(FieldFormatNotValidException.class)
                    .isThrownBy(() -> securityService.getCredentialsFromAuthorization(emptyAuthorization));
        }
        @Test
        @DisplayName("When providing an invalid authorization string a FieldNullOrEmptyException is thrown")
        void givenInvalidAuthorizationString_whenGetCredentialsIsCalled_thenFieldNullOrEmptyExceptionIsThrown() {
            String invalidAuthorization = "bmljb2xhc3NtZXRAZ21haWwuY29tOnRlc3Q=";

            Assertions.assertThatExceptionOfType(FieldFormatNotValidException.class)
                    .isThrownBy(() -> securityService.getCredentialsFromAuthorization(invalidAuthorization));
        }
    }
    @Nested
    @DisplayName("Tests regarding the validating of users")
    public class UserValidationTests{
        UserRepository repositoryMock = Mockito.mock(UserRepository.class);
        SecurityService securityService = new SecurityService(repositoryMock);
        Address validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
        ContactDetails validContactDetails = new ContactDetails("nicolassmet2@gmail.com", validAddress, "0472454710");
        Name validName = new Name("Nicolas","Smet");
        User customerUser = new User("Test",validName,validContactDetails, Role.CUSTOMER);

        @Test
        @DisplayName("When calling validateAuthorization on an unknown emailaddress, a UserUnknownException should be thrown.")

        void givenUnknownEmail_whenCallingValidateAuthorization_thenUserUnknownExceptionShouldBeThrown() {
            Credentials credentials = new Credentials("nicolassmet2@gmail.com","Test");
            Mockito.when(repositoryMock.getByEmail(credentials.getEmail()))
                    .thenReturn(null);

            Assertions.assertThatExceptionOfType(UserUnknownException.class)
                    .isThrownBy(() -> securityService.validateAuthorization("Basic dGVzdEBnbWFpbC5jb206dGVzdA==",Feature.CREATE_ITEM));
        }
        @Test
        @DisplayName("When calling validateAuthorization on an incorrect password, a WrongPasswordException should be thrown.")
        void givenInvalidPassword_whenCallingValidateAuthorization_thenWrongPasswordExceptionShouldBeThrown() {
            Credentials credentials = new Credentials("nicolassmet2@gmail.com","wrongpassword");
            Mockito.when(repositoryMock.getByEmail(credentials.getEmail()))
                    .thenReturn(customerUser);

            Assertions.assertThatExceptionOfType(WrongPasswordException.class)
                    .isThrownBy(() -> securityService.validateAuthorization("Basic bmljb2xhc3NtZXQyQGdtYWlsLmNvbTp3cm9uZ3Bhc3N3b3Jk",Feature.CREATE_ITEM));
        }
        @Test
        @DisplayName("When calling validateAuthorization on a user with insufficient features, a UnauthorizedException should be thrown.")
        void givenInsufficientFeatures_whenCallingValidateAuthorization_thenUnauthorizedExceptionShouldBeThrown() {
            Credentials credentials = new Credentials("nicolassmet2@gmail.com","Test");
            Mockito.when(repositoryMock.getByEmail(credentials.getEmail()))
                    .thenReturn(customerUser);
            Assertions.assertThatExceptionOfType(UnauthorizedException.class)
                    .isThrownBy(() -> securityService.validateAuthorization("Basic bmljb2xhc3NtZXQyQGdtYWlsLmNvbTpUZXN0",Feature.CREATE_ITEM));
        }
    }
}