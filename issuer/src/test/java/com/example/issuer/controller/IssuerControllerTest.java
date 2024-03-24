package com.example.issuer.controller;

import com.example.issuer.Service.IssuerService;
import com.example.issuer.dto.IssuerRequest;
import com.example.issuer.dto.IssuerResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaTypeFactory.getMediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.awaitility.Awaitility.given;

@WebMvcTest(controllers = IssuerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class IssuerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssuerService issuerSerivce;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIssuerByBankIdOrBankName() throws Exception {
        IssuerResponse issuerResponse = IssuerResponse.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        IssuerRequest issuerRequest = IssuerRequest.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        when(issuerSerivce.getIssuerByBankIdOrBankName(any())).thenReturn(issuerResponse);

        ResultActions actualResponse = mockMvc.perform(get("/api/issuer")
                        .param("bankId", "12345")
                        .param("bankName", "ABC Bank"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("bankId").value("12345"))
                .andExpect(jsonPath("bankName").value("ABC Bank"));
    }

    @Test
    void getAllIssuers() {
    }

    @Test
    void createIssuer() throws Exception{
        IssuerResponse issuerResponse = IssuerResponse.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        IssuerRequest issuerRequest = IssuerRequest.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        when(issuerSerivce.createIssuer(any())).thenReturn(issuerResponse);

        ResultActions actualResponse = mockMvc.perform(post("/api/issuer")
                        .contentType(APPLICATION_JSON)
                   //     .param("bankId", "12345")
                   //     .param("bankName", "ABC Bank")
                        .content(objectMapper.writeValueAsString(issuerRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("bankId").value("12345"))
                .andExpect(jsonPath("bankName").value("ABC Bank"));
    }

    @Test
    void updateIssuer() {
    }
}