package ru.diasoft.otus.quiz.service.impl;

import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.otus.quiz.exception.UnsupportedLanguageException;
import ru.diasoft.otus.quiz.service.LocaleDefiner;
import ru.diasoft.otus.quiz.service.MessageService;
import ru.diasoft.otus.quiz.service.QuizService;

@ShellComponent
public class QuizServiceCommands {

    private final QuizService quizService;
    private final LocaleDefiner localeDefiner;
    private final MessageService messageService;

    public QuizServiceCommands(QuizService quizService,
                               LocaleDefiner localeDefiner,
                               MessageService messageService) {
        this.quizService = quizService;
        this.localeDefiner = localeDefiner;
        this.messageService = messageService;
    }

    @ShellMethod(key = "quiz-language", value = "Choose language for Quiz Service 2000")
    public String setLanguage(@ShellOption(value = {"-l", "--language"}) String language) {
        localeDefiner.defineLocale(language);
        return messageService.getMessage("out.language.have.chosen");
    }

    @ShellMethod(key = "start", value = "Start Quiz Service 2000")
    public void startQuizService() {
        quizService.start();
    }

    @ExceptionResolver({UnsupportedLanguageException.class})
    public String errorHandler(Exception e) {
        return e.getMessage() + "\n";
    }

}
