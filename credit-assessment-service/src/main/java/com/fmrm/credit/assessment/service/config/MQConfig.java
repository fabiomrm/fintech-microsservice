package com.fmrm.credit.assessment.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.cards-emission}")
    private String cardsEmissionQueue;

    @Bean
    public Queue emissionCardQueue() {
        return new Queue(cardsEmissionQueue, true);
    }
}
