package com.axiomatika.service.impl;

import com.axiomatika.mapper.QuadraticEquationResultMapper;
import com.axiomatika.math.MathCalculator;
import com.axiomatika.model.dto.QuadraticEquationRequest;
import com.axiomatika.model.dto.QuadraticEquationResponse;
import com.axiomatika.repository.QuadraticEquationResultRepository;
import com.axiomatika.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final MathCalculator calculator;
    private final QuadraticEquationResultMapper quadraticEquationResultMapper;
    private final QuadraticEquationResultRepository quadraticEquationResultRepository;

    @Override
    @Transactional
    public QuadraticEquationResponse calculateQuadraticEquation(QuadraticEquationRequest request) {
        Pair<BigDecimal, BigDecimal> resultPair =
                calculator.calculateQuadraticEquation(request.getA(), request.getB(), request.getC());

        QuadraticEquationResponse response = new QuadraticEquationResponse(resultPair.getLeft(), resultPair.getRight());
        quadraticEquationResultRepository.save(quadraticEquationResultMapper.toEntity(request, response));

        return response;
    }
}
