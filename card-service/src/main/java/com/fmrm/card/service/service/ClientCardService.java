package com.fmrm.card.service.service;

import com.fmrm.card.service.domain.ClientCard;
import com.fmrm.card.service.infra.repository.ClientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCardService {

    private final ClientCardRepository repository;

    public ClientCardService(ClientCardRepository repository) {
        this.repository = repository;
    }

    public List<ClientCard> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
