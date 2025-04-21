package br.com.srm.srmcurrencyconverter.api.controller;


import br.com.srm.srmcurrencyconverter.util.CatalogEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class KingdomControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void createKingdomTest() throws Exception {
        mockMvc.perform(post("/api/v1/kingdoms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CatalogEnum.KINGDOM_REQUEST_NEWKINGDOM.getFileAsString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.kingdomId").exists());
    }

    @Test
    void getAllKingdomsTest() throws Exception {
        mockMvc.perform(get("/api/v1/kingdoms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].kingdomId", is(1)));
    }

    @Test
    void getKingdomByIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/kingdoms/{kingdomId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kingdomId", is(1)))
                .andExpect(jsonPath("$.name", is("Khazad-dûm")));
    }

    @Test
    void getProductByKingdomIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/kingdoms/{kingdomId}/products", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].productKingdomId", is(1)))
                .andExpect(jsonPath("$.[0].product.productId", is(2)))
                .andExpect(jsonPath("$.[0].product.name", is("Machado Anão")))
                .andExpect(jsonPath("$.[0].product.description", is("Machado confiável utilizado pelos anãos em guerra.")));
    }

    @Test
    void getKingdomByIdNotFoundTest() throws Exception {
        mockMvc.perform(get("/api/v1/kingdoms/{kingdomId}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("NOT_FOUND")));
    }




}
