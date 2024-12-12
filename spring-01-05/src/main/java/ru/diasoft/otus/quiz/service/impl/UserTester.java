package ru.diasoft.otus.quiz.service.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.otus.quiz.service.InputOutputService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTester {

    private final InputOutputService inputOutputService;
    private String username;
    private int correctAnswers;
    private int questionsCount;

    public UserTester(InputOutputService inputOutputService) {
        this.inputOutputService = inputOutputService;
    }

    public void greetUser() {
        inputOutputService.out("Please enter your first name:");
        String firstName = inputOutputService.readString();
        inputOutputService.out("Please enter your last name:");
        String lastName = inputOutputService.readString();
        username = firstName + " " + lastName;
        inputOutputService.out("Nice to meet you " + username + "!");
        inputOutputService.out("Welcome to Quiz Service 2000!");
    }

    public boolean askForReadiness() {
        inputOutputService.out("Are you ready to start the quiz? (yes/no)");
        String answer = inputOutputService.readString();
        while (!"yes".equals(answer) && !"no".equals(answer)) {
            inputOutputService.out("Please enter 'yes' or 'no'");
            answer = inputOutputService.readString();
        }
        return "yes".equals(answer);
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
        inputOutputService.out("Sorry, " + username + ". The quiz has been failed, please try again later.");
        printStatistic();
    }

    public void printSuccessResult() {
        inputOutputService.out("Congratulations, " + username + "! You have successfully completed the quiz!");
        printStatistic();
    }

    public void printInternalError(String message) {
        inputOutputService.out("Error occurred while running program: " + message);
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
        inputOutputService.out("You have " + correctAnswers + " out of " + questionsCount + " correct answers.");
    }
}
