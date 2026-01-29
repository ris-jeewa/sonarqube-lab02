package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void mainMethodDoesNotThrowException() {
        // Test that the main method executes without throwing an exception
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    @Test
    void mainMethodWithArgs() {
        // Test main method with arguments
        assertDoesNotThrow(() -> App.main(new String[]{"arg1", "arg2"}));
    }
}
