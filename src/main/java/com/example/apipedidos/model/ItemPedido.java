package com.example.apipedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ItemPedido {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;
    @NotNull
    @Positive
    private Integer quantidade;

    ItemPedido(
            Pedido pedido,
            Produto produto,
            Integer quantidade
    ){
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido(){

    }
}
