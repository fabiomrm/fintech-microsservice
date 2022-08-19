package com.fmrm.credit.assessment.service.model;

import lombok.Data;

import java.util.List;

@Data
public class ClientStatus {
    private ClientData clientData;
    private List<CardClient> cards;

}
