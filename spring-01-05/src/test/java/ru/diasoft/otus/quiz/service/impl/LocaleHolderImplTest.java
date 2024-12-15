package ru.diasoft.otus.quiz.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocaleHolderImplTest {

    private LocaleHolderImpl localeHolder;
    private String defaultLanguageTag;

    @BeforeEach
    void setUp() {
        defaultLanguageTag = "en-US";
        localeHolder = new LocaleHolderImpl(defaultLanguageTag);
    }

    @DisplayName("Возвращает локаль по умолчанию")
    @Test
    void getLocale_returnsDefaultLocale() {
        Locale defaultLocale = Locale.forLanguageTag(defaultLanguageTag);
        assertEquals(defaultLocale, localeHolder.getLocale());
    }

    @DisplayName("Возвращает локаль")
    @Test
    void getLocale_returnsLocale() {
        assertNotNull(localeHolder.getLocale());
    }

    @DisplayName("Устанавливает новую локаль")
    @Test
    void setLocale_setNewLocale() {
        localeHolder.setLocale(Locale.UK);
        Locale localeFirst = localeHolder.getLocale();
        localeHolder.setLocale(Locale.US);
        Locale localeSecond = localeHolder.getLocale();
        assertNotEquals(localeFirst, localeSecond);
    }
}
