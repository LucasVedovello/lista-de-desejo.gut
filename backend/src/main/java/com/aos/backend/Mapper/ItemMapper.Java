package com.aos.productsapi.mapper;

import com.aos.productsapi.dto.ProductDTO;
import com.aos.productsapi.model.Product;

public class ItemMapper {

     private ItemMapper() {}

      public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getNome(),
                product.getPrioridade(),
                product.getPreco(),
                product.getCompleted()
                );
    }

     public static Product toEntity(WishDTO WishDTO) {
        return new Product(
                productDTO.getNome(),
                productDTO.getPrioridade(),
                productDTO.getPreco(),
                productDTO.getCompleted()
        );
    }
}
