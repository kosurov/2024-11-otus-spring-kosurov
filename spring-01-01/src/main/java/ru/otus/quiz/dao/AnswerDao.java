package ru.otus.quiz.dao;

import ru.otus.quiz.domain.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> findAllByQuestionId(long questionId);
}
