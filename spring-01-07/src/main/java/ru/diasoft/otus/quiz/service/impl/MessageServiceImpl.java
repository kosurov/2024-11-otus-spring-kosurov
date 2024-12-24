package ru.diasoft.otus.quiz.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.service.LocaleHolder;
import ru.diasoft.otus.quiz.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final LocaleHolder localeHolder;

    public MessageServiceImpl(MessageSource messageSource,
                              LocaleHolder localeHolder) {
        this.messageSource = messageSource;
        this.localeHolder = localeHolder;
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, localeHolder.getLocale());
    }
}
