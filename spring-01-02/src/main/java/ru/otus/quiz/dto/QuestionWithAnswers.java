package ru.otus.quiz.dto;

import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.util.List;

public class QuestionWithAnswers {

    private Question question;
    private List<Answer> answers;

    public QuestionWithAnswers(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
