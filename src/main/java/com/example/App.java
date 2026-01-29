package com.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Calculator calc = new Calculator();

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.valueOf(calc.calculate(10, 5, "add-again")));
        }
    }
}
