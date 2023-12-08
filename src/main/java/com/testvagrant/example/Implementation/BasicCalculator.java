package com.testvagrant.example.Implementation;

public class BasicCalculator extends CalculatorImplementation{

        public double add(double num1, double num2) {
            return num1 + num2;
        }

        public double subtract(double num1, double num2) {
            return num1 - num2;
        }

        public double multiply(double num1, double num2) {
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

}
