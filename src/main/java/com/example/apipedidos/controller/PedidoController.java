package com.example.apipedidos.controller;

import com.example.apipedidos.model.Pedido;
import com.example.apipedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController{

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@Valid @RequestBody Pedido pedido){

        Pedido pedidoCriar = pedidoService.salvar(pedido);

        return pedido;
    }

    @PutMapping("/{id}/pagar")
    public Pedido pagar(@PathVariable Long id){

        return pedidoService.pagar(id);
    }
}
