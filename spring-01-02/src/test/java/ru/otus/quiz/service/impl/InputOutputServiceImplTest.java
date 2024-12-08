package ru.otus.quiz.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс InputOutputServiceImpl")
class InputOutputServiceImplTest {

    private InputOutputServiceImpl inputOutputService;
    private final String inputString = "Hello!";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStreamCaptor));
        inputOutputService = new InputOutputServiceImpl();
    }

    @AfterEach
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @DisplayName("Печатает строку")
    @Test
    void out_printString() {
        String expected = "Printed string";
        inputOutputService.out(expected);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @DisplayName("Читает строку")
    @Test
    void readString() {
        String result = inputOutputService.readString();
        assertEquals(inputString, result);
    }
}
