package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.domain.Answer;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.dto.QuestionWithAnswers;
import ru.diasoft.otus.quiz.service.MessageService;
import ru.diasoft.otus.quiz.service.QuestionService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuizServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

    private QuizServiceImpl quizService;
    @Mock
    private UserTester userTester;
    @Mock
    private QuestionService questionService;
    @Mock
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        quizService = new QuizServiceImpl(questionService, userTester, 60, messageService);
    }

    @DisplayName("Приветствует пользователя")
    @Test
    void start_shouldGreetUser() {
        quizService.start();
        verify(userTester).greetUser();
    }

    @DisplayName("Завершает работу, если пользователь не готов")
    @Test
    void start_whenUserNotReady_shouldStop() {
        when(userTester.askForReadiness()).thenReturn(false);
        quizService.start();
        verify(questionService, never()).getQuestionsWithAnswers();
    }

    @DisplayName("Продолжает работу, если пользователь готов")
    @Test
    void start_whenUserReady_shouldContinue() {
        when(userTester.askForReadiness()).thenReturn(true);
        quizService.start();
        verify(questionService).getQuestionsWithAnswers();
    }

    @DisplayName("Выводит ошибку, если список вопросов пустой")
    @Test
    void start_whenEmptyQuestions_shouldPrintInternalError() {
        when(userTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.emptyList());
        when(messageService.getMessage(anyString())).thenReturn("Question database is empty");

        quizService.start();
        verify(userTester).printInternalError("Question database is empty");
    }

    @DisplayName("Задает вопрос")
    @Test
    void start_askQuestions() {
        mockSuccessParameters();
        quizService.start();
        verify(userTester).ask(any(), any());
    }

    @DisplayName("Печатает успешный результат")
    @Test
    void start_whenSuccessResult_printSuccessResult() {
        mockSuccessParameters();
        quizService.start();
        verify(userTester).printSuccessResult();
    }

    @DisplayName("Увеличивает количество правильных ответов если есть верный ответ пользователя")
    @Test
    void start_whenHasCorrectAnswers_incrementRightAnswersCount() {
        mockSuccessParameters();
        quizService.start();
        verify(userTester).incrementRightAnswersCount();
    }

    @DisplayName("Печатает неуспешный результат")
    @Test
    void start_whenFaultResult_printFaultResult() {
        mockFaultParameters();
        quizService.start();
        verify(userTester).printFaultResult();
    }

    @DisplayName("Не увеличивает количество правильных ответов")
    @Test
    void start_whenHasCorrectAnswers_notIncrementRightAnswersCount() {
        mockFaultParameters();
        quizService.start();
        verify(userTester, never()).incrementRightAnswersCount();
    }

    private void mockSuccessParameters() {
        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");
        Question question = new Question(1, "question1", 1);
        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(question, List.of(answer1, answer2, answer3));

        when(userTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.singletonList(questionWithAnswers));
        when(userTester.ask(eq(question.getQuestion()), Mockito.any())).thenReturn(answer1.getAnswer());
        when(userTester.getQuestionsCount()).thenReturn(1);
        when(userTester.getCorrectAnswers()).thenReturn(1);
    }

    private void mockFaultParameters() {
        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");
        Question question = new Question(1, "question1", 1);
        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(question, List.of(answer1, answer2, answer3));

        when(userTester.askForReadiness()).thenReturn(true);
        when(questionService.getQuestionsWithAnswers()).thenReturn(Collections.singletonList(questionWithAnswers));
        when(userTester.ask(eq(question.getQuestion()), Mockito.any())).thenReturn(answer2.getAnswer());
        when(userTester.getQuestionsCount()).thenReturn(1);
        when(userTester.getCorrectAnswers()).thenReturn(0);
    }
}
