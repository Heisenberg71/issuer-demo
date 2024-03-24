package com.example.issuer.controller;

import com.example.issuer.Service.IssuerService;
import com.example.issuer.dto.IssuerRequest;
import com.example.issuer.dto.IssuerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issuer")
public class IssuerController {
    @Autowired
    IssuerService issuerService;
    @GetMapping("")
    IssuerResponse getIssuerByBankIdOrBankName(@RequestParam String bankId, @RequestParam String bankName){
        IssuerRequest issuerRequest = IssuerRequest.builder()
                .bankId(bankId)
                .bankName(bankName)
                .build();
        return issuerService.getIssuerByBankIdOrBankName(issuerRequest);
    }

    @GetMapping("/all")
    IssuerResponse getAllIssuers(){
        return null;
    }

    @PostMapping("")
    IssuerResponse createIssuer(IssuerRequest issuerRequest){
        return issuerService.createIssuer(issuerRequest);
    }

    @PutMapping("")
    void updateIssuer(IssuerRequest issuerRequest){

    }
}
