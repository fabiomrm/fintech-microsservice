package com.fmrm.credit.assessment.service.infra.clients;

import com.fmrm.credit.assessment.service.model.Card;
import com.fmrm.credit.assessment.service.model.CardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "card-service", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClient>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getCardsByIncome(@RequestParam("income") Long income);

}
