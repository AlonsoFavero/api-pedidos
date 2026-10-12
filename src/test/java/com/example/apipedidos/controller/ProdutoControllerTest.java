package com.example.apipedidos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
}
