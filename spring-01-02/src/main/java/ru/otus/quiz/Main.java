package ru.otus.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.quiz.service.impl.QuizServiceImpl;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuizServiceImpl testService = context.getBean(QuizServiceImpl.class);
        testService.start();
    }
}
