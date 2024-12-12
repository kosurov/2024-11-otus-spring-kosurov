package ru.diasoft.otus.quiz.service.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.dao.AnswerDao;
import ru.diasoft.otus.quiz.dao.QuestionDao;
import ru.diasoft.otus.quiz.domain.Answer;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.dto.QuestionWithAnswers;
import ru.diasoft.otus.quiz.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
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
