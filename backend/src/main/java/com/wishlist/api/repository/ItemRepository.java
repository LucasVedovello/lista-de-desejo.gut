package com.wishlist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishlist.api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
