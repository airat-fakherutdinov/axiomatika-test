package com.axiomatika.controller;

import com.axiomatika.model.dto.QuadraticEquationRequest;
import com.axiomatika.model.dto.QuadraticEquationResponse;
import com.axiomatika.service.CalculationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calculate")
public class CalculationController {

    private final CalculationService service;

    @PostMapping("/quadratic-equation")
    public QuadraticEquationResponse calculateQuadraticEquation(@Valid @RequestBody QuadraticEquationRequest request) {
        return service.calculateQuadraticEquation(request);
    }
}
