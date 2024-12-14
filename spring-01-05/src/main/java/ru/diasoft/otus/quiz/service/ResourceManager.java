package ru.diasoft.otus.quiz.service;

import java.util.Locale;

public interface ResourceManager {

    String getQuestions();

    String getAnswers();

    Locale getLocale();

    void setLocale(Locale locale);
}
