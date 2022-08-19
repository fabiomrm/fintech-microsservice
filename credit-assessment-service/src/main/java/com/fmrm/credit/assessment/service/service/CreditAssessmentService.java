package com.fmrm.credit.assessment.service.service;

import com.fmrm.credit.assessment.service.exception.ClientDataNotFoundException;
import com.fmrm.credit.assessment.service.exception.MicroserviceCommunicationException;
import com.fmrm.credit.assessment.service.infra.clients.CardResourceClient;
import com.fmrm.credit.assessment.service.infra.clients.ClientResourceClient;
import com.fmrm.credit.assessment.service.model.*;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CreditAssessmentService {

    private final ClientResourceClient clientsClient;
    private final CardResourceClient cardsClient;

    public CreditAssessmentService(ClientResourceClient clientsClient ,CardResourceClient cardsClient) {
        this.clientsClient = clientsClient;
        this.cardsClient = cardsClient;
    }

    public  ClientStatus getStatus(String cpf)
            throws ClientDataNotFoundException, MicroserviceCommunicationException {
        try {

        ResponseEntity<ClientData> responseEntityClient = clientsClient.clientData(cpf);
        ResponseEntity<List<CardClient>> responseEntityCards = cardsClient.getCardsByClient(cpf);

        return ClientStatus.builder()
                .clientData(responseEntityClient.getBody())
                .cards(responseEntityCards.getBody())
                .build();
        }catch(FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    public ClientEvaluationReturn evaluate(String cpf, Long income) throws ClientDataNotFoundException,
            MicroserviceCommunicationException {

        try {
            ResponseEntity<ClientData> responseEntityClient = clientsClient.clientData(cpf);
            ResponseEntity<List<Card>> responseEntityCards = cardsClient.getCardsByIncome(income);

            List<Card> cards = responseEntityCards.getBody();
            List<ApprovedCard> approvedCards = cards.stream().map(card -> {
                BigDecimal baseLimit = card.getBaseLimit();
                BigDecimal ageBD = BigDecimal.valueOf(Objects.requireNonNull(responseEntityClient.getBody()).getAge());
                BigDecimal factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(baseLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setName(card.getName());
                approvedCard.setCardFlag(card.getCardFlag());
                approvedCard.setLimitApproved(approvedLimit);

                return approvedCard;
            }).collect(Collectors.toList());

            return new ClientEvaluationReturn(approvedCards);

        }catch(FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }

    }
}
