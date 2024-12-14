package ru.diasoft.otus.quiz.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.config.ResourceProperties;
import ru.diasoft.otus.quiz.service.ResourceManager;

import java.util.Locale;

@Service
public class ResourceManagerImpl implements ResourceManager {

    private final ResourceProperties resourceProperties;
    private final String defaultLanguageTag;
    private Locale locale;

    public ResourceManagerImpl(ResourceProperties resourceProperties,
                               @Value("${resource.default-locale:en-EN}")
                               String defaultLanguageTag) {
        this.resourceProperties = resourceProperties;
        this.defaultLanguageTag = defaultLanguageTag;
    }

    @Override
    public String getQuestions() {
        return resourceProperties.getLocalizedQuestions().get(getLocale());
    }

    @Override
    public String getAnswers() {
        return resourceProperties.getLocalizedAnswers().get(getLocale());
    }

    @Override
    public Locale getLocale() {
        if (locale == null) {
            locale = Locale.forLanguageTag(defaultLanguageTag);
        }
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
