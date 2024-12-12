package ru.diasoft.otus.quiz.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.dao.QuestionDao;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.service.ObjectLoader;

import java.util.List;

@Service
public class QuestionDaoImpl implements QuestionDao {

    private final ObjectLoader objectLoader;
    private final String resource;

    public QuestionDaoImpl(ObjectLoader objectLoader, @Value("${quiz.questions}") String resource) {
        this.objectLoader = objectLoader;
        this.resource = resource;
    }

    @Override
    public List<Question> findAll() {
        return objectLoader.loadObjectList(Question.class, this.resource);
    }
}
