package ru.diasoft.otus.quiz.dao.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.dao.QuestionDao;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.service.ObjectLoader;
import ru.diasoft.otus.quiz.service.ResourceManager;

import java.util.List;

@Service
public class QuestionDaoImpl implements QuestionDao {

    private final ObjectLoader objectLoader;
    private final ResourceManager resourceManager;

    public QuestionDaoImpl(ObjectLoader objectLoader, ResourceManager resourceManager) {
        this.objectLoader = objectLoader;
        this.resourceManager = resourceManager;
    }

    @Override
    public List<Question> findAll() {
        return objectLoader.loadObjectList(Question.class, resourceManager.getQuestions());
    }
}
