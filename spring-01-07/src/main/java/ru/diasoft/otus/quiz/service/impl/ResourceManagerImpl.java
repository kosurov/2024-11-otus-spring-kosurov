package ru.diasoft.otus.quiz.service.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.config.ResourceProperties;
import ru.diasoft.otus.quiz.service.LocaleHolder;
import ru.diasoft.otus.quiz.service.ResourceManager;

@Service
public class ResourceManagerImpl implements ResourceManager {

    private final ResourceProperties resourceProperties;
    private final LocaleHolder localeHolder;

    public ResourceManagerImpl(ResourceProperties resourceProperties,
                               LocaleHolder localeHolder) {
        this.resourceProperties = resourceProperties;
        this.localeHolder = localeHolder;
    }

    @Override
    public String getQuestions() {
        return resourceProperties.getLocalizedQuestions().get(localeHolder.getLocale());
    }

    @Override
    public String getAnswers() {
        return resourceProperties.getLocalizedAnswers().get(localeHolder.getLocale());
    }
}
