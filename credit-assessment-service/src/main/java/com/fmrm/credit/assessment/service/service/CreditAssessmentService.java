package com.fmrm.credit.assessment.service.service;

import com.fmrm.credit.assessment.service.infra.clients.CardResourceClient;
import com.fmrm.credit.assessment.service.infra.clients.ClientResourceClient;
import com.fmrm.credit.assessment.service.model.CardClient;
import com.fmrm.credit.assessment.service.model.ClientData;
import com.fmrm.credit.assessment.service.model.ClientStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAssessmentService {

    private final ClientResourceClient clientsClient;
    private final CardResourceClient cardsClient;

    public CreditAssessmentService(ClientResourceClient clientsClient ,CardResourceClient cardsClient) {
        this.clientsClient = clientsClient;
        this.cardsClient = cardsClient;
    }

    public  ClientStatus getStatus(String cpf) {
        ResponseEntity<ClientData> responseEntityClient = clientsClient.clientData(cpf);
        ResponseEntity<List<CardClient>> responseEntityCards = cardsClient.getCardsByClient(cpf);

        return ClientStatus.builder()
                .clientData(responseEntityClient.getBody())
                .cards(responseEntityCards.getBody())
                .build();
    }
}
