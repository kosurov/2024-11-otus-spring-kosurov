package ru.otus.quiz.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.quiz.dao.AnswerDao;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.dto.QuestionWithAnswers;

import java.util.Collections;
import java.util.List;

class QuestionServiceImplTest {

    @Test
    void getQuestionsWithAnswers_shouldReturnQuestionsWithAnswers() {
        AnswerDao answerDaoMock = Mockito.mock(AnswerDao.class);
        QuestionDao questionDaoMock = Mockito.mock(QuestionDao.class);
        QuestionServiceImpl questionService = new QuestionServiceImpl(questionDaoMock, answerDaoMock);

        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");

        Question question1 = new Question(1, "question1");

        Mockito.when(answerDaoMock.findAllByQuestionId(question1.getId()))
                .thenReturn(List.of(answer1, answer2, answer3));
        Mockito.when(questionDaoMock.findAll()).thenReturn(List.of(question1));

        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        Assertions.assertNotNull(questionsWithAnswers);
        Assertions.assertFalse(questionsWithAnswers.isEmpty());
        Assertions.assertNotNull(questionsWithAnswers.get(0).getAnswers());
        Assertions.assertEquals(3, questionsWithAnswers.get(0).getAnswers().size());
    }

    @Test
    void getQuestionsWithAnswers_shouldReturnQuestionsWithEmptyAnswers() {
        AnswerDao answerDaoMock = Mockito.mock(AnswerDao.class);
        QuestionDao questionDaoMock = Mockito.mock(QuestionDao.class);
        QuestionServiceImpl questionService = new QuestionServiceImpl(questionDaoMock, answerDaoMock);

        Question question1 = new Question(1, "question1");

        Mockito.when(answerDaoMock.findAllByQuestionId(question1.getId()))
                .thenReturn(Collections.emptyList());
        Mockito.when(questionDaoMock.findAll()).thenReturn(List.of(question1));

        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        Assertions.assertNotNull(questionsWithAnswers);
        Assertions.assertFalse(questionsWithAnswers.isEmpty());
        Assertions.assertNotNull(questionsWithAnswers.get(0).getAnswers());
        Assertions.assertTrue(questionsWithAnswers.get(0).getAnswers().isEmpty());
    }
}
