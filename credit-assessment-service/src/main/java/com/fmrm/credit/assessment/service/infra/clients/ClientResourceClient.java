package com.fmrm.credit.assessment.service.infra.clients;

import com.fmrm.credit.assessment.service.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-service", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params="cpf")
    ResponseEntity<ClientData> clientData(@RequestParam("cpf") String cpf);
}
