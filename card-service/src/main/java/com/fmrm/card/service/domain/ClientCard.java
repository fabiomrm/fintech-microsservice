package com.fmrm.card.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ClientCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private BigDecimal cardLimit;
    @ManyToOne
    @JoinColumn(name="id_card")
    private Card card;

}
