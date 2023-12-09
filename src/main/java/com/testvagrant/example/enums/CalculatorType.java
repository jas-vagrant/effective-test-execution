package com.testvagrant.example.enums;

import com.testvagrant.example.implementation.BasicCalculator;
import com.testvagrant.example.implementation.CalculatorImplementation;
import com.testvagrant.example.implementation.ScientificCalculator;

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