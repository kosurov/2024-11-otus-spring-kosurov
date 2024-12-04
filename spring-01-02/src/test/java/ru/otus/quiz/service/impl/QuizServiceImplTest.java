package ru.otus.quiz.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.QuestionService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuizServiceImplTest {

    @Test
    void test_successResult() {
        UserTester userTester = mock(UserTester.class);
        QuestionService questionService = mock(QuestionService.class);
        QuizServiceImpl quizService = new QuizServiceImpl(questionService, userTester, 60);

        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");

        Question question = new Question(1, "question1", 1);

        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(question, List.of(answer1, answer2, answer3));

        when(userTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.singletonList(questionWithAnswers));
        when(userTester.ask(eq(question.getQuestion()), Mockito.any())).thenReturn(answer1.getAnswer());
        when(userTester.getCorrectAnswersPercent()).thenReturn(100);

        quizService.start();

        verify(userTester).incrementRightAnswersCount();
        verify(userTester).printSuccessResult();
    }

    @Test
    void test_faultResult() {
        UserTester userTester = mock(UserTester.class);
        QuestionService questionService = mock(QuestionService.class);
        QuizServiceImpl quizService = new QuizServiceImpl(questionService, userTester, 60);

        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");

        Question question = new Question(1, "question1", 1);

        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(question, List.of(answer1, answer2, answer3));

        when(userTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.singletonList(questionWithAnswers));
        when(userTester.ask(eq(question.getQuestion()), Mockito.any())).thenReturn(answer2.getAnswer());
        when(userTester.getCorrectAnswersPercent()).thenReturn(0);

        quizService.start();

        verify(userTester, never()).incrementRightAnswersCount();
        verify(userTester).printFaultResult();
    }
}
