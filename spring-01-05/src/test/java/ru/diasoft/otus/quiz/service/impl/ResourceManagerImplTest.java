package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.diasoft.otus.quiz.config.ResourceProperties;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс ResourceManagerImpl")
class ResourceManagerImplTest {

    private ResourceManagerImpl resourceManager;
    private String defaultLanguageTag;
    private ResourceProperties resourceProperties;

    @BeforeEach
    void setUp() {
        defaultLanguageTag = "en-EN";
        resourceProperties = Mockito.mock(ResourceProperties.class);
        resourceManager = new ResourceManagerImpl(resourceProperties, defaultLanguageTag);
    }

    @DisplayName("Возвращает вопросы локали ru-RU")
    @Test
    void getQuestions_returnsRuQuestions() {
        Map<Locale, String> questions = new HashMap<>();
        questions.put(Locale.forLanguageTag("ru-RU"), "questions-ru");
        questions.put(Locale.forLanguageTag("en-EN"), "questions-en");
        Mockito.when(resourceProperties.getLocalizedQuestions()).thenReturn(questions);

        resourceManager.setLocale(Locale.forLanguageTag("ru-RU"));

        assertEquals("questions-ru", resourceManager.getQuestions());
    }

    @DisplayName("Возвращает вопросы локали en-EN")
    @Test
    void getQuestions_returnsEnQuestions() {
        Map<Locale, String> questions = new HashMap<>();
        questions.put(Locale.forLanguageTag("ru-RU"), "questions-ru");
        questions.put(Locale.forLanguageTag("en-EN"), "questions-en");
        Mockito.when(resourceProperties.getLocalizedQuestions()).thenReturn(questions);

        resourceManager.setLocale(Locale.forLanguageTag("en-EN"));

        assertEquals("questions-en", resourceManager.getQuestions());
    }

    @DisplayName("Возвращает вопросы локали ru-RU")
    @Test
    void getAnswers_returnsRuQuestions() {
        Map<Locale, String> answers = new HashMap<>();
        answers.put(Locale.forLanguageTag("ru-RU"), "answers-ru");
        answers.put(Locale.forLanguageTag("en-EN"), "answers-en");
        Mockito.when(resourceProperties.getLocalizedAnswers()).thenReturn(answers);

        resourceManager.setLocale(Locale.forLanguageTag("ru-RU"));

        assertEquals("answers-ru", resourceManager.getAnswers());
    }

    @DisplayName("Возвращает вопросы локали en-EN")
    @Test
    void getAnswers_returnsEnQuestions() {
        Map<Locale, String> answers = new HashMap<>();
        answers.put(Locale.forLanguageTag("ru-RU"), "answers-ru");
        answers.put(Locale.forLanguageTag("en-EN"), "answers-en");
        Mockito.when(resourceProperties.getLocalizedAnswers()).thenReturn(answers);

        resourceManager.setLocale(Locale.forLanguageTag("en-EN"));

        assertEquals("answers-en", resourceManager.getAnswers());
    }

    @DisplayName("Возвращает локаль по умолчанию")
    @Test
    void getLocale_returnsDefaultLocale() {
        Locale defaultLocale = Locale.forLanguageTag(defaultLanguageTag);
        assertEquals(defaultLocale, resourceManager.getLocale());
    }

    @DisplayName("Возвращает локаль")
    @Test
    void getLocale_returnsLocale() {
        assertNotNull(resourceManager.getLocale());
    }

    @DisplayName("Устанавливает новую локаль")
    @Test
    void setLocale_setNewLocale() {
        resourceManager.setLocale(Locale.UK);
        Locale localeFirst = resourceManager.getLocale();
        resourceManager.setLocale(Locale.US);
        Locale localeSecond = resourceManager.getLocale();
        assertNotEquals(localeFirst, localeSecond);
    }
}
