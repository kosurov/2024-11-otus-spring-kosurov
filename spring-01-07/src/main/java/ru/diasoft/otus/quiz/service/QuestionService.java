package ru.diasoft.otus.quiz.service;

import ru.diasoft.otus.quiz.dto.QuestionWithAnswers;

import java.util.List;

public interface QuestionService {

    List<QuestionWithAnswers> getQuestionsWithAnswers();
}
