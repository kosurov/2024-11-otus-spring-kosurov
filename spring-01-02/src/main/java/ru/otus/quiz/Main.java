package ru.otus.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.QuestionService;

import java.util.List;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        printQuestionsWithAnswers(questionsWithAnswers);
    }

    private static void printQuestionsWithAnswers(List<QuestionWithAnswers> questionsWithAnswers) {
        for (QuestionWithAnswers question : questionsWithAnswers) {
            System.out.println(question);
        }
    }
}
