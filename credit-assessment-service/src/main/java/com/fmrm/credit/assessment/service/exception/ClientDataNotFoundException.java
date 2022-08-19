package com.fmrm.credit.assessment.service.exception;

public class ClientDataNotFoundException extends RuntimeException {

    public ClientDataNotFoundException() {
        super("Client not found for this CPF");
    }
}
