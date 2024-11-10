package ru.otus.quiz.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.service.ObjectLoader;

import java.util.Collections;
import java.util.List;

class AnswerDaoImplTest {

    @Test
    void findAllByQuestionId_whenEmptyObjectList_shouldReturnEmptyList() {
        String resource = "resource.xml";
        ObjectLoader objectLoaderMock = Mockito.mock(ObjectLoader.class);
        AnswerDaoImpl answerDao = new AnswerDaoImpl(objectLoaderMock, resource);
        Mockito.when(objectLoaderMock.loadObjectList(Answer.class, resource))
                .thenReturn(Collections.emptyList());

        List<Answer> answers = answerDao.findAllByQuestionId(1L);
        Assertions.assertNotNull(answers);
        Assertions.assertTrue(answers.isEmpty());
    }

    @Test
    void findAllByQuestionId_whenNotEmptyObjectList_shouldFilterResultByQuestionId() {
        String resource = "resource.xml";
        ObjectLoader objectLoaderMock = Mockito.mock(ObjectLoader.class);
        AnswerDaoImpl answerDao = new AnswerDaoImpl(objectLoaderMock, resource);

        int validQuestionId = 1;
        int invalidQuestionId = 2;

        Answer answer1 = new Answer(1, validQuestionId, "answer1");
        Answer answer2 = new Answer(2, validQuestionId, "answer2");
        Answer answer3 = new Answer(3, invalidQuestionId, "answer3");

        Mockito.when(objectLoaderMock.loadObjectList(Answer.class, resource))
                .thenReturn(List.of(answer1, answer2, answer3));

        List<Answer> answers = answerDao.findAllByQuestionId(1L);
        Assertions.assertNotNull(answers);
        Assertions.assertEquals(2, answers.size());
    }

}
