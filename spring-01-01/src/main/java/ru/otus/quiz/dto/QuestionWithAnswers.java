package ru.otus.quiz.dto;

import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.util.List;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        String result = "";
        if (this.question != null) {
            result = result + "Question: " + question.getQuestion();
        }
        if (this.answers != null && !this.answers.isEmpty()) {
            StringJoiner answersString = new StringJoiner("; ");
            int answerNumber = 1;
            for (Answer answer : answers) {
                answersString.add(answerNumber +". " + answer.getAnswer());
                answerNumber++;
            }
            result = result + " Answers: " + answersString;
        }
        return result;
    }
}
