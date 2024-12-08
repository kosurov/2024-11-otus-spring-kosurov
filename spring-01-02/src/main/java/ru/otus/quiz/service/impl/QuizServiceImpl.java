package ru.otus.quiz.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.QuestionService;
import ru.otus.quiz.service.QuizService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final UserTester userTester;
    private final int correctAnswersPercentToPass;

    public QuizServiceImpl(QuestionService questionService,
                           UserTester userTester,
                           @Value("${quiz.correct-answers-percent-to-pass:60}")
                           int correctAnswersPercentToPass) {
        this.questionService = questionService;
        this.userTester = userTester;
        this.correctAnswersPercentToPass = correctAnswersPercentToPass;
    }

    @Override
    public void start() {
        userTester.greetUser();
        boolean readyToStart = userTester.askForReadiness();
        if (readyToStart) {
            startTesting();
        }
    }

    private void startTesting() {
        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        if (questionsWithAnswers.isEmpty()) {
            userTester.printInternalError("Question database is empty");
            return;
        }
        testQuestions(questionsWithAnswers);
        processResult();
    }

    private void testQuestions(List<QuestionWithAnswers> questionsWithAnswers) {
        for (QuestionWithAnswers questionWithAnswers : questionsWithAnswers) {
            testQuestion(questionWithAnswers);
        }
    }

    private void testQuestion(QuestionWithAnswers questionWithAnswers) {
        if (questionWithAnswers.getQuestion() == null || questionWithAnswers.getAnswers() == null) {
            return;
        }
        String question = getQuestion(questionWithAnswers);
        List<String> answers = getAnswers(questionWithAnswers);
        String correctAnswer = getCorrectAnswer(questionWithAnswers);
        String userAnswer = userTester.ask(question, answers);
        compareAnswers(correctAnswer, userAnswer);
    }

    private void processResult() {
        int correctAnswersPercent = getCorrectAnswersPercent();
        if (correctAnswersPercent < correctAnswersPercentToPass) {
            userTester.printFaultResult();
        } else {
            userTester.printSuccessResult();
        }
    }

    private int getCorrectAnswersPercent() {
        return userTester.getCorrectAnswers() * 100 / userTester.getQuestionsCount();
    }

    private String getQuestion(QuestionWithAnswers questionWithAnswers) {
        return questionWithAnswers.getQuestion().getQuestion();
    }

    private List<String> getAnswers(QuestionWithAnswers questionWithAnswers) {
        return questionWithAnswers.getAnswers().stream()
                .map(Answer::getAnswer)
                .collect(Collectors.toList());
    }

    private String getCorrectAnswer(QuestionWithAnswers question) {
        return question.getAnswers().stream()
                .filter(answer -> question.getQuestion().getCorrectAnswerId() == answer.getId())
                .map(Answer::getAnswer)
                .findFirst()
                .orElseThrow();
    }

    private void compareAnswers(String correctAnswer, String userAnswer) {
        if (correctAnswer.equalsIgnoreCase(userAnswer)) {
            userTester.incrementRightAnswersCount();
        }
    }
}
