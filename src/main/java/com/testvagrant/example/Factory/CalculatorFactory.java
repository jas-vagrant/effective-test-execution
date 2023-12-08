package com.testvagrant.example.Factory;

import com.testvagrant.example.Implementation.BasicCalculator;

import com.testvagrant.example.Implementation.ScientificCalculator;


public class CalculatorFactory {
    public BasicCalculator createBasic(){
        return new BasicCalculator();
    }
    public ScientificCalculator createScientific(){
        return new ScientificCalculator();
    }
}