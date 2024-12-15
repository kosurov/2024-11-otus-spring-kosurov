package ru.diasoft.otus.quiz.service.impl;

import org.springframework.stereotype.Component;
import ru.diasoft.otus.quiz.service.InputOutputService;
import ru.diasoft.otus.quiz.service.LocaleDefiner;
import ru.diasoft.otus.quiz.service.LocaleHolder;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class LocaleDefinerImpl implements LocaleDefiner {

    private final InputOutputService inputOutputService;
    private final LocaleHolder localeHolder;
    private final MessageService messageService;

    public LocaleDefinerImpl(InputOutputService inputOutputService,
                             LocaleHolder localeHolder,
                             MessageService messageService) {
        this.inputOutputService = inputOutputService;
        this.localeHolder = localeHolder;
        this.messageService = messageService;
    }

    @Override
    public void defineLocale() {
        Map<String, Locale> locales = new HashMap<>();
        locales.put("ru", Locale.forLanguageTag("ru-RU"));
        locales.put("en", Locale.US);

        inputOutputService.out(messageService.getMessage("out.choose.language"));
        String userInput = inputOutputService.readString();
        while (!"en".equals(userInput) && !"ru".equals(userInput)) {
            inputOutputService.out(messageService.getMessage("out.retry.choose.language"));
            userInput = inputOutputService.readString();
        }
        localeHolder.setLocale(locales.get(userInput));
    }
}
