package com.axiomatika.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "quadratic_equation_result")
public class QuadraticEquationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal a;

    @Column(nullable = false)
    private BigDecimal b;

    @Column(nullable = false)
    private BigDecimal c;

    @Column(nullable = false)
    private BigDecimal x1;

    @Column
    private BigDecimal x2;
}
