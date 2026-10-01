package com.example.apipedidos.service;

import com.example.apipedidos.model.Produto;
import com.example.apipedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

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

        return produtoRepository.findById(id).orElse(null);
    }
}
