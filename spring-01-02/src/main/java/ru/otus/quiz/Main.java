package ru.otus.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.QuestionService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
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
