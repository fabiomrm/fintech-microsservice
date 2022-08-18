package com.fmrm.client.service.dto;

import com.fmrm.client.service.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientSaveDTO {

    private String cpf;
    private String name;
    private Integer age;

    public Client toModel() {
        return new Client(cpf, name, age);
    }
}
