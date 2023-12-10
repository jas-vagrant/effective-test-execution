package com.testvagrant.example;

import com.testvagrant.example.implementation.ScientificCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScientificCalculatorTest {
    ScientificCalculator scientificCalculator;
    Double result;
    @BeforeEach
    public void initialization(){
        scientificCalculator = new ScientificCalculator();
    }
    @Test
    public void shouldTestSinFunction(){
        result = scientificCalculator.sin(90);
        result = scientificCalculator.formatResult(result);
        Assertions.assertEquals(1,result,"The results are not matching");
    }
    @Test
    public void shouldTestCosFunction(){
        result = scientificCalculator.cos(0);
        Assertions.assertEquals(1, scientificCalculator.formatResult(result),"The results are not matching");
    }
    @Test
    public void shouldTestTanFunction(){
        result = scientificCalculator.tan(90);
        Assertions.assertEquals(1.633123935319537E16,result,"The results are not matching");
    }
}