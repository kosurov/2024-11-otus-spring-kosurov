package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.domain.Answer;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Класс CsvObjectLoader")
@ExtendWith(MockitoExtension.class)
class CsvObjectLoaderTest {

    @InjectMocks
    private CsvObjectLoader csvObjectLoader;
    @Mock
    private MessageService messageService;

    @DisplayName("Возвращает пустой список если возникла ошибка при чтении файла")
    @Test
    void loadObjectList_whenIncorrectFile_returnsEmptyList() {
        when(messageService.getMessage(anyString(), any())).thenReturn("error");
        List<Answer> result = csvObjectLoader.loadObjectList(Answer.class, "incorrect.csv");
        assertTrue(result.isEmpty());
    }

    @DisplayName("Возвращает пустой список файла с пустым списком ответов")
    @Test
    void loadObjectList_whenEmptyFile_returnsEmptyList() {
        List<Answer> result = csvObjectLoader.loadObjectList(Answer.class, "empty.csv");
        assertTrue(result.isEmpty());
    }

    @DisplayName("Загружает объекты типа Answer из файла")
    @Test
    void loadObjectList_returnsAnswers() {
        List<Answer> result = csvObjectLoader.loadObjectList(Answer.class, "answers-test.csv");
        assertNotNull(result.get(0));
    }

    @DisplayName("Загружает объекты типа Question из файла")
    @Test
    void loadObjectList_returnsQuestions() {
        List<Question> result = csvObjectLoader.loadObjectList(Question.class, "questions-test.csv");
        assertNotNull(result.get(0));
    }

    @DisplayName("Осуществляет корректный маппинг объектов типа Answer из файла")
    @Test
    void loadObjectList_correctAnswerMapping() {
        int expectedId = 1;
        int expectedQuestionId = 1;
        String expectedAnswer = "Nile";

        List<Answer> result = csvObjectLoader.loadObjectList(Answer.class, "answers-test.csv");
        Answer answer = result.get(0);

        assertEquals(expectedId, answer.getId());
        assertEquals(expectedQuestionId, answer.getQuestionId());
        assertEquals(expectedAnswer, answer.getAnswer());
    }

    @DisplayName("Осуществляет корректный маппинг объектов типа Question из файла")
    @Test
    void loadObjectList_correctQuestionMapping() {
        int expectedId = 1;
        String expectedQuestion = "What is the longest river in the world?";
        int expectedCorrectAnswerId = 1;

        List<Question> result = csvObjectLoader.loadObjectList(Question.class, "questions-test.csv");
        Question question = result.get(0);

        assertEquals(expectedId, question.getId());
        assertEquals(expectedQuestion, question.getQuestion());
        assertEquals(expectedCorrectAnswerId, question.getCorrectAnswerId());
    }
}
