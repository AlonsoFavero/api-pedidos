package com.example.apipedidos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        ).andExpect(status().isCreated());

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        String produtoJson = produto.andReturn()
                .getResponse()
                .getContentAsString();

        String clienteJson = cliente.andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        Long produtoId = mapper.readTree(produtoJson).get("id").asLong();
        Long clienteId = mapper.readTree(clienteJson).get("id").asLong();

        ResultActions pedido = mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 1
                                        }
                                    ]
                                }
                                """.formatted(clienteId, produtoId))
        ).andExpect(status().isCreated());

        String pedidoJson = pedido.andReturn()
                .getResponse()
                .getContentAsString();

        Long pedidoId = mapper.readTree(pedidoJson).get("id").asLong();

        mockMvc.perform(
                put("/pedidos/" + pedidoId + "/pagar")
        ).andExpect(status().isOk());
    }

    @Test
    void deveBuscarPorId() throws Exception {

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
        ).andExpect(status().isCreated());

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long produtoId = mapper.readTree(
                produto.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        Long clienteId = mapper.readTree(
                cliente.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        ResultActions pedido = mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 1
                                        }
                                    ]
                                }
                                """.formatted(clienteId, produtoId))
        ).andExpect(status().isCreated());

        Long pedidoId = mapper.readTree(
                pedido.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                get("/pedidos/" + pedidoId)
        ).andExpect(status().isOk());
    }

    @Test
    void deveListarPedido() throws Exception {

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
        ).andExpect(status().isCreated());

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long produtoId = mapper.readTree(
                produto.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        Long clienteId = mapper.readTree(
                cliente.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 1
                                        }
                                    ]
                                }
                                """.formatted(clienteId, produtoId))
        ).andExpect(status().isCreated());

        mockMvc.perform(
                get("/pedidos")
        ).andExpect(status().isOk());
    }

    @Test
    void deveRetornar404AoPagarPedidoInexistente() throws Exception {

        mockMvc.perform(
                put("/pedidos/999/pagar")
        ).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404AoCriarPedidoComProdutoInexistente()
            throws Exception {

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long clienteId = mapper.readTree(
                cliente.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": 999
                                            },
                                            "quantidade": 1
                                        }
                                    ]
                                }
                                """.formatted(clienteId))
        ).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404AoCriarPedidoComClienteInexistente()
            throws Exception {

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
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long produtoId = mapper.readTree(
                produto.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": 999
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 1
                                        }
                                    ]
                                }
                                """.formatted(produtoId))
        ).andExpect(status().isNotFound());

        ResultActions consultaProduto = mockMvc.perform(
                get("/produtos/" + produtoId)
        ).andExpect(status().isOk());

        JsonNode produtoAtualizado = mapper.readTree(
                consultaProduto.andReturn().getResponse().getContentAsString()
        );

        assertEquals(10, produtoAtualizado.get("estoque").asInt());
    }

    @Test
    void deveRetornar400QuandoEstoqueForInsuficiente()
            throws Exception {

        ResultActions produto = mockMvc.perform(
                post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Mouse",
                                    "preco": 100,
                                    "estoque": 2
                                }
                                """)
        ).andExpect(status().isCreated());

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long produtoId = mapper.readTree(
                produto.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        Long clienteId = mapper.readTree(
                cliente.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 5
                                        }
                                    ]
                                }
                                """.formatted(clienteId, produtoId))
        ).andExpect(status().isBadRequest());

        ResultActions consultaProduto = mockMvc.perform(
                get("/produtos/" + produtoId)
        ).andExpect(status().isOk());

        JsonNode produtoAtualizado = mapper.readTree(
                consultaProduto.andReturn().getResponse().getContentAsString()
        );

        assertEquals(2, produtoAtualizado.get("estoque").asInt());
    }

    @Test
    void deveDiminuirEstoqueAoCriarPedido() throws Exception {

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
        ).andExpect(status().isCreated());

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long produtoId = mapper.readTree(
                produto.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        Long clienteId = mapper.readTree(
                cliente.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 3
                                        }
                                    ]
                                }
                                """.formatted(clienteId, produtoId))
        ).andExpect(status().isCreated());

        ResultActions consultaProduto = mockMvc.perform(
                get("/produtos/" + produtoId)
        ).andExpect(status().isOk());

        JsonNode produtoAtualizado = mapper.readTree(
                consultaProduto.andReturn().getResponse().getContentAsString()
        );

        assertEquals(7, produtoAtualizado.get("estoque").asInt());
    }

    @Test
    void deveManterEstoqueQuandoUmDosItensDoPedidoFalhar()
            throws Exception {

        ResultActions produtoA = mockMvc.perform(
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

        ResultActions produtoB = mockMvc.perform(
                post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Teclado",
                                    "preco": 200,
                                    "estoque": 2
                                }
                                """)
        ).andExpect(status().isCreated());

        ResultActions cliente = mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Alonso",
                                    "email": "alonso@email.com"
                                }
                                """)
        ).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();

        Long produtoAId = mapper.readTree(
                produtoA.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        Long produtoBId = mapper.readTree(
                produtoB.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        Long clienteId = mapper.readTree(
                cliente.andReturn().getResponse().getContentAsString()
        ).get("id").asLong();

        mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente": {
                                        "id": %d
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 3
                                        },
                                        {
                                            "produto": {
                                                "id": %d
                                            },
                                            "quantidade": 5
                                        }
                                    ]
                                }
                                """.formatted(
                                clienteId, produtoAId, produtoBId
                        ))
        ).andExpect(status().isBadRequest());

        ResultActions consultaProdutoA = mockMvc.perform(
                get("/produtos/" + produtoAId)
        ).andExpect(status().isOk());

        ResultActions consultaProdutoB = mockMvc.perform(
                get("/produtos/" + produtoBId)
        ).andExpect(status().isOk());

        JsonNode produtoAAtualizado = mapper.readTree(
                consultaProdutoA.andReturn().getResponse().getContentAsString()
        );

        JsonNode produtoBAtualizado = mapper.readTree(
                consultaProdutoB.andReturn().getResponse().getContentAsString()
        );

        assertEquals(10, produtoAAtualizado.get("estoque").asInt());
        assertEquals(2, produtoBAtualizado.get("estoque").asInt());
    }
}
