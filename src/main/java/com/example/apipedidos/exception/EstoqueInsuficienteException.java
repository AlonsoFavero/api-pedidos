package com.example.apipedidos.exception;

public class EstoqueInsuficienteException extends RuntimeException{

    public EstoqueInsuficienteException(String mensagem){
         super(mensagem);
    }
}
