package com.fmrm.credit.assessment.service.controller;

import com.fmrm.credit.assessment.service.model.ClientStatus;
import com.fmrm.credit.assessment.service.service.CreditAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-assessments")
public class CreditAssessmentController {

    private final CreditAssessmentService service;

    public CreditAssessmentController(CreditAssessmentService service) {
        this.service = service;
    }

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value="client-status", params="cpf")
    public ResponseEntity<ClientStatus> getClientStatus(@RequestParam("cpf") String cpf) {
        ClientStatus status = service.getStatus(cpf);
        return ResponseEntity.ok().body(status);
    }
}
