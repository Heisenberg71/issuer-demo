package com.example.issuer.Service;

import com.example.issuer.controller.IssuerController;
import com.example.issuer.dao.IssuerDao;
import com.example.issuer.dto.IssuerRequest;
import com.example.issuer.dto.IssuerResponse;
import com.example.issuer.entity.Issuer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IssuerServiceTest {
    @Mock
    private IssuerDao issuerDao;
    @InjectMocks
    private IssuerService issuerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIssuerByBankIdOrBankName() {
        IssuerRequest issuerRequestByBankId = IssuerRequest.builder()
                .bankId("12345")
                .build();

        IssuerRequest issuerRequestByBankName = IssuerRequest.builder()
                .bankName("ABC Bank")
                .build();

        IssuerResponse issuerResponse = IssuerResponse.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        Issuer issuer = Issuer.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .dskl("12344321")
                .dekt("12344321")
                .build();

        when(issuerDao.getByBankId(any())).thenReturn(issuer);
        when(issuerDao.getByBankName(any())).thenReturn(issuer);

        IssuerResponse actualIssuer_getByBankId = issuerService.getIssuerByBankIdOrBankName(issuerRequestByBankId);
        IssuerResponse actualIssuer_getByBankName = issuerService.getIssuerByBankIdOrBankName(issuerRequestByBankName);

        assertEquals(issuerResponse, actualIssuer_getByBankId);
        assertEquals(issuerResponse, actualIssuer_getByBankName);
    }

    @Test
    void createIssuer() {
        IssuerRequest issuerRequest = IssuerRequest.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        Issuer issuer = Issuer.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .dskl("12344321")
                .dekt("12344321")
                .build();

        when(issuerDao.createIssuer(issuerRequest)).thenReturn(issuer);

        IssuerResponse actualIssuerResponse = issuerService.createIssuer(issuerRequest);

        IssuerResponse issuerResponse = IssuerResponse.builder()
                .bankId("12345")
                .bankName("ABC Bank")
                .build();

        assertEquals(issuerResponse, actualIssuerResponse);
    }
}