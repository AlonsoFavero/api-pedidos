package com.example.apipedidos.controller;

import com.example.apipedidos.model.Produto;
import com.example.apipedidos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){

        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@Valid @RequestBody Produto produto){

        Produto produtoCriar = produtoService.salvar(produto);

        return produto;
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id){

        Produto produtoEncontrado = produtoService.buscarPorId(id);

        return produtoEncontrado;
    }
}
