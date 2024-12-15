package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otus.quiz.service.InputOutputService;
import ru.diasoft.otus.quiz.service.LocaleHolder;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс LocaleDefinerImpl")
@ExtendWith(MockitoExtension.class)
class LocaleDefinerImplTest {

    @InjectMocks
    private LocaleDefinerImpl localeDefiner;
    @Mock
    private InputOutputService inputOutputService;
    @Mock
    private LocaleHolder localeHolder;
    @Mock
    private MessageService messageService;
    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @DisplayName("Определяет локаль ru-RU")
    @Test
    void defineLocale_definesRuLocale() {
        Locale ruLocale = Locale.forLanguageTag("ru-RU");
        when(inputOutputService.readString()).thenReturn("ru");
        localeDefiner.defineLocale();
        verify(localeHolder).setLocale(ruLocale);
    }

    @DisplayName("Определяет локаль en-US")
    @Test
    void defineLocale_definesEnLocale() {
        Locale ruLocale = Locale.forLanguageTag("en-US");
        when(inputOutputService.readString()).thenReturn("en");
        localeDefiner.defineLocale();
        verify(localeHolder).setLocale(ruLocale);
    }

    @DisplayName("Просит пользователя выбрать язык")
    @Test
    void defineLocale_askUserForLocale() {
        String expected = "Please, choose your language (en/ru):";
        when(messageService.getMessage("out.choose.language")).thenReturn(expected);
        when(inputOutputService.readString()).thenReturn("en");

        localeDefiner.defineLocale();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }

    @DisplayName("Переспрашивает пользователя, если введен неверный язык")
    @Test
    void defineLocale_repeatAskingUserForLocale() {
        String expected = "Please enter 'en' or 'ru':";
        when(messageService.getMessage("out.choose.language")).thenReturn("string");
        when(messageService.getMessage("out.retry.choose.language")).thenReturn(expected);
        when(inputOutputService.readString()).thenReturn("incorrect language","en");

        localeDefiner.defineLocale();

        verify(inputOutputService, atLeastOnce()).out(stringCaptor.capture());
        assertTrue(stringCaptor.getAllValues().stream().anyMatch(expected::equals));
    }
}
