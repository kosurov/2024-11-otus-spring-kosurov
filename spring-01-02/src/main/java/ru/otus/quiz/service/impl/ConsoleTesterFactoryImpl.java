package ru.otus.quiz.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.quiz.service.ConsoleTesterFactory;

import java.util.Scanner;

@Component
public class ConsoleTesterFactoryImpl implements ConsoleTesterFactory {

    @Override
    public ConsoleTester getTester() {
        return new ConsoleTester(new Scanner(System.in));
    }
}
