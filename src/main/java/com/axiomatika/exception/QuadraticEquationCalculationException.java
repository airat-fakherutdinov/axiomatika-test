package com.axiomatika.exception;

public class QuadraticEquationCalculationException extends RuntimeException {

    public QuadraticEquationCalculationException(double discriminant) {
        super(String.format(
                "It is impossible to calculate the quadratic equation. Discriminant less than zero: %s",
                discriminant));
    }
}
