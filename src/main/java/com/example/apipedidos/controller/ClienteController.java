package com.example.apipedidos.controller;

import com.example.apipedidos.model.Cliente;
import com.example.apipedidos.service.ClienteService;
import com.example.apipedidos.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService){

        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@RequestBody Cliente cliente){

        Cliente clienteCriar = clienteService.salvar(cliente);

        return cliente;
    }
}
