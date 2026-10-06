package com.example.apipedidos.service;

import com.example.apipedidos.exception.ClienteNotFoundException;
import com.example.apipedidos.exception.EstoqueInsuficienteException;
import com.example.apipedidos.exception.ProdutoNotFoundException;
import com.example.apipedidos.model.*;
import com.example.apipedidos.repository.ClienteRepository;
import com.example.apipedidos.repository.PedidoRepository;
import com.example.apipedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository ){

        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido salvar(Pedido pedido){

        Cliente cliente = pedido.getCliente();

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getId());

        if(!clienteEncontrado.isPresent()) {

            throw new ClienteNotFoundException("cliente não encontrado");
        }

        BigDecimal total = BigDecimal.ZERO;

        for(ItemPedido itemPedido : pedido.getItens()){

            Produto produto = itemPedido.getProduto();

            Optional<Produto> produtoEncontrado = produtoRepository.findById(produto.getId());

            if(!produtoEncontrado.isPresent()){

                throw new ProdutoNotFoundException("produto não encontardo");
            }

            if(itemPedido.getQuantidade() > produtoEncontrado.get().getEstoque()){

                throw new EstoqueInsuficienteException("estoque insuficiente");
            }

            produto .setEstoque(
                    produtoEncontrado.get().getEstoque() - itemPedido.getQuantidade()
            );

            BigDecimal valorItem = produtoEncontrado.get().getPreco()
                    .multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));

          total =  total.add(valorItem);
        }

        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id){

        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> listar(){

        return pedidoRepository.findAll();
    }

    public Pedido pagar (Long id){

        Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id);

    if(!pedidoEncontrado.isPresent()){

        throw new ProdutoNotFoundException("pedido não encontardo");

    }

    Pedido pedido = pedidoEncontrado.get();

    pedido.setStatusPedido(StatusPedido.PAGO);

    return pedidoRepository.save(pedido);
    }
}
