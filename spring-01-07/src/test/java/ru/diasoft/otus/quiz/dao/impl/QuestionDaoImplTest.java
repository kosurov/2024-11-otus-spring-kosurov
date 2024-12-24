package ru.diasoft.otus.quiz.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.domain.Question;
import ru.diasoft.otus.quiz.service.ObjectLoader;
import ru.diasoft.otus.quiz.service.ResourceManager;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionDaoImpl")
@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {

    @InjectMocks
    private QuestionDaoImpl questionDao;
    @Mock
    private ObjectLoader objectLoader;
    @Mock
    private ResourceManager resourceManager;

    @DisplayName("Возвращает пустой список если ответы не были загружены из файла")
    @Test
    void findAll_whenEmptyObjectList_shouldReturnEmptyList() {
        String resource = "resource.xml";
        when(resourceManager.getQuestions()).thenReturn(resource);
        when(objectLoader.loadObjectList(Question.class, resource))
                .thenReturn(Collections.emptyList());

        List<Question> questions = questionDao.findAll();
        assertTrue(questions.isEmpty());
    }

    @DisplayName("Возвращает список ответов")
    @Test
    void findAll_whenNotEmptyObjectList_shouldReturnNotEmptyList() {
        String resource = "resource.xml";
        when(resourceManager.getQuestions()).thenReturn(resource);
        Question question1 = new Question(1, "question1", 1);
        Question question2 = new Question(2, "question2", 1);
        Question question3 = new Question(3, "question3", 1);
        when(objectLoader.loadObjectList(Question.class, resource))
                .thenReturn(List.of(question1, question2, question3));

        List<Question> questions = questionDao.findAll();
        assertFalse(questions.isEmpty());
    }
}
