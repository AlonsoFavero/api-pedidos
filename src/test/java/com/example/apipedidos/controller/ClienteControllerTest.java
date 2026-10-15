package com.example.apipedidos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void deveCadastrarCliente() throws Exception{

        mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Alonso",
                                "email": "alonso@gmail.com",
                                "senha": 10
                                }
                                """)
        ).andExpect(status().isCreated());
    }
    @Test
    void deveRetornar404SeClienteForInvalido() throws Exception{

        mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "",
                                "email": "",
                                "senha": 10
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoEmailForInvalido() throws Exception{

        mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Alonso",
                                "email": "alonso",
                                "senha": 10
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void deveBuscarClientePorId() throws Exception{

        mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Alonso",
                                "email": "alonso@gmail.com",
                                "senha": 10
                                }
                                """)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                get("/clientes/1")
        ).andExpect(status().isOk());
    }

    @Test
    void deveRetornar404QuandoClienteNaoExiste() throws Exception{

        mockMvc.perform(
                get("/clientes/999")
        ).andExpect(status().isNotFound());
    }
}
