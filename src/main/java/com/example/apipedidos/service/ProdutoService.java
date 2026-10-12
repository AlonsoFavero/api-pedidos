package com.example.apipedidos.service;

import com.example.apipedidos.model.Produto;
import com.example.apipedidos.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto){

         return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id){

        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "produto não encontrado"
        ));
    }

    public List<Produto> listar(){

        return produtoRepository.findAll();
    }
}
