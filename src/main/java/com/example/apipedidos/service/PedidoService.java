package com.example.apipedidos.service;

import com.example.apipedidos.exception.ClienteNotFoundException;
import com.example.apipedidos.model.Cliente;
import com.example.apipedidos.model.Pedido;
import com.example.apipedidos.repository.ClienteRepository;
import com.example.apipedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository ){

        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Pedido salvar(Pedido pedido){

        Cliente cliente = pedido.getCliente();

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getId());

        if(clienteEncontrado.isPresent()){

             return pedidoRepository.save(pedido);
        }else {

            throw new ClienteNotFoundException("cliente não encontrado");
        }
    }

    public Pedido buscarPorId(Long id){

        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> listar(){

        return pedidoRepository.findAll();
    }
}
