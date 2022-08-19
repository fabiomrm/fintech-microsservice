package com.fmrm.credit.assessment.service.controller;

import com.fmrm.credit.assessment.service.exception.ClientDataNotFoundException;
import com.fmrm.credit.assessment.service.exception.MicroserviceCommunicationException;
import com.fmrm.credit.assessment.service.model.AssessmentData;
import com.fmrm.credit.assessment.service.model.ClientEvaluationReturn;
import com.fmrm.credit.assessment.service.model.ClientStatus;
import com.fmrm.credit.assessment.service.service.CreditAssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ClientStatus status = null;
        try{
            status = service.getStatus(cpf);
            return ResponseEntity.ok().body(status);
        }catch(ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientEvaluationReturn>evaluate(@RequestBody AssessmentData data) {
        try{
            ClientEvaluationReturn clientEvaluationReturn = service.evaluate(data.getCpf(), data.getIncome());
            return ResponseEntity.ok().body(clientEvaluationReturn);
        }catch(ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
