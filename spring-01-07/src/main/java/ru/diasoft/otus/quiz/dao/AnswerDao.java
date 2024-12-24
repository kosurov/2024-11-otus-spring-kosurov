package ru.diasoft.otus.quiz.dao;


import ru.diasoft.otus.quiz.domain.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> findAllByQuestionId(long questionId);
}
