package com.fmrm.credit.assessment.service.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {
    private String name;
    private String cardFlag;
    private BigDecimal limitApproved;

}
