package com.axiomatika.math;

import com.axiomatika.exception.QuadraticEquationCalculationException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.MathContext.DECIMAL32;

@Component
public class MathCalculator {

    public Pair<BigDecimal, BigDecimal> calculateQuadraticEquation(BigDecimal a, BigDecimal b, BigDecimal c) {
        BigDecimal discriminant = calculateDiscriminant(a, b, c);

        if (discriminant.doubleValue() < 0) {
            throw new QuadraticEquationCalculationException(discriminant.doubleValue());
        }

        return discriminant.doubleValue() == 0 ?
                calculateSingleValue(a, b) :
                calculateDoubleValue(a, b, discriminant);
    }

    private BigDecimal calculateDiscriminant(BigDecimal a, BigDecimal b, BigDecimal c) {
        BigDecimal multiplying = new BigDecimal(4)
                .multiply(a)
                .multiply(c);

        return b.pow(2).subtract(multiplying);
    }

    private Pair<BigDecimal, BigDecimal> calculateSingleValue(BigDecimal a, BigDecimal b) {
        BigDecimal value = calculateQuadraticEquationValue(a, b, 0);
        return Pair.of(value, null);
    }

    private Pair<BigDecimal, BigDecimal> calculateDoubleValue(BigDecimal a, BigDecimal b, BigDecimal discriminant) {
        BigDecimal sqrt = discriminant.sqrt(DECIMAL32);
        BigDecimal value1 = calculateQuadraticEquationValue(a, b, sqrt.doubleValue());
        BigDecimal value2 = calculateQuadraticEquationValue(a, b, -(sqrt.doubleValue()));

        return Pair.of(value1, value2);
    }

    private BigDecimal calculateQuadraticEquationValue(BigDecimal a, BigDecimal b, double sqrtDiscriminant) {
        return BigDecimal.valueOf(-(b.doubleValue()))
                .add(BigDecimal.valueOf(sqrtDiscriminant))
                .divide(new BigDecimal(2).multiply(a), 2, RoundingMode.DOWN);
    }
}
