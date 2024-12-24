package ru.diasoft.otus.quiz.service;

import java.util.List;

public interface ObjectLoader {

    <T> List<T> loadObjectList(Class<T> type, String fileName);
}
