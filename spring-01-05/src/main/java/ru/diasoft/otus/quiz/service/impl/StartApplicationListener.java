package ru.diasoft.otus.quiz.service.impl;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.diasoft.otus.quiz.service.QuizService;

@Component
public class StartApplicationListener {

    private final QuizService quizService;

    public StartApplicationListener(QuizService quizService) {
        this.quizService = quizService;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        quizService.start();
    }
}
