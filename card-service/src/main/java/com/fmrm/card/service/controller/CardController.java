package com.fmrm.card.service.controller;

import com.fmrm.card.service.domain.Card;
import com.fmrm.card.service.dto.CardSaveDto;
import com.fmrm.card.service.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody CardSaveDto cardSaveDto) {
        Card card = cardSaveDto.toModel();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsByIncome(@RequestParam("income") Long income) {
        List<Card> cardsBasedOnIncome = service.getCardsBasedOnIncome(income);
        return ResponseEntity.ok().body(cardsBasedOnIncome);
    }
}
