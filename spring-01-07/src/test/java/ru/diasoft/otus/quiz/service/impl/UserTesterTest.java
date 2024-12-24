package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.diasoft.otus.quiz.service.InputOutputService;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс UserTester")
@ExtendWith(MockitoExtension.class)
class UserTesterTest {

    @InjectMocks
    private UserTester userTester;
    @Mock
    private InputOutputService inputOutputService;
    @Mock
    private MessageService messageService;
    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @DisplayName("Спрашивает имя пользователя")
    @Test()
    void greetUser_askUserFirstName() {
        String expected = "Please enter your first name:";
        when(messageService.getMessage(anyString())).thenReturn(expected);

        userTester.greetUser();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Спрашивает фамилию пользователя")
    @Test()
    void greetUser_askUserLastName() {
        String expected = "Please enter your last name:";
        when(messageService.getMessage(anyString())).thenReturn(expected);

        userTester.greetUser();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Выводит приветственное сообщение")
    @Test()
    void greetUser_displaysWelcomeMessage() {
        String expected = "Welcome to Quiz Service 2000!";
        when(messageService.getMessage(anyString())).thenReturn(expected);

        userTester.greetUser();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Приветствует пользователя")
    @Test()
    void greetUser() {
        String expected = "Nice to meet you Ivan Ivanov!";
        when(inputOutputService.readString()).thenReturn("Ivan", "Ivanov");
        when(messageService.getMessage(anyString())).thenReturn(expected);

        userTester.greetUser();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Спрашивает готовность пользователя")
    @Test
    void askForReadiness() {
        String expected = "Are you ready to start the quiz? (yes/no)";
        when(messageService.getMessage("out.yes")).thenReturn("yes");
        when(messageService.getMessage("out.no")).thenReturn("no");
        when(messageService.getMessage("out.ready.to.start")).thenReturn(expected);
        when(inputOutputService.readString()).thenReturn( "yes");

        userTester.askForReadiness();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Уточняет варианты ответа о готовности, до тех пор, пока пользователь введет корректные данные")
    @Test
    void askForReadiness_whenIncorrectInput_clarifyAnswerForReadiness() {
        String expected = "Please enter 'yes' or 'no'";
        when(inputOutputService.readString()).thenReturn("incorrect", "yes");
        when(messageService.getMessage("out.yes")).thenReturn("yes");
        when(messageService.getMessage("out.no")).thenReturn("no");
        when(messageService.getMessage("out.ready.to.start")).thenReturn("string");
        when(messageService.getMessage("out.yes.or.no")).thenReturn(expected);

        userTester.askForReadiness();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Возвращает true если пользователь готов")
    @Test
    void askForReadiness_whenUserIsReady_returnsTrue() {
        when(messageService.getMessage("out.yes")).thenReturn("yes");
        when(messageService.getMessage("out.no")).thenReturn("no");
        when(messageService.getMessage("out.ready.to.start")).thenReturn("string");
        when(inputOutputService.readString()).thenReturn( "yes");

        boolean result = userTester.askForReadiness();

        assertTrue(result);
    }

    @DisplayName("Возвращает false если пользователь не готов")
    @Test
    void askForReadiness_whenUserIsNotReady_returnsFalse() {
        when(messageService.getMessage("out.yes")).thenReturn("yes");
        when(messageService.getMessage("out.no")).thenReturn("no");
        when(messageService.getMessage("out.ready.to.start")).thenReturn("string");
        when(inputOutputService.readString()).thenReturn("no");

        boolean result = userTester.askForReadiness();

        assertFalse(result);
    }

    @DisplayName("Выводит вопрос пользователю")
    @Test
    void ask_printQuestion() {
        String expected = "question";
        userTester.ask(expected, Collections.emptyList());
        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Увеличивает количество заданых вопросов")
    @Test
    void ask_incrementQuestionsCount() {
        String expected = "question";
        int questionsCountBefore = userTester.getQuestionsCount();

        userTester.ask(expected, Collections.emptyList());

        int questionsCountAfter = userTester.getQuestionsCount();
        assertEquals( questionsCountBefore + 1, questionsCountAfter);
    }

    @DisplayName("Возвращает ответ пользователя")
    @Test
    void ask_returnsUserAnswer() {
        String expected = "user answer";
        when(inputOutputService.readString()).thenReturn(expected);
        String result = userTester.ask(expected, Collections.emptyList());
        assertEquals(expected, result);
    }

    @DisplayName("Увеличивает количество правильных ответов")
    @Test
    void incrementRightAnswersCount() {
        int correctAnswersBefore = userTester.getCorrectAnswers();
        userTester.incrementRightAnswersCount();
        int correctAnswersAfter = userTester.getCorrectAnswers();
        assertEquals(correctAnswersBefore + 1, correctAnswersAfter);
    }

    @DisplayName("Возвращает количество правильных ответов")
    @Test
    void getCorrectAnswers() {
        ReflectionTestUtils.setField(userTester, "correctAnswers", 7);
        int result = userTester.getCorrectAnswers();
        assertEquals(7, result);
    }

    @DisplayName("Возвращает количество заданных вопросов")
    @Test
    void getQuestionsCount() {
        ReflectionTestUtils.setField(userTester, "questionsCount", 10);
        int result = userTester.getQuestionsCount();
        assertEquals(10, result);
    }

    @DisplayName("Печатает неуспешный результат")
    @Test
    void printFaultResult() {
        String expected = "The quiz has been failed, please try again later.";
        when(messageService.getMessage(anyString(), any())).thenReturn(expected);

        userTester.printFaultResult();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(s -> s.contains(expected)));
    }

    @DisplayName("Печатает успешный результат")
    @Test
    void printSuccessResult() {
        String expected = "You have successfully completed the quiz!";
        when(messageService.getMessage(anyString(), any())).thenReturn(expected);

        userTester.printSuccessResult();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(s -> s.contains(expected)));
    }

    @DisplayName("Печатает внутреннюю ошибку сервиса")
    @Test
    void printInternalError() {
        String message = "Error occurred while running program: " + "Error";
        when(messageService.getMessage(anyString(), any())).thenReturn(message);

        userTester.printInternalError("Error");

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(s -> s.equals(message)));
    }
}
