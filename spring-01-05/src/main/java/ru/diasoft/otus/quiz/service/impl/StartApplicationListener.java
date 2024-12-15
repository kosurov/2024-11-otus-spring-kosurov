package ru.diasoft.otus.quiz.service.impl;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.diasoft.otus.quiz.service.LocaleDefiner;
import ru.diasoft.otus.quiz.service.QuizService;

@Component
public class StartApplicationListener {

    private final QuizService quizService;
    private final LocaleDefiner localeDefiner;


    public StartApplicationListener(QuizService quizService,
                                    LocaleDefiner localeDefiner) {
        this.quizService = quizService;
        this.localeDefiner = localeDefiner;
    }

    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        localeDefiner.defineLocale();
        quizService.start();
    }
}
