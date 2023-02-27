package com.axiomatika.service;

import com.axiomatika.model.dto.QuadraticEquationRequest;
import com.axiomatika.model.dto.QuadraticEquationResponse;

public interface CalculationService {

    QuadraticEquationResponse calculateQuadraticEquation(QuadraticEquationRequest request);
}
