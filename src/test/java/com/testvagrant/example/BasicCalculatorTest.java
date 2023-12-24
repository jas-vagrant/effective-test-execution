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
    public void testAddTwoNumbersSuccess(){
        result = basicCalculator.add(12, 14);
        Assertions.assertEquals(26,result,"The results are not matching");
    }
    @Test
    public void testSubtractTwoNumbersSuccess(){
        result = basicCalculator.subtract(4,2);
        Assertions.assertEquals(2,result,"The results are not matching");
    }
    @Test
    public void testMultiplyTwoNumbersSuccess(){
        result = basicCalculator.multiply(10,4);
        Assertions.assertEquals(40,result,"The results are not matching");
    }
    @Test
    public void testDivideTwoNumbersSuccess(){
        result = basicCalculator.divide(6,3);
        Assertions.assertEquals(2,result,"The results are not matching");
    }
    @Test
    public void testDivideByZeroShouldReturnNaN(){
        result = basicCalculator.divide(8,0);
        Assertions.assertEquals(Double.NaN,result,"The results are not matching");
    }
    @Test
    public void testMultiplyWithInnerTrue(){
        result = basicCalculator.multiplyWithInner(8,0, true);
        Assertions.assertEquals(0,result,"The results are not matching");
    }
    @Test
    public void testMultiplyWithInnerFalse(){
        result = basicCalculator.multiplyWithInner(8,0, false);
        Assertions.assertEquals(0,result,"The results are not matching");
    }
}
