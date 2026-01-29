package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test void add() { assertEquals(15, calc.calculate(10, 5, "add")); }
    @Test void addAgainOp() { assertEquals(15, calc.calculate(10, 5, "add-again")); }
    @Test void sub() { assertEquals(5, calc.calculate(10, 5, "sub")); }
    @Test void subAgainOp() { assertEquals(5, calc.calculate(10, 5, "sub-again")); }
    @Test void mul() { assertEquals(50, calc.calculate(10, 5, "mul")); }
    @Test void divNonZero() { assertEquals(2, calc.calculate(10, 5, "div")); }
    @Test void divByZeroReturnsZero() { assertEquals(0, calc.calculate(10, 0, "div")); }
    @Test void mod() { assertEquals(0, calc.calculate(10, 5, "mod")); }
    @Test void pow() { assertEquals(1000, calc.calculate(10, 3, "pow")); }
    @Test void unknownOpReturnsZero() { assertEquals(0, calc.calculate(10, 5, "unknown")); }

    @Test void addAgainMethod() { assertEquals(15, calc.addAgain(10, 5)); }

    // Additional test cases for better coverage
    @Test void addWithZero() { assertEquals(10, calc.calculate(10, 0, "add")); }
    @Test void addWithNegativeNumbers() { assertEquals(-5, calc.calculate(10, -15, "add")); }
    @Test void subWithZero() { assertEquals(10, calc.calculate(10, 0, "sub")); }
    @Test void subWithNegativeNumbers() { assertEquals(25, calc.calculate(10, -15, "sub")); }
    @Test void mulByZero() { assertEquals(0, calc.calculate(10, 0, "mul")); }
    @Test void mulByOne() { assertEquals(10, calc.calculate(10, 1, "mul")); }
    @Test void mulWithNegativeNumbers() { assertEquals(-50, calc.calculate(10, -5, "mul")); }
    @Test void divWithNegativeNumbers() { assertEquals(-2, calc.calculate(10, -5, "div")); }
    @Test void divByOne() { assertEquals(10, calc.calculate(10, 1, "div")); }
    @Test void modWithZeroRemainder() { assertEquals(0, calc.calculate(10, 5, "mod")); }
    @Test void modWithRemainder() { assertEquals(3, calc.calculate(10, 7, "mod")); }
    @Test void powWithZeroExponent() { assertEquals(1, calc.calculate(10, 0, "pow")); }
    @Test void powWithOneExponent() { assertEquals(10, calc.calculate(10, 1, "pow")); }
    @Test void powWithLargeExponent() { assertEquals(10000, calc.calculate(10, 4, "pow")); }
    @Test void powWithNegativeNumbers() { assertEquals(1, calc.calculate(-10, 0, "pow")); }
    @Test void addAgainMethodWithNegatives() { assertEquals(-5, calc.addAgain(10, -15)); }
    @Test void addAgainMethodWithZero() { assertEquals(10, calc.addAgain(10, 0)); }
}