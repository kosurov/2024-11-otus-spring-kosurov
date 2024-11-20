package ru.otus.quiz.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.service.ObjectLoader;

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
