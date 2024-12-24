package ru.diasoft.otus.quiz.dto;

public class ResourceProperty {

    private String locale;
    private String fileName;

    public ResourceProperty(String locale, String fileName) {
        this.locale = locale;
        this.fileName = fileName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
