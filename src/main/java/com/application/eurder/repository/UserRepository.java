package com.application.eurder.repository;

import com.application.eurder.domain.User;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.exceptions.EmailNotUniqueException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class UserRepository {
    private final HashMap<UUID, User> repository = new HashMap<>();

    public User create(User newUser) {
        checkEmailUnique(newUser);

        repository.put(newUser.getId(),newUser);
        return newUser;
    }

    private void checkEmailUnique(User newUser) {
        if(isEmailNotUnique(getEmail(newUser))){
            throw new EmailNotUniqueException();
        }
    }

    private static String getEmail(User newUser) {
        return newUser.getContactdetails().getEmail();
    }

    private boolean isEmailNotUnique(String email) {
        return repository.values().stream()
                .map(User::getContactdetails)
                .map(ContactDetails::getEmail)
                .anyMatch(streamEmail -> streamEmail.equals(email));
    }

    public User getById(UUID id){return repository.get(id);
    }

    public Collection<User> getAll() {
        return repository.values();
    }

}
