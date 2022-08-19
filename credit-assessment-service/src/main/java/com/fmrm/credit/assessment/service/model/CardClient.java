package com.fmrm.credit.assessment.service.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardClient {

    private String name;
    private String cardFlag;
    private BigDecimal limitAllowed;
}
