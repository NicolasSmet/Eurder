package com.application.eurder.mapper;

import com.application.eurder.domain.Item;
import com.application.eurder.dto.CreateItemDTO;
import com.application.eurder.dto.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toDomain(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(), createItemDTO.getAmount());
    }

    public ItemDTO toDTO(Item item) {
        return new ItemDTO(item.getId(),item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }
}
