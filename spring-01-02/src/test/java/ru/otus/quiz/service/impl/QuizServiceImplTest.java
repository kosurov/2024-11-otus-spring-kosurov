package ru.otus.quiz.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.ConsoleTesterFactory;
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
        ConsoleTesterFactory testerFactory = mock(ConsoleTesterFactoryImpl.class);
        ConsoleTester consoleTester = mock(ConsoleTester.class);
        QuestionService questionService = mock(QuestionService.class);
        QuizServiceImpl quizService = new QuizServiceImpl(questionService, testerFactory, 60);

        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");

        Question question = new Question(1, "question1", 1);

        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(question, List.of(answer1, answer2, answer3));

        when(testerFactory.getTester()).thenReturn(consoleTester);
        when(consoleTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.singletonList(questionWithAnswers));
        when(consoleTester.ask(eq(question.getQuestion()), Mockito.any())).thenReturn(answer1.getAnswer());
        when(consoleTester.getCorrectAnswersPercent()).thenReturn(100);

        quizService.start();

        verify(consoleTester).incrementRightAnswersCount();
        verify(consoleTester).printSuccessResult();
    }

    @Test
    void test_faultResult() {
        ConsoleTesterFactory testerFactory = mock(ConsoleTesterFactoryImpl.class);
        ConsoleTester consoleTester = mock(ConsoleTester.class);
        QuestionService questionService = mock(QuestionService.class);
        QuizServiceImpl quizService = new QuizServiceImpl(questionService, testerFactory, 60);

        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");

        Question question = new Question(1, "question1", 1);

        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(question, List.of(answer1, answer2, answer3));

        when(testerFactory.getTester()).thenReturn(consoleTester);
        when(consoleTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.singletonList(questionWithAnswers));
        when(consoleTester.ask(eq(question.getQuestion()), Mockito.any())).thenReturn(answer2.getAnswer());
        when(consoleTester.getCorrectAnswersPercent()).thenReturn(0);

        quizService.start();

        verify(consoleTester, never()).incrementRightAnswersCount();
        verify(consoleTester).printFaultResult();
    }
}
