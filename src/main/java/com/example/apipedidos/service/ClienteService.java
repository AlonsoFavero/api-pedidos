package com.example.apipedidos.service;

import com.example.apipedidos.model.Cliente;
import com.example.apipedidos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService{

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente){

        return clienteRepository.save(cliente);
    }
}

