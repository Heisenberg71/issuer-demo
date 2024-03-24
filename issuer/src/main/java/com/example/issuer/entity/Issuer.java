package com.example.issuer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Issuer {
    private String bankId;
    private String bankName;
    private String dskl;
    private String dekt;
}
