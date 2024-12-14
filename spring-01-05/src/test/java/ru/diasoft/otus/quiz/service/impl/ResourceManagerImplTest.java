package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.config.ResourceProperties;
import ru.diasoft.otus.quiz.service.LocaleHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Класс ResourceManagerImpl")
@ExtendWith(MockitoExtension.class)
class ResourceManagerImplTest {

    @InjectMocks
    private ResourceManagerImpl resourceManager;
    @Mock
    private ResourceProperties resourceProperties;
    @Mock
    private LocaleHolder localeHolder;

    @DisplayName("Возвращает вопросы локали ru-RU")
    @Test
    void getQuestions_returnsRuQuestions() {
        Map<Locale, String> questions = new HashMap<>();
        questions.put(Locale.forLanguageTag("ru-RU"), "questions-ru");
        questions.put(Locale.forLanguageTag("en-US"), "questions-en");
        when(resourceProperties.getLocalizedQuestions()).thenReturn(questions);
        when(localeHolder.getLocale()).thenReturn(Locale.forLanguageTag("ru-RU"));

        assertEquals("questions-ru", resourceManager.getQuestions());
    }

    @DisplayName("Возвращает вопросы локали en-EN")
    @Test
    void getQuestions_returnsEnQuestions() {
        Map<Locale, String> questions = new HashMap<>();
        questions.put(Locale.forLanguageTag("ru-RU"), "questions-ru");
        questions.put(Locale.forLanguageTag("en-US"), "questions-en");
        when(resourceProperties.getLocalizedQuestions()).thenReturn(questions);
        when(localeHolder.getLocale()).thenReturn(Locale.forLanguageTag("en-US"));

        assertEquals("questions-en", resourceManager.getQuestions());
    }

    @DisplayName("Возвращает ответы локали ru-RU")
    @Test
    void getAnswers_returnsRuAnswers() {
        Map<Locale, String> answers = new HashMap<>();
        answers.put(Locale.forLanguageTag("ru-RU"), "answers-ru");
        answers.put(Locale.forLanguageTag("en-US"), "answers-en");
        when(resourceProperties.getLocalizedAnswers()).thenReturn(answers);
        when(localeHolder.getLocale()).thenReturn(Locale.forLanguageTag("ru-RU"));

        assertEquals("answers-ru", resourceManager.getAnswers());
    }

    @DisplayName("Возвращает ответы локали en-US")
    @Test
    void getAnswers_returnsEnAnswers() {
        Map<Locale, String> answers = new HashMap<>();
        answers.put(Locale.forLanguageTag("ru-RU"), "answers-ru");
        answers.put(Locale.forLanguageTag("en-US"), "answers-en");
        when(resourceProperties.getLocalizedAnswers()).thenReturn(answers);
        when(localeHolder.getLocale()).thenReturn(Locale.forLanguageTag("en-US"));

        assertEquals("answers-en", resourceManager.getAnswers());
    }
}
