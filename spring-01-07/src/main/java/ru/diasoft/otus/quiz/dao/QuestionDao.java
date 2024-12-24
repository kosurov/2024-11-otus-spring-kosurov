package ru.diasoft.otus.quiz.dao;

import ru.diasoft.otus.quiz.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
