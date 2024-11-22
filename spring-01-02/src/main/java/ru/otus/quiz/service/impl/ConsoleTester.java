package ru.otus.quiz.service.impl;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleTester implements Closeable {

    private final Scanner scanner;
    private String username;
    private int correctAnswers;
    private int questionsCount;

    public ConsoleTester(Scanner scanner) {
        this.scanner = scanner;
    }

    public void greetUser() {
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name:");
        String lastName = scanner.nextLine();
        username = firstName + " " + lastName;
        System.out.println("Nice to meet you " + username + "!");
        System.out.println("Welcome to Quiz Service 2000!");
    }

    public boolean askForReadiness() {
        System.out.println("Are you ready to start the quiz? (yes/no)");
        String answer = scanner.nextLine();
        while (!"yes".equals(answer) && !"no".equals(answer)) {
            System.out.println("Please enter 'yes' or 'no'");
            answer = scanner.nextLine();
        }
        return "yes".equals(answer);
    }

    public String ask(String question, List<String> answers) {
        Map<Character, String> answerOutput = getAnswerOutput(answers);
        System.out.println(question);
        printAnswerOutput(answerOutput);
        questionsCount++;
        String userInput = scanner.nextLine();
        return getAnswerFromUserInput(userInput, answerOutput);
    }

    public void incrementRightAnswersCount() {
        correctAnswers++;
    }

    public int getCorrectAnswersCount() {
        return correctAnswers;
    }

    public int getQuestionCount() {
        return questionsCount;
    }

    public void printFaultResult() {
        System.out.println("Sorry, " + username + ". The quiz has been failed, please try again later.");
        printStatistic();
    }

    public void printSuccessResult() {
        System.out.println("Congratulations, " + username + "! You have successfully completed the quiz!");
        printStatistic();
    }

    @Override
    public void close() {
        this.scanner.close();
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
            answerOutput.forEach((key, value) -> System.out.println(key + ") " + value));
        }
    }

    private void printStatistic() {
        System.out.println("You have " + correctAnswers + " out of " + questionsCount + " correct answers.");
    }
}
