package com.wishlist.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de um desejo")
public class ItemDTO {

    @Schema(
        description = "Identificador do desejo, atribuído pelo servidor",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "Nome é necessário")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
    @Schema(
        description = "Título do desejo",
        example = "Carro",
        maxLength = 150,
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nome;

    @Size(max = 100, message = "A prioridade deve ter no máximo 100 caracteres")
    @Schema(
        description = "Prioridade do desejo, opcional",
        example = "alta",
        maxLength = 100
    )
    private String prioridade;

    @NotNull(message = "Preço é necessário")
    @DecimalMin(
        value = "0.0",
        inclusive = true,
        message = "Preço não pode ser negativo"
    )
    @Digits(
        integer = 10,
        fraction = 2,
        message = "Preço deve ter no máximo 10 dígitos inteiros e 2 casas decimais"
    )
    @Schema(
        description = "Preço do desejo",
        example = "30000.00",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal preco;

    @Schema(
        description = "Estado de compra. Se omitido em um PUT, o estado atual é preservado",
        example = "false"
    )
    private Boolean comprado;

    public ItemDTO() {
    }

    public ItemDTO(
            Long id,
            String nome,
            String prioridade,
            BigDecimal preco,
            Boolean comprado
    ) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.preco = preco;
        this.comprado = comprado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getComprado() {
        return comprado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }
}
