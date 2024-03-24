package com.example.issuer.Service;

import com.example.issuer.IssuerRepository.IssuerRepository;
import com.example.issuer.dao.IssuerDao;
import com.example.issuer.dto.IssuerRequest;
import com.example.issuer.dto.IssuerResponse;
import com.example.issuer.entity.Issuer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuerService {
    @Autowired
    IssuerDao issuerDao;
    public IssuerResponse getIssuerByBankIdOrBankName(IssuerRequest issuerRequest){
        IssuerResponse issuerResponse;
        String bankId = issuerRequest.getBankId();
        String bankName = issuerRequest.getBankName();

        if(bankId != null){
            Issuer issuer = issuerDao.getByBankId(bankId);
            issuerResponse = IssuerResponse.builder()
                    .bankId(issuer.getBankId())
                    .bankName(issuer.getBankName())
                    .build();
            return issuerResponse;
        }

        Issuer issuer = issuerDao.getByBankName(bankName);
        issuerResponse = IssuerResponse.builder()
                .bankId(issuer.getBankId())
                .bankName(issuer.getBankName())
                .build();
        return issuerResponse;
    }

    public IssuerResponse createIssuer(IssuerRequest issuerRequest) {
        Issuer issuer = issuerDao.createIssuer(issuerRequest);

        System.out.println(issuer);

        IssuerResponse issuerResponse = IssuerResponse.builder()
                .bankId(issuer.getBankId())
                .bankName(issuer.getBankName())
                .build();

        return issuerResponse;
    }
}
