package com.example.apipedidos.controller;

import com.example.apipedidos.model.Pedido;
import com.example.apipedidos.service.PedidoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PedidoController{

    private PedidoService pedidoService;

    @PutMapping("/{id}/pagar")
    public Pedido pagar(@PathVariable Long id){

        return pedidoService.pagar(id);
    }
}
