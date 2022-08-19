package com.fmrm.card.service.dto;

import com.fmrm.card.service.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardResponseDTO {
    private String name;
    private String cardFlag;
    private BigDecimal limitAllowed;

    public static ClientCardResponseDTO fromModel(ClientCard model) {
        return new ClientCardResponseDTO(
                model.getCard().getName(),
                model.getCard().getCardFlag().toString(),
                model.getCardLimit()
        );
    }

}
