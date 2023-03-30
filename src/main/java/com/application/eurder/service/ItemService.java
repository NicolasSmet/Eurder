package com.application.eurder.service;

import com.application.eurder.domain.Item;
import com.application.eurder.dto.CreateItemDTO;
import com.application.eurder.dto.ItemDTO;
import com.application.eurder.mapper.ItemMapper;
import com.application.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    public ItemService(ItemRepository itemRepository, ItemMapper mapper) {
        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    public ItemDTO create(CreateItemDTO newItem) {
        Item itemToSave = mapper.toDomain(newItem);
        return mapper.toDTO(itemRepository.create(itemToSave));
    }
}