package com.example.apipedidos.exception;

public class ClienteNotFoundException extends RuntimeException{

    public ClienteNotFoundException(String mensagem){
        super(mensagem);
    }
}
