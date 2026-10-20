package com.wishlist.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wishlist.api.dto.ItemDTO;
import com.wishlist.api.mapper.ItemMapper;
import com.wishlist.api.model.Item;
import com.wishlist.api.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getAllItens() {
        logger.debug("Fetching all itens");
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ItemDTO> getItemById(Long id) {
        logger.debug("Fetching item with id={}", id);
        return itemRepository.findById(id)
                .map(itemMapper::convertToDTO);
    }

    @Transactional
    public ItemDTO createItem(ItemDTO itemDTO) {
        logger.info("Creating item with nome='{}'", itemDTO.getNome());
        Item item = itemMapper.convertToEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        return itemMapper.convertToDTO(savedItem);
    }

    @Transactional
    public Optional<ItemDTO> updateItem(Long id, ItemDTO itemDTO) {
        logger.info("Updating item with id={}", id);
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setNome(itemDTO.getNome());
                    existingItem.setPreco(itemDTO.getPreco());
                    existingItem.setPrioridade(itemDTO.getPrioridade());
                    if (itemDTO.getComprado() != null) {
                        existingItem.setComprado(itemDTO.getComprado());
                    }
                    Item updatedItem = itemRepository.save(existingItem);
                    return itemMapper.convertToDTO(updatedItem);
                });
    }

    @Transactional
    public boolean deleteItem(Long id) {
        logger.info("Deleting item with id={}", id);
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<ItemDTO> marcarComprado(Long id) {
        logger.info("Marking item with id={} as comprado", id);
        return itemRepository.findById(id)
                .map(item -> {
                    item.setComprado(true);
                    Item updatedItem = itemRepository.save(item);
                    return itemMapper.convertToDTO(updatedItem);
                });
    }
}
