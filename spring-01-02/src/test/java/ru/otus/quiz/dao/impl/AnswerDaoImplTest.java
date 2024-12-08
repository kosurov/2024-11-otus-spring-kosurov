package ru.otus.quiz.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.service.ObjectLoader;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Класс AnswerDaoImpl")
@ExtendWith(MockitoExtension.class)
class AnswerDaoImplTest {

    @Mock
    private ObjectLoader objectLoader;

    @DisplayName("Возвращает пустой список если ответы не были загружены из файла")
    @Test
    void findAllByQuestionId_whenEmptyObjectList_shouldReturnEmptyList() {
        String resource = "resource.xml";
        AnswerDaoImpl answerDao = new AnswerDaoImpl(objectLoader, resource);
        when(objectLoader.loadObjectList(Answer.class, resource))
                .thenReturn(Collections.emptyList());

        List<Answer> answers = answerDao.findAllByQuestionId(1L);
        assertTrue(answers.isEmpty());
    }

    @DisplayName("Возвращает список из двух ответов, отфильтровав их по questionId")
    @Test
    void findAllByQuestionId_whenNotEmptyObjectList_shouldFilterResultByQuestionId() {
        String resource = "resource.xml";
        AnswerDaoImpl answerDao = new AnswerDaoImpl(objectLoader, resource);
        int validQuestionId = 1;
        int invalidQuestionId = 2;
        Answer answer1 = new Answer(1, validQuestionId, "answer1");
        Answer answer2 = new Answer(2, validQuestionId, "answer2");
        Answer answer3 = new Answer(3, invalidQuestionId, "answer3");

        when(objectLoader.loadObjectList(Answer.class, resource))
                .thenReturn(List.of(answer1, answer2, answer3));

        List<Answer> answers = answerDao.findAllByQuestionId(1L);
        assertEquals(2, answers.size());
    }
}
