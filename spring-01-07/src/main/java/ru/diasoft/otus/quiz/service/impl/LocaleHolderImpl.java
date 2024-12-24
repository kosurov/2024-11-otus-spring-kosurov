package ru.diasoft.otus.quiz.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.service.LocaleHolder;

import java.util.Locale;

@Service
public class LocaleHolderImpl implements LocaleHolder {

    private Locale locale;

    @PostConstruct
    public void init() {
        Locale.setDefault(this.locale);
    }

    public LocaleHolderImpl(@Value("${resource.default-locale:en-US}")
                            String defaultLanguageTag) {
        locale = Locale.forLanguageTag(defaultLanguageTag);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        if (locale != null) {
            this.locale = locale;
        }
    }
}
