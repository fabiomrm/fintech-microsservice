package com.fmrm.credit.assessment.service.infra.clients.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmrm.credit.assessment.service.model.RequestCardRequestEmissionData;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CardsEmissionSolicitationPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue emissionCardQueue;

    public CardsEmissionSolicitationPublisher(RabbitTemplate rabbitTemplate, Queue emissionCardQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.emissionCardQueue = emissionCardQueue;
    }

    public void requestCard(RequestCardRequestEmissionData requestCardRequestEmissionData)
            throws JsonProcessingException {
        String jsonString = convertIntoJSON(requestCardRequestEmissionData);

        rabbitTemplate.convertAndSend(emissionCardQueue.getName(), jsonString);
    }

    private String convertIntoJSON(RequestCardRequestEmissionData requestCardRequestEmissionData)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(requestCardRequestEmissionData);
    }
}
