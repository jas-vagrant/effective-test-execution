package com.testvagrant.example;

import com.testvagrant.example.enums.CalculatorType;
import com.testvagrant.example.implementation.BasicCalculator;
import com.testvagrant.example.implementation.CalculatorImplementation;
import com.testvagrant.example.implementation.ScientificCalculator;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  Scanner scanner = new Scanner(System.in);
  private static final Logger log = LoggerFactory.getLogger(Application.class);
  Double result;

  public static void main(String[] args) {
    Application application = new Application();
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      log.info("Select an option:");
      log.info("1. Basic Calculator");
      log.info("2. Scientific Calculator");
      log.info("3. Exit");
      log.info("Enter your choice (1-3): ");

      choice = scanner.nextInt();
      if (choice == 3) {
        log.info("The user chose to exit");
        break;
      }
      CalculatorImplementation instance = CalculatorType
        .from(choice)
        .getInstance();

      if (instance instanceof BasicCalculator) {
        application.performBasicCalculations((BasicCalculator) instance);
      } else if (instance instanceof ScientificCalculator) {
        application.performScientificCalculations(
          (ScientificCalculator) instance
        );
      }
    } while (choice != 3);
    scanner.close();
  }

  private void performBasicCalculations(BasicCalculator basicCalc) {
    int operationChoice = 0;

    log.info("Choose operation:");
    log.info("1. Addition");
    log.info("2. Subtraction");
    log.info("3. Multiplication");
    log.info("4. Division");

    log.info("Enter operation choice (1-4): ");

    operationChoice = scanner.nextInt();

    log.info("Enter first number: ");
    double num1 = scanner.nextDouble();
    log.info("Enter second number: ");
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
        log.info("Invalid choice!");
    }
  }

  private void performScientificCalculations(
    ScientificCalculator scientificCalc
  ) {
    int operationChoice = 0;

    log.info("Choose operation:");
    log.info("1. Sin");
    log.info("2. Cos");
    log.info("3. Tan");

    log.info("Enter operation choice (1-3): ");

    operationChoice = scanner.nextInt();

    log.info("Enter angle in degrees: ");
    double angle = 0;

    while (true) {
      if (scanner.hasNextDouble()) {
        angle = scanner.nextDouble();
        break;
      } else {
        log.info("Invalid input! Please enter a valid angle.");
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
        log.info("Invalid choice!");
    }
  }
}
