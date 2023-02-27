package com.axiomatika.repository;

import com.axiomatika.model.entity.QuadraticEquationResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface QuadraticEquationResultRepository extends JpaRepository<QuadraticEquationResult, Long> {

    List<QuadraticEquationResult> findAllByAAndBAndC(BigDecimal a, BigDecimal b, BigDecimal c);
}
