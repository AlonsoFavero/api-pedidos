package com.example.apipedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente {

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;

    public Cliente(
            String nome,
            String email
    ){
        this.nome = nome;
        this.email = email;
    }
    public Cliente(){

    }
}
