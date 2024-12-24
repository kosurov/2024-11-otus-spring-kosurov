package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.exception.UnsupportedLanguageException;
import ru.diasoft.otus.quiz.service.LocaleHolder;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@DisplayName("Класс LocaleDefinerImpl")
@ExtendWith(MockitoExtension.class)
class LocaleDefinerImplTest {

    @InjectMocks
    private LocaleDefinerImpl localeDefiner;
    @Mock
    private LocaleHolder localeHolder;
    @Mock
    private MessageService messageService;

    @DisplayName("Определяет локаль ru-RU")
    @Test
    void defineLocale_definesRuLocale() {
        Locale ruLocale = Locale.forLanguageTag("ru-RU");
        localeDefiner.defineLocale("ru");
        verify(localeHolder).setLocale(ruLocale);
    }

    @DisplayName("Определяет локаль en-US")
    @Test
    void defineLocale_definesEnLocale() {
        Locale ruLocale = Locale.forLanguageTag("en-US");
        localeDefiner.defineLocale("en");
        verify(localeHolder).setLocale(ruLocale);
    }

    @DisplayName("Бросает исключение, если локаль не определена")
    @Test
    void defineLocale_throwsException() {
        assertThrows(UnsupportedLanguageException.class, () -> localeDefiner.defineLocale("unsupported"));
        verify(messageService, atLeastOnce()).getMessage(anyString());
    }

}
