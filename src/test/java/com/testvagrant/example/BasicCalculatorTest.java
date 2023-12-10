package com.testvagrant.example;

import com.testvagrant.example.implementation.BasicCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicCalculatorTest{
    BasicCalculator basicCalculator;
    double result;

    @BeforeEach
    public void initialization(){
        basicCalculator = new BasicCalculator();
    }
    @Test
    public void shouldAddTwoNumbers(){
        result = basicCalculator.add(12, 14);
        Assertions.assertEquals(26,result,"The results are not matching");
    }
    @Test
    public void shouldSubtractTwoNumbers(){
        result = basicCalculator.subtract(4,2);
        Assertions.assertEquals(2,result,"The results are not matching");
    }
    @Test
    public void shouldMultiplyTwoNumbers(){
        result = basicCalculator.multiply(10,4);
        Assertions.assertEquals(40,result,"The results are not matching");
    }
    @Test
    public void shouldDivideTwoNumbers(){
        result = basicCalculator.divide(6,3);
        Assertions.assertEquals(2,result,"The results are not matching");
    }
    @Test
    public void shouldTestDivideByZero(){
        result = basicCalculator.divide(8,0);
        Assertions.assertEquals(Double.NaN,result,"The results are not matching");
    }
}
