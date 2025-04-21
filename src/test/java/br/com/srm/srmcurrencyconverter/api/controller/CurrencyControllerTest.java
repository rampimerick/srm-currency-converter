package br.com.srm.srmcurrencyconverter.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCurrenciesTest() throws Exception {
        mockMvc.perform(get("/api/v1/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].currencyId").value(1))
                .andExpect(jsonPath("$[0].name").value("OURO REAL"))
                .andExpect(jsonPath("$[1].currencyId").value(2))
                .andExpect(jsonPath("$[1].name").value("TIBAR"));
    }
}
