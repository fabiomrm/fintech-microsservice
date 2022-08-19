package com.fmrm.card.service.domain;

import com.fmrm.card.service.enums.CardFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardFlag cardFlag;
    private BigDecimal income;
    private BigDecimal baseLimit;
    

    public Card(String name, CardFlag cardFlag, BigDecimal income, BigDecimal baseLimit) {
        this.name = name;
        this.cardFlag = cardFlag;
        this.income = income;
        this.baseLimit = baseLimit;
    }
}
