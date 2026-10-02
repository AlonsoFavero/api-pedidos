package com.example.apipedidos.exception;

public class ProdutoNotFoundException extends RuntimeException{

    public ProdutoNotFoundException(String mensagem){
        super(mensagem);
    }
}
