package ru.diasoft.otus.quiz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.diasoft.otus.quiz.dto.ResourceProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "resource")
public class ResourceProperties {

    private final List<ResourceProperty> questions = new ArrayList<>();
    private final List<ResourceProperty> answers = new ArrayList<>();

    private Map<Locale, String> localizedQuestions;
    private Map<Locale, String> localizedAnswers;

    public Map<Locale, String> getLocalizedQuestions() {
        if (localizedQuestions == null) {
            localizedQuestions = questions.stream()
                    .collect(Collectors.toMap(p -> Locale.forLanguageTag(p.getLocale()),
                            ResourceProperty::getFileName, (p1, p2) -> p1));
        }
        return localizedQuestions;
    }

    public Map<Locale, String> getLocalizedAnswers() {
        if (localizedAnswers == null) {
            localizedAnswers = answers.stream()
                    .collect(Collectors.toMap(p -> Locale.forLanguageTag(p.getLocale()),
                            ResourceProperty::getFileName, (p1, p2) -> p1));
        }
        return localizedAnswers;
    }

    public List<ResourceProperty> getQuestions() {
        return questions;
    }

    public List<ResourceProperty> getAnswers() {
        return answers;
    }

}
