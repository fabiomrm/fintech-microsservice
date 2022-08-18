package com.fmrm.card.service.service;

import com.fmrm.card.service.domain.Card;
import com.fmrm.card.service.infra.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Card save(Card card) {
        return repository.save(card);
    }

    @Transactional(readOnly = true)
    public List<Card> getCardsBasedOnIncome(Long income) {
        return repository.findByIncomeLessThanEqual((BigDecimal.valueOf(income)));
    }
}
