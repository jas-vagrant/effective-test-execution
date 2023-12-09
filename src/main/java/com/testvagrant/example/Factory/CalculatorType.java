package com.testvagrant.example.factory;

import com.testvagrant.example.Implementation.BasicCalculator;
import com.testvagrant.example.Implementation.CalculatorImplementation;
import com.testvagrant.example.Implementation.ScientificCalculator;

import java.util.Arrays;

public enum CalculatorType {
    BASIC(1, new BasicCalculator()),
    SCIENTIFIC(2, new ScientificCalculator());

    private final CalculatorImplementation instance;
    private final int value;

    CalculatorType(int value, CalculatorImplementation instance) {
        this.instance = instance;
        this.value = value;
    }

    public static CalculatorType from(int choice){
        return Arrays.stream(CalculatorType.values())
                .filter(type -> type.value == choice)
                .findFirst()
                .orElse(null);
    }
    public  CalculatorImplementation getInstance() {
        return instance;
    }
}