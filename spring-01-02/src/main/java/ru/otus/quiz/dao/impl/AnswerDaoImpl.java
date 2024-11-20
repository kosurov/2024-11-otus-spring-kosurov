package ru.otus.quiz.dao.impl;

import ru.otus.quiz.dao.AnswerDao;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.service.ObjectLoader;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerDaoImpl implements AnswerDao {

    private final ObjectLoader objectLoader;
    private final String resource;

    public AnswerDaoImpl(ObjectLoader objectLoader, String resource) {
        this.objectLoader = objectLoader;
        this.resource = resource;
    }

    @Override
    public List<Answer> findAllByQuestionId(long questionId) {
        return objectLoader.loadObjectList(Answer.class, this.resource).stream()
                .filter(answer -> answer.getQuestionId() == questionId)
                .collect(Collectors.toList());
    }
}
