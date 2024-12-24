package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.dao.AnswerDao;
import ru.diasoft.otus.quiz.dao.QuestionDao;
import ru.diasoft.otus.quiz.domain.Answer;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.dto.QuestionWithAnswers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl questionService;
    @Mock
    private AnswerDao answerDaoMock;
    @Mock
    private QuestionDao questionDaoMock;

    @DisplayName("Возвращает вопросы с вариантами ответов")
    @Test
    void getQuestionsWithAnswers_shouldReturnQuestionsWithAnswers() {
        Answer answer1 = new Answer(1, 1, "answer1");
        Answer answer2 = new Answer(2, 1, "answer2");
        Answer answer3 = new Answer(3, 1, "answer3");

        Question question1 = new Question(1, "question1", 1);

        when(answerDaoMock.findAllByQuestionId(question1.getId()))
                .thenReturn(List.of(answer1, answer2, answer3));
        when(questionDaoMock.findAll()).thenReturn(List.of(question1));

        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        assertNotNull(questionsWithAnswers);
        assertFalse(questionsWithAnswers.isEmpty());
        assertNotNull(questionsWithAnswers.get(0).getAnswers());
        assertEquals(3, questionsWithAnswers.get(0).getAnswers().size());
    }

    @DisplayName("Возвращает вопросы без вариантов ответа")
    @Test
    void getQuestionsWithAnswers_shouldReturnQuestionsWithEmptyAnswers() {
        Question question1 = new Question(1, "question1", 1);

        when(answerDaoMock.findAllByQuestionId(question1.getId()))
                .thenReturn(Collections.emptyList());
        when(questionDaoMock.findAll()).thenReturn(List.of(question1));

        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        assertNotNull(questionsWithAnswers);
        assertFalse(questionsWithAnswers.isEmpty());
        assertNotNull(questionsWithAnswers.get(0).getAnswers());
        assertTrue(questionsWithAnswers.get(0).getAnswers().isEmpty());
    }
}
