package com.testvagrant.example.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicCalculator extends CalculatorImplementation {

    private static final Logger LOG = LoggerFactory.getLogger(BasicCalculator.class);

    public double add(double num1, double num2) {
        return num1 + num2;
    }

    public double subtract(double num1, double num2) {
        privateMethod();
        return num1 - num2;
    }

    public double multiply(double num1, double num2) {
        InnerClass.firstInnerMethod();
        return num1 * num2;
    }

    public double multiplyWithInner(double num1, double num2, boolean flag) {
        if (flag) {
            InnerClass.firstInnerMethod();
        } else {
            InnerClass.secondInnerMethod();
        }
        return num1 * num2;
    }

    public double divide(double num1, double num2) {
        try {
            if (num2 == 0) {
                throw new ArithmeticException("Error! Division by zero is not allowed.");
            } else {
                return num1 / num2;
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return Double.NaN;
        }
    }

    private void privateMethod() {
        LOG.info("Inside private method");
    }
}