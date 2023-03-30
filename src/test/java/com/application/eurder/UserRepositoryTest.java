package com.application.eurder;

import com.application.eurder.domain.User;
import com.application.eurder.domain.userdetails.Address;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.exceptions.EmailNotUniqueException;
import com.application.eurder.repository.UserRepository;
import com.application.eurder.security.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Test
    @DisplayName("when adding user with not a unique email address than throw EmailNotUniqueException.")
    void givenNewUserWithNotUniqueEmail_whenAddingNewUserToRepository_thenEmailNotUniqueExceptionIsThrown() {
        Address validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
        ContactDetails validContactDetails = new ContactDetails("nicolassmet2@gmail.com", validAddress, "0472454710");
        Name validName = new Name("Nicolas","Smet");

        User user = new User("Test",validName,validContactDetails, Role.CUSTOMER);

        repository.create(user);
        Assertions.assertThatExceptionOfType(EmailNotUniqueException.class)
                .isThrownBy(() -> repository.create(user));



    }
}
