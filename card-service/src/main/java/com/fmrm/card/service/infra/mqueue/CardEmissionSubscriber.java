package com.fmrm.card.service.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmrm.card.service.domain.Card;
import com.fmrm.card.service.domain.ClientCard;
import com.fmrm.card.service.domain.RequestCardRequestEmissionData;
import com.fmrm.card.service.infra.repository.CardRepository;
import com.fmrm.card.service.infra.repository.ClientCardRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardEmissionSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    public CardEmissionSubscriber(CardRepository cardRepository, ClientCardRepository clientCardRepository) {
        this.cardRepository = cardRepository;
        this.clientCardRepository = clientCardRepository;
    }

    @RabbitListener(queues = "${mq.queues.cards-emission}")
    public void receiveCardEmissionSolicitation(@Payload String payload) {
        var mapper = new ObjectMapper();
        try {
            RequestCardRequestEmissionData data = mapper.readValue(payload, RequestCardRequestEmissionData.class);
            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();

            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(data.getCpf());
            clientCard.setCardLimit(data.getLimitAllowed());

            clientCardRepository.save(clientCard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
