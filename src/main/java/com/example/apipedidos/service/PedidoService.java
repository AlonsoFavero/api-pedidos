package com.example.apipedidos.service;

import com.example.apipedidos.model.Pedido;
import com.example.apipedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository){

        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvar(Pedido pedido){

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id){

        return pedidoRepository.findById(id).orElse(null);
    }
}
