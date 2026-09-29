package com.example.apipedidos.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Produto {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @Positive
    private Integer estoque;

    public Produto(
            String nome,
            BigDecimal preco,
            Integer estoque
    ) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}