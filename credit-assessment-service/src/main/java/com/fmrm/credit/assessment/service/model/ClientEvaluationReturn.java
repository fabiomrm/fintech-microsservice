package com.fmrm.credit.assessment.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientEvaluationReturn {
    private List<ApprovedCard> cards;
}
