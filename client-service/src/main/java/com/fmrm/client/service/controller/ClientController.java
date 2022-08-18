package com.fmrm.client.service.controller;


import com.fmrm.client.service.domain.Client;
import com.fmrm.client.service.dto.ClientSaveDTO;
import com.fmrm.client.service.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }
    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientSaveDTO clientSaveDTO) {

        Client client = service.save(clientSaveDTO.toModel());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(params="cpf")
    public ResponseEntity<Client> clientData(@RequestParam("cpf") String cpf) {
        Optional<Client> client = service.getByCpf(cpf);

        if(client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client.get());
    }



}
