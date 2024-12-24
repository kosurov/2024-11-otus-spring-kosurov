package ru.diasoft.otus.quiz.dao.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.dao.AnswerDao;
import ru.diasoft.otus.quiz.domain.Answer;
import ru.diasoft.otus.quiz.service.ObjectLoader;
import ru.diasoft.otus.quiz.service.ResourceManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerDaoImpl implements AnswerDao {

    private final ObjectLoader objectLoader;
    private final ResourceManager resourceManager;

    public AnswerDaoImpl(ObjectLoader objectLoader, ResourceManager resourceManager) {
        this.objectLoader = objectLoader;
        this.resourceManager = resourceManager;
    }

    @Override
    public List<Answer> findAllByQuestionId(long questionId) {
        return objectLoader.loadObjectList(Answer.class, resourceManager.getAnswers()).stream()
                .filter(answer -> answer.getQuestionId() == questionId)
                .collect(Collectors.toList());
    }
}
