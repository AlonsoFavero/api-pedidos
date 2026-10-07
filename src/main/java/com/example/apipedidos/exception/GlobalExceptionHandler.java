package com.example.apipedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<?> tratarPedidoNaoEncontrado (PedidoNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<?> tratarClienteNaoEncontrado (ClienteNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<?> tratarProdutoNaoEncontrado (ProdutoNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
