package com.wishlist.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishlist.api.dto.ItemDTO;
import com.wishlist.api.service.ItemService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/itens")
@Tag(name = "Itens", description = "API de gerenciamento da lista de desejos")
public class ItemController {

    private static final String VALIDATION_ERROR_EXAMPLE = "{\"nome\": \"Nome é necessário\"}";

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Lista todos os itens")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de itens retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItens() {
        List<ItemDTO> itens = itemService.getAllItens();
        return ResponseEntity.ok(itens);
    }

    @Operation(summary = "Busca um item por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item encontrado"),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        Optional<ItemDTO> item = itemService.getItemById(id);
        return item.map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Item criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Corpo da requisição inválido",
            content = @Content(mediaType = "application/json",
                schema = @Schema(example = VALIDATION_ERROR_EXAMPLE)))
    })
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        ItemDTO createdItem = itemService.createItem(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @Operation(summary = "Atualiza um item existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Corpo da requisição inválido",
            content = @Content(mediaType = "application/json",
                schema = @Schema(example = VALIDATION_ERROR_EXAMPLE))),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO) {
        Optional<ItemDTO> updatedItem = itemService.updateItem(id, itemDTO);
        return updatedItem.map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Item removido com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.deleteItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Marca o item como comprado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item marcado como comprado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @PatchMapping("/{id}/marcar-comprado")
    public ResponseEntity<ItemDTO> marcarComprado(@PathVariable Long id) {
        Optional<ItemDTO> updatedItem = itemService.marcarComprado(id);
        return updatedItem.map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }
}
