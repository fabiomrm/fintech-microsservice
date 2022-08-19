package com.fmrm.credit.assessment.service.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private Long id;
    private String name;
    private String cardFlag;
    private BigDecimal baseLimit;
}
