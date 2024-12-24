package ru.diasoft.otus.quiz.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.diasoft.otus.quiz.dto.ResourceProperty;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс ResourceProperties")
class ResourcePropertiesTest {

    private ResourceProperties resourceProperties;

    @BeforeEach
    void setUp() {
        resourceProperties = new ResourceProperties();
        resourceProperties.getQuestions().add(new ResourceProperty("ru-RU", "question-ru"));
        resourceProperties.getQuestions().add(new ResourceProperty("en-EN", "question-en"));
        resourceProperties.getAnswers().add(new ResourceProperty("ru-RU", "answer-RU"));
        resourceProperties.getAnswers().add(new ResourceProperty("en-EN", "answer-en"));
    }

    @DisplayName("Возвращает map соответствия локали и файла вопросов")
    @Test
    void getLocalizedQuestions_returnsLocalizedQuestions() {
        assertNotNull(resourceProperties.getLocalizedQuestions());
    }

    @DisplayName("Возвращает map, которая содержит файл вопросов для локали ru-RU")
    @Test
    void getLocalizedQuestions_returnsLocalizedQuestionsRuLocale() {
        Locale locale = Locale.forLanguageTag("ru-RU");
        String result = resourceProperties.getLocalizedQuestions().get(locale);
        assertNotNull(result);
    }

    @DisplayName("Возвращает map, которая содержит файл вопросов для локали en-EN")
    @Test
    void getLocalizedQuestions_returnsLocalizedQuestionsEnLocale() {
        Locale locale = Locale.forLanguageTag("en-EN");
        String result = resourceProperties.getLocalizedQuestions().get(locale);
        assertNotNull(result);
    }

    @DisplayName("Возвращает map соответствия локали и файла ответов")
    @Test
    void getLocalizedAnswers_returnsLocalizedAnswers() {
        assertNotNull(resourceProperties.getLocalizedAnswers());
    }

    @DisplayName("Возвращает map, которая содержит файл ответов для локали ru-RU")
    @Test
    void getLocalizedQuestions_returnsLocalizedAnswersRuLocale() {
        Locale locale = Locale.forLanguageTag("ru-RU");
        String result = resourceProperties.getLocalizedQuestions().get(locale);
        assertNotNull(result);
    }

    @DisplayName("Возвращает map, которая содержит файл ответов для локали en-EN")
    @Test
    void getLocalizedQuestions_returnsLocalizedAnswersEnLocale() {
        Locale locale = Locale.forLanguageTag("en-EN");
        String result = resourceProperties.getLocalizedQuestions().get(locale);
        assertNotNull(result);
    }

    @DisplayName("Возвращает список property вопросов")
    @Test
    void getQuestions_returnsQuestions() {
        assertNotNull(resourceProperties.getQuestions());
    }

    @DisplayName("Возвращает список property ответов")
    @Test
    void getAnswers_returnsAnswers() {
        assertNotNull(resourceProperties.getAnswers());
    }

    @DisplayName("Возвращает не пустой список property вопросов")
    @Test
    void getQuestions_returnsNotEmptyQuestions() {
        assertFalse(resourceProperties.getQuestions().isEmpty());
    }

    @DisplayName("Возвращает не пустой список property ответов")
    @Test
    void getAnswers_returnsNotEmptyAnswers() {
        assertFalse(resourceProperties.getAnswers().isEmpty());
    }
}
