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

        ResultActions pedido = mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cliente":{
                                        "id": 1
                                    },
                                    "itens": [
                                        {
                                            "produto": {
                                                "id": 1
                                            },
                                            "quantidade": 1
                                        }
                                    ]
                                }
                                """)
        ).andDo(result -> System.out.println(
                result.getResponse().getContentAsString()
        ));

        String pedidoJson = pedido.andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode pedidoNode = mapper.readTree(pedidoJson);
        Long buscarPedido = pedidoNode.get("id").asLong();

        ResultActions pagamento = mockMvc.perform(
                put("/pedidos/" + buscarPedido + "/pagar")
        ).andExpect(
                status().isOk()
        );

        String pagamentoJson = pagamento.andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("PAGAMENTO" + pagamentoJson);
    }

    @Test
    void deveBuscarPorId() throws Exception{

        ResultActions produtos = mockMvc.perform(
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


        String produtoJson = produtos.andReturn()
                .getResponse()
                .getContentAsString();

        ResultActions pedido = mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "cliente":{
                                "id": 1
                                },
                                "itens":[
                                {
                                "produto": {
                                "id": 1
                                },
                                "quantidade": 1
                                }
                                ]
                                }
                                """)
        ).andDo(result -> System.out.println(
                result.getResponse().getContentAsString()
        ));

        String pedidoJson = pedido.andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode pedidoNode = mapper.readTree(pedidoJson);
        Long buscarPedido = pedidoNode.get("id").asLong();

        ResultActions deveBuscarPorId = mockMvc.perform(
                get("/pedidos/" + buscarPedido)
        ).andExpect(
                status().isOk()
        );

    }

    @Test
    void deveListarPedido() throws Exception{

        ResultActions produtos = mockMvc.perform(
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

        String produtoJson = produtos.andReturn()
                .getResponse()
                .getContentAsString();

        ResultActions pedido = mockMvc.perform(
                post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "cliente":{
                                "id": 1
                                },
                                "itens":[
                                {
                                "produto": {
                                "id": 1
                                },
                                "quantidade": 1
                                }
                                ]
                                }
                                """)
        ).andDo(result -> System.out.println(
                result.getResponse().getContentAsString()
        ));

        String pedidoJson = pedido.andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode pedidoNode = mapper.readTree(pedidoJson);
        Long buscarPedido = pedidoNode.get("id").asLong();

        ResultActions deveListarPedido = mockMvc.perform(
                get("/pedidos")
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void deveRetornar404AoPagarPedidoInexistente() throws Exception{

       ResultActions deveRetornar404  = mockMvc.perform(
                put("/pedidos/999/pagar")
        ).andExpect(
                status().isNotFound()
        );
    }
}