package com.example.apipedidos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void devePagarPedido() throws Exception {

        ResultActions produto = mockMvc.perform(
                post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Mouse",
                                    "preco": 100,
                                    "estoque": 10
                                }
                                """)
        ).andExpect(
                status().isCreated()
        );

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(
                status().isCreated()
        );

        String produtoJson = produto.andReturn()
                .getResponse()
                .getContentAsString();

        String clienteJson = cliente.andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("PRODUTO" + produtoJson);
        System.out.println("CLIENTE" + clienteJson);
        System.out.println("ANTES DE CRIAR PEDIDO");
        System.out.println("PEDIDO CRIADO");

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente":{
                                    "id": 1
                                    },
                                    "produto": {
                                    "id": 1
                                    },
                                    "quantidade": 1
                                }
                                """)
        ).andExpect(
                status().isCreated()
        );
    }
}