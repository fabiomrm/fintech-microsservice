package com.fmrm.credit.assessment.service.model;

import lombok.Data;

@Data
public class ClientData {
    private Long id;
    private String name;
    private String cpf;
    private Integer age;
}
