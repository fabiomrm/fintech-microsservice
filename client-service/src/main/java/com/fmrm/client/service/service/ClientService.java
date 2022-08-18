package com.fmrm.client.service.service;

import com.fmrm.client.service.domain.Client;
import com.fmrm.client.service.infra.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;
    
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    @Transactional(readOnly = true)
    public Optional<Client> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }


}
