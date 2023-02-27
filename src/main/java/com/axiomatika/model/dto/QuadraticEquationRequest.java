package com.axiomatika.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuadraticEquationRequest {

    @NotNull
    private BigDecimal a;

    @NotNull
    private BigDecimal b;

    @NotNull
    private BigDecimal c;
}
