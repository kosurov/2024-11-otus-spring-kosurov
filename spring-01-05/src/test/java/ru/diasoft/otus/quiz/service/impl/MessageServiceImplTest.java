package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.diasoft.otus.quiz.service.ResourceManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@DisplayName("Класс MessageServiceImpl")
@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @InjectMocks
    private MessageServiceImpl messageService;
    @Mock
    private MessageSource messageSource;
    @Mock
    private ResourceManager resourceManager;

    @DisplayName("Вызывает MessageSource для получения локализованного сообщения")
    @Test
    void getMessage_callMessageSource() {
        messageService.getMessage("code", "arsg");
        verify(messageSource).getMessage(eq("code"), any(), any());
    }

    @DisplayName("Вызывает ResourceManager для получения локали")
    @Test
    void getMessage_callResourceManager() {
        messageService.getMessage("code", "arsg");
        verify(resourceManager).getLocale();
    }
}
