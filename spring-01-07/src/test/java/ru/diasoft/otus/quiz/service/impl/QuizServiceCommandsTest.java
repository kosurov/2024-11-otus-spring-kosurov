package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.exception.UnsupportedLanguageException;
import ru.diasoft.otus.quiz.service.LocaleDefiner;
import ru.diasoft.otus.quiz.service.MessageService;
import ru.diasoft.otus.quiz.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuizServiceCommands")
@ExtendWith(MockitoExtension.class)
class QuizServiceCommandsTest {

    @InjectMocks
    private QuizServiceCommands quizServiceCommands;
    @Mock
    private QuizService quizService;
    @Mock
    private LocaleDefiner localeDefiner;
    @Mock
    private MessageService messageService;

    @DisplayName("Возвращает информацию о выбранном языке")
    @Test
    void setLanguage_returnsLanguageInfo() {
        when(messageService.getMessage("out.language.have.chosen"))
                .thenReturn("Выбран язык Ru");
        String result = quizServiceCommands.setLanguage("ru");
        assertEquals("Выбран язык Ru", result);
    }

    @DisplayName("Устанавливает локаль викторины")
    @Test
    void setLanguage_setQuizLocale() {
        quizServiceCommands.setLanguage("ru");
        verify(localeDefiner, times(1)).defineLocale("ru");
    }

    @DisplayName("Запускает викторину")
    @Test
    void startQuizService_startQuiz() {
        quizServiceCommands.startQuizService();
        verify(quizService, times(1)).start();
    }

    @DisplayName("Возвращает сообщение об ошибке с переносом строки")
    @Test
    void errorHandler_returnsErrorMessageWithLineBreak() {
        String result = quizServiceCommands.errorHandler(new UnsupportedLanguageException("unsupported language"));
        assertEquals("unsupported language\n", result);
    }
}
