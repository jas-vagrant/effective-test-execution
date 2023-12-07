package com.testvagrant.example.Implementation;

import java.text.DecimalFormat;

public abstract class CalculatorImplementation {
    DecimalFormat decimalFormat;
    public double formatResult(double result) {
        decimalFormat = new DecimalFormat("#.###");
        return Double.parseDouble(decimalFormat.format(result));
    }

    public void print(double result){
        double formattedValue = formatResult(result);
        System.out.println("Result : "+formattedValue);
    }
}
