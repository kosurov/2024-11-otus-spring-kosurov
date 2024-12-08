package ru.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс CsvObjectLoader")
class CsvObjectLoaderTest {

    @DisplayName("Возвращает пустой список если возникла ошибка при чтении файла")
    @Test
    void loadObjectList_whenIncorrectFile_returnsEmptyList() {
        CsvObjectLoader loader = new CsvObjectLoader();
        List<Answer> result = loader.loadObjectList(Answer.class, "incorrect.csv");
        assertTrue(result.isEmpty());
    }

    @DisplayName("Возвращает пустой список файла с пустым списком ответов")
    @Test
    void loadObjectList_whenEmptyFile_returnsEmptyList() {
        CsvObjectLoader loader = new CsvObjectLoader();
        List<Answer> result = loader.loadObjectList(Answer.class, "empty.csv");
        assertTrue(result.isEmpty());
    }

    @DisplayName("Загружает объекты типа Answer из файла")
    @Test
    void loadObjectList_returnsAnswers() {
        CsvObjectLoader loader = new CsvObjectLoader();
        List<Answer> result = loader.loadObjectList(Answer.class, "answers-test.csv");
        assertNotNull(result.get(0));
    }

    @DisplayName("Загружает объекты типа Question из файла")
    @Test
    void loadObjectList_returnsQuestions() {
        CsvObjectLoader loader = new CsvObjectLoader();
        List<Question> result = loader.loadObjectList(Question.class, "questions-test.csv");
        assertNotNull(result.get(0));
    }

    @DisplayName("Осуществляет корректный маппинг объектов типа Answer из файла")
    @Test
    void loadObjectList_correctAnswerMapping() {
        int expectedId = 1;
        int expectedQuestionId = 1;
        String expectedAnswer = "Nile";
        CsvObjectLoader loader = new CsvObjectLoader();

        List<Answer> result = loader.loadObjectList(Answer.class, "answers-test.csv");
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
        CsvObjectLoader loader = new CsvObjectLoader();

        List<Question> result = loader.loadObjectList(Question.class, "questions-test.csv");
        Question question = result.get(0);

        assertEquals(expectedId, question.getId());
        assertEquals(expectedQuestion, question.getQuestion());
        assertEquals(expectedCorrectAnswerId, question.getCorrectAnswerId());
    }
}
