package com.application.eurder.repository;

import com.application.eurder.domain.User;
import com.application.eurder.domain.userdetails.Address;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.exceptions.EmailNotUniqueException;
import com.application.eurder.security.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {
    @Nested
    @DisplayName("Tests regarding the creation of users")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public class UserCreationTests{
        private final UserRepository repository = new UserRepository();
        Address validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
        ContactDetails validContactDetails = new ContactDetails("nicolassmet2@gmail.com", validAddress, "0472454710");
        Name validName = new Name("Nicolas","Smet");
        User user = new User("Test",validName,validContactDetails, Role.CUSTOMER);

        @Test
        @DisplayName("when calling create on a user with valid data, the user can be found in the repository")
        void givenUserWithValidData_whenCallingCreate_thenUserCanBeFoundInTheRepository() {
            repository.create(user);

            Assertions.assertThat(repository.getAll()).contains(user);
        }

        @Test
        @DisplayName("when adding user with a not unique email address than throw EmailNotUniqueException.")
        void givenNewUserWithoutUniqueEmail_whenAddingNewUserToRepository_thenEmailNotUniqueExceptionIsThrown() {
            repository.create(user);

            Assertions.assertThatExceptionOfType(EmailNotUniqueException.class)
                    .isThrownBy(() -> repository.create(user));
        }

        @Test
        @DisplayName("when calling getByEmail on an existing user, the user should be returned")
        void givenAValidUser_whenCallingGetByEmail_thenTheUserShouldBeReturned() {
            repository.create(user);

            Assertions.assertThat(repository.getByEmail("nicolassmet2@gmail.com")).isEqualTo(user);
        }

        @Test
        @DisplayName("when calling getByEmail with an unknown email address, null should be returned")
        void givenAnUnknownEmail_whenCallingGetByEmail_thenNullIsReturned() {
            Assertions.assertThat(repository.getByEmail("test@gmail.com")).isNull();
        }

    }

}
