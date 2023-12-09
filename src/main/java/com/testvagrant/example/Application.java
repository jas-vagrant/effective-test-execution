package com.testvagrant.example;
import com.testvagrant.example.Implementation.BasicCalculator;
import com.testvagrant.example.Implementation.CalculatorImplementation;
import com.testvagrant.example.Implementation.ScientificCalculator;
import com.testvagrant.example.factory.CalculatorType;

import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);
    Double result;

    public static void main(String[] args) {
        Application application = new Application();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Select operation:");
            System.out.println("1. Basic Calculator");
            System.out.println("2. Scientific Calculator");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            choice = scanner.nextInt();
            if(choice == 3){
                System.out.println();
            }
            CalculatorImplementation instance = CalculatorType.from(choice).getInstance();

            if (instance instanceof BasicCalculator) {
                application.performBasicCalculations((BasicCalculator) instance);
            } else if (instance instanceof ScientificCalculator) {
                application.performScientificCalculations((ScientificCalculator) instance);
            }

        } while (choice != 3);
        scanner.close();
    }

    private void performBasicCalculations(BasicCalculator basicCalc) {

        int operationChoice = 0;

        System.out.println("Choose operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Enter operation choice (1-4): ");

        operationChoice = scanner.nextInt();

        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        switch (operationChoice) {
            case 1:
                result = basicCalc.add(num1, num2);
                basicCalc.print(result);
                break;
            case 2:
                result = basicCalc.subtract(num1, num2);
                basicCalc.print(result);
                break;
            case 3:
                result = basicCalc.multiply(num1, num2);
                basicCalc.print(result);
                break;
            case 4:
                result = basicCalc.divide(num1, num2);
                basicCalc.print(result);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void performScientificCalculations(ScientificCalculator scientificCalc) {
        int operationChoice = 0;

        System.out.println("Choose operation:");
        System.out.println("1. Sin");
        System.out.println("2. Cos");
        System.out.println("3. Tan");

        System.out.print("Enter operation choice (1-3): ");
        operationChoice = scanner.nextInt();

        System.out.print("Enter angle in degrees: ");
        double angle = 0;

        while (true) {
            if (scanner.hasNextDouble()) {
                angle = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid angle.");
                scanner.next();
            }
        }

        switch (operationChoice) {
            case 1:
                result = scientificCalc.sin(angle);
                scientificCalc.print(result);
                break;
            case 2:
                result = scientificCalc.cos(angle);
                scientificCalc.print(result);
                break;
            case 3:
                result = scientificCalc.tan(angle);
                scientificCalc.print(result);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}