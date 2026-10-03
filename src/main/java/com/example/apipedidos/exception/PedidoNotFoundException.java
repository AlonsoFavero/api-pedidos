package com.example.apipedidos.exception;

public class PedidoNotFoundException extends RuntimeException{

    public PedidoNotFoundException(String mensagem){
        super(mensagem);
    }
}
