package ru.otus.quiz.service.impl;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.otus.quiz.service.InputOutputService;

import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final Scanner scanner;

    public InputOutputServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @PreDestroy
    public void destroy() {
        this.scanner.close();
    }

    @Override
    public void out(String message) {
        System.out.println(message);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
