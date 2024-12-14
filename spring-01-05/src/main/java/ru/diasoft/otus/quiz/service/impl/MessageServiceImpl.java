package ru.diasoft.otus.quiz.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.service.MessageService;
import ru.diasoft.otus.quiz.service.ResourceManager;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final ResourceManager resourceManager;

    public MessageServiceImpl(MessageSource messageSource,
                              ResourceManager resourceManager) {
        this.messageSource = messageSource;
        this.resourceManager = resourceManager;
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, resourceManager.getLocale());
    }
}
