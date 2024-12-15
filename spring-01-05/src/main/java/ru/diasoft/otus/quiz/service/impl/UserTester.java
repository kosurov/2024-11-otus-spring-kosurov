package ru.diasoft.otus.quiz.service.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.service.InputOutputService;
import ru.diasoft.otus.quiz.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTester {

    private final InputOutputService inputOutputService;
    private final MessageService messageService;
    private String username;
    private int correctAnswers;
    private int questionsCount;

    public UserTester(InputOutputService inputOutputService, MessageService messageService) {
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    public void greetUser() {
        inputOutputService.out(messageService.getMessage("out.first.name"));
        String firstName = inputOutputService.readString();
        inputOutputService.out(messageService.getMessage("out.last.name"));
        String lastName = inputOutputService.readString();
        username = firstName + " " + lastName;
        inputOutputService.out(messageService.getMessage("out.nice.to.meet.you", username));
        inputOutputService.out(messageService.getMessage("out.welcome"));
    }

    public boolean askForReadiness() {
        inputOutputService.out(messageService.getMessage("out.ready.to.start"));
        String yes = messageService.getMessage("out.yes");
        String no = messageService.getMessage("out.no");
        String answer = inputOutputService.readString();
        while (!yes.equalsIgnoreCase(answer) && !no.equalsIgnoreCase(answer)) {
            inputOutputService.out(messageService.getMessage("out.yes.or.no"));
            answer = inputOutputService.readString();
        }
        return yes.equals(answer);
    }

    public String ask(String question, List<String> answers) {
        Map<Character, String> answerOutput = getAnswerOutput(answers);
        inputOutputService.out(question);
        printAnswerOutput(answerOutput);
        questionsCount++;
        String userInput = inputOutputService.readString();
        return getAnswerFromUserInput(userInput, answerOutput);
    }

    public void incrementRightAnswersCount() {
        correctAnswers++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void printFaultResult() {
        inputOutputService.out(messageService.getMessage("out.fault.result", username));
        printStatistic();
    }

    public void printSuccessResult() {
        inputOutputService.out(messageService.getMessage("out.success.result", username));
        printStatistic();
    }

    public void printInternalError(String message) {
        inputOutputService.out(messageService.getMessage("error.internal.running.program", message));
    }

    private Map<Character, String> getAnswerOutput(List<String> answers) {
        Map<Character, String> answerOutput = new HashMap<>();
        char answerLetter = 'a';
        for (String answer : answers) {
            answerOutput.put(answerLetter, answer);
            answerLetter++;
        }
        return answerOutput;
    }

    private String getAnswerFromUserInput(String userInput, Map<Character, String> answerOutput) {
        if (hasAnswersOptions(answerOutput)) {
            String answer = answerOutput.get(userInput.charAt(0));
            if (answer != null) {
                return answer;
            }
        }
        return userInput;
    }

    private boolean hasAnswersOptions(Map<Character, String> answerOutput) {
        return answerOutput.size() > 1;
    }

    private void printAnswerOutput(Map<Character, String> answerOutput) {
        if (hasAnswersOptions(answerOutput)) {
            answerOutput.forEach((key, value) -> inputOutputService.out(key + ") " + value));
        }
    }

    private void printStatistic() {
        inputOutputService.out(messageService.getMessage("out.statistic", correctAnswers, questionsCount));
    }
}
