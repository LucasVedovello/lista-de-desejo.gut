package com.aos.backend.model.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de um desejo")
public class WishDTO {

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
        description = "Prioridade do desejo",
        example = "Baixa",
        maxLength = 100,
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String prioridade;

    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "Preço deve ser maior que zero"
    )
    @Digits(
        integer = 10,
        fraction = 2,
        message = "Preço deve ter no máximo 10 dígitos inteiros e 2 casas decimais"
    )
    @Schema(
        description = "Preço do desejo",
        example = "30000.00"
    )
    private BigDecimal preco;

    @Schema(
        description = "Estado de conclusão. Se omitido em um PUT, o estado atual é preservado",
        example = "false"
    )
    private Boolean completed;

    public WishDTO() {
        this.nome = "";
        this.completed = false;
    }

    public WishDTO(
            Long id,
            String nome,
            String prioridade,
            BigDecimal preco,
            Boolean completed
    ) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.preco = preco;
        this.completed = completed;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
