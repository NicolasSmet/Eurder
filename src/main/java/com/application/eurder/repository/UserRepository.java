package com.application.eurder.repository;

import com.application.eurder.domain.User;
import com.application.eurder.domain.userdetails.Address;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.exceptions.EmailNotUniqueException;
import com.application.eurder.security.Role;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    public UserRepository(){
        Address validAddress = new Address("Adminlaan", "1", "1000", "Brussel");
        ContactDetails validContactDetails = new ContactDetails("Admin@gmail.com", validAddress, "0");
        Name validName = new Name("Admin","Admin");

        User user = new User("Test",validName,validContactDetails, Role.ADMIN);
        create(user);
    }
    private final HashMap<UUID, User> repository = new HashMap<>();

    public User create(User newUser) {
        checkEmailUnique(newUser);

        repository.put(newUser.getId(),newUser);
        return newUser;
    }

    public User getById(UUID id){return repository.get(id);
    }

    public Collection<User> getAll() {
        return repository.values();
    }

    public User getByEmail(String email){
        return repository.values()
                .stream()
                .filter(user -> getEmailFromUser(user).equals(email))
                .findAny()
                .orElse(null);
    }

    private static String getEmailFromUser(User user) {
        return user.getContactdetails().getEmail();
    }

    private void checkEmailUnique(User newUser) {
        if(getByEmail(getEmailFromUser(newUser)) != null){
            throw new EmailNotUniqueException();
        }
    }
}
