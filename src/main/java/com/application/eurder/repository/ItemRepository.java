package com.application.eurder.repository;

import com.application.eurder.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class ItemRepository {
    private final HashMap<UUID, Item> repository = new HashMap<>();
    public Item create(Item newItem) {
        repository.put(newItem.getId(),newItem);
        return newItem;
    }
    public Item getById(UUID id){
        return repository.get(id);
    }

    public Collection<Item> getAll() {
        return repository.values();
    }
}
