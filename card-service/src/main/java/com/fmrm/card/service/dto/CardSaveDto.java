package com.fmrm.card.service.dto;

import com.fmrm.card.service.domain.Card;
import com.fmrm.card.service.enums.CardFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardSaveDto {
    private String name;
    private CardFlag cardFlag;
    private BigDecimal income;
    private BigDecimal baseLimit;

    public Card toModel() {
        return new Card(name, cardFlag, income, baseLimit);
    }


}
