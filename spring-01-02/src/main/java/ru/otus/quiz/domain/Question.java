package ru.otus.quiz.domain;

public class Question {

    private int id;
    private String question;
    private int correctAnswerId;

    public Question() {
    }

    public Question(int id, String question, int correctAnswerId) {
        this.id = id;
        this.question = question;
        this.correctAnswerId = correctAnswerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }
}
