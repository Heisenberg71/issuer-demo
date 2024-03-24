package com.example.issuer.dao;

import com.example.issuer.IssuerRepository.IssuerRepository;
import com.example.issuer.dto.IssuerRequest;
import com.example.issuer.entity.Issuer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Component
public class IssuerDao {
    @Autowired
    IssuerRepository issuerRepository;
    @Autowired
    RestTemplate restTemplate;

    public Issuer getByBankId(String bankId){
        String url = "http://localhost:8080/testing/issuer";
        Issuer issuer = restTemplate.getForObject(url, Issuer.class);
        return issuer;
    }

    public Issuer getByBankName(String bankName){
        return new Issuer();
    }

    public Issuer createIssuer(IssuerRequest issuerRequest){
        String url = "http://localhost:8080/testing/issuer";
        Issuer issuer = restTemplate.postForObject(url, issuerRequest, Issuer.class);
        return issuer;
    }
}
