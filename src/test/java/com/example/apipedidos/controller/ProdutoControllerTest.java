package com.example.apipedidos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void deveCadastrarProduto() throws Exception{

        mockMvc.perform(
                post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Mouse",
                                "preco": 100,
                                "estoque": 10
                                }
                                """)
        ).andExpect(status().isCreated());
    }

    @Test
    void deveBuscarPorId() throws Exception{

        mockMvc.perform(
                post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Mouse",
                                "preco": 100,
                                "estoque": 10
                                }
                                """)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                get("/produtos/1")
        ).andExpect(status().isOk());
    }

    @Test
    void deveRetornar404QuandoProdutoNaoExiste() throws Exception{

        mockMvc.perform(
                get("/produtos/999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar400QuandoProdutoForInvalido() throws Exception{

        mockMvc.perform(
                post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "",
                                "preco": -1,
                                "estoque": -10
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }
}
