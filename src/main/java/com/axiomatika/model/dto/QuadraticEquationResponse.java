package com.axiomatika.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuadraticEquationResponse {

    private BigDecimal x1;
    private BigDecimal x2;
}
