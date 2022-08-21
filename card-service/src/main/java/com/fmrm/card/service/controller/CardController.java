package com.fmrm.card.service.controller;

import com.fmrm.card.service.domain.Card;
import com.fmrm.card.service.domain.ClientCard;
import com.fmrm.card.service.dto.CardSaveDto;
import com.fmrm.card.service.dto.ClientCardResponseDTO;
import com.fmrm.card.service.service.CardService;
import com.fmrm.card.service.service.ClientCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
public class CardController {

    private final CardService service;
    private final ClientCardService clientCardService;

    public CardController(CardService service, ClientCardService clientCardService) {
        this.service = service;
        this.clientCardService = clientCardService;
    }

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody CardSaveDto cardSaveDto) {
        Card card = cardSaveDto.toModel();
        card = service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsByIncome(@RequestParam("income") Long income) {
        List<Card> cardsBasedOnIncome = service.getCardsBasedOnIncome(income);
        return ResponseEntity.ok().body(cardsBasedOnIncome);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardResponseDTO>> getCardsByClient(@RequestParam("cpf") String cpf) {
        List<ClientCard> clientCards = clientCardService.listCardsByCpf(cpf);
        List<ClientCardResponseDTO> response = clientCards.stream()
                .map(ClientCardResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
}
