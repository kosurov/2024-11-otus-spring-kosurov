package ru.diasoft.otus.quiz.service.impl;

import org.springframework.stereotype.Component;
import ru.diasoft.otus.quiz.exception.UnsupportedLanguageException;
import ru.diasoft.otus.quiz.service.InputOutputService;
import ru.diasoft.otus.quiz.service.LocaleDefiner;
import ru.diasoft.otus.quiz.service.LocaleHolder;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class LocaleDefinerImpl implements LocaleDefiner {

    private final LocaleHolder localeHolder;
    private final MessageService messageService;

    public LocaleDefinerImpl(LocaleHolder localeHolder,
                             MessageService messageService) {
        this.localeHolder = localeHolder;
        this.messageService = messageService;
    }

    @Override
    public void defineLocale(String language) {
        Map<String, Locale> locales = new HashMap<>();
        locales.put("ru", Locale.forLanguageTag("ru-RU"));
        locales.put("en", Locale.US);

        Locale locale = locales.get(language);
        if (locale == null) {
            throw new UnsupportedLanguageException(messageService.getMessage("out.supported.languages"));
        }
        localeHolder.setLocale(locales.get(language));
    }
}
