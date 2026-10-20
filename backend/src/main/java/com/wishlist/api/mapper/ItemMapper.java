package com.wishlist.api.mapper;

import com.wishlist.api.dto.ItemDTO;
import com.wishlist.api.model.Item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO convertToDTO(Item item) {
        return new ItemDTO(
            item.getId(),
            item.getNome(),
            item.getPrioridade(),
            item.getPreco(),
            item.getComprado()
        );
    }

    public Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setNome(itemDTO.getNome());
        item.setPreco(itemDTO.getPreco());
        item.setPrioridade(itemDTO.getPrioridade());
        item.setComprado(Boolean.TRUE.equals(itemDTO.getComprado()));
        return item;
    }
}
