package ru.otus.quiz.service.impl;

import ru.otus.quiz.dao.AnswerDao;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public QuestionServiceImpl(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public List<QuestionWithAnswers> getQuestionsWithAnswers() {
        List<QuestionWithAnswers> result = new ArrayList<>();
        List<Question> questions = questionDao.findAll();
        for (Question question : questions) {
            List<Answer> answers = answerDao.findAllByQuestionId(question.getId());
            result.add(new QuestionWithAnswers(question, answers));
        }
        return result;
    }
}
