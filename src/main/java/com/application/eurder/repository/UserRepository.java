package com.application.eurder.repository;

import com.application.eurder.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class UserRepository {
    private final HashMap<UUID, User> repository = new HashMap<>();

    public User create(User newUser) {
        repository.put(newUser.getId(),newUser);
        return newUser;
    }
    public User getById(UUID id){
        return repository.get(id);
    }

    public Collection<User> getAll() {
        return repository.values();
    }

}
