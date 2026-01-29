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
}