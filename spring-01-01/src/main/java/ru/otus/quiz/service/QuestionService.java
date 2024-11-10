package ru.otus.quiz.service;

import ru.otus.quiz.dto.QuestionWithAnswers;

import java.util.List;

public interface QuestionService {

    List<QuestionWithAnswers> getQuestionsWithAnswers();
}
