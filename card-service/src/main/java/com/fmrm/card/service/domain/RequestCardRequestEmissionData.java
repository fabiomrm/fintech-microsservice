package com.fmrm.card.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCardRequestEmissionData {

    private Long idCard;
    private String cpf;
    private String deliverAddress;
    private BigDecimal limitAllowed;
}