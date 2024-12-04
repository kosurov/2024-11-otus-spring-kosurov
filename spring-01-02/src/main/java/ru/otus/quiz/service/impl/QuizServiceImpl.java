package ru.otus.quiz.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.dto.QuestionWithAnswers;
import ru.otus.quiz.service.ConsoleTesterFactory;
import ru.otus.quiz.service.QuestionService;
import ru.otus.quiz.service.QuizService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final ConsoleTesterFactory consoleTesterFactory;
    private final int correctAnswersPercentToPass;
    private ConsoleTester consoleTester;

    public QuizServiceImpl(QuestionService questionService,
                           ConsoleTesterFactory testerFactory,
                           @Value("${quiz.correct-answers-percent-to-pass:60}")
                       int correctAnswersPercentToPass) {
        this.questionService = questionService;
        this.consoleTesterFactory = testerFactory;
        this.correctAnswersPercentToPass = correctAnswersPercentToPass;
    }

    @Override
    public void start() {
        try {
            consoleTester = consoleTesterFactory.getTester();
            consoleTester.greetUser();
            boolean readyToStart = consoleTester.askForReadiness();
            if (readyToStart) {
                startTesting();
            }
        } finally {
            consoleTester.close();
        }
    }

    private void startTesting() {
        List<QuestionWithAnswers> questionsWithAnswers = questionService.getQuestionsWithAnswers();
        for (QuestionWithAnswers questionWithAnswers : questionsWithAnswers) {
            testQuestion(questionWithAnswers);
        }
        processResult();
    }

    private void testQuestion(QuestionWithAnswers questionWithAnswers) {
        if (questionWithAnswers.getQuestion() == null || questionWithAnswers.getAnswers() == null) {
            return;
        }
        String question = getQuestion(questionWithAnswers);
        List<String> answers = getAnswers(questionWithAnswers);
        String correctAnswer = getCorrectAnswer(questionWithAnswers);
        String userAnswer = consoleTester.ask(question, answers);
        compareAnswers(correctAnswer, userAnswer);
    }

    private void processResult() {
        int correctAnswersPercent = consoleTester.getCorrectAnswersPercent();
        if (correctAnswersPercent < correctAnswersPercentToPass) {
            consoleTester.printFaultResult();
        } else {
            consoleTester.printSuccessResult();
        }
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
            consoleTester.incrementRightAnswersCount();
        }
    }
}
