package seedu.lifetrack.ui;

import seedu.lifetrack.calorielist.CalorieList;

import java.util.Scanner;

/**
 * Reads user input from the console and processes it.
 * <p>
 * This method continuously reads input from the console until the user
 * inputs "bye". For each input line, it checks if it's empty and prompts
 * the user to enter a non-empty input if it is. If the input line starts
 * with "calories in", it attempts to parse the input as calorie intake
 * information using the calorieIn method from the CalorieList class.
 */
public class Ui {

    public static void readUserInput(CalorieList calorieList) {
        String line;
        do {
            line = new Scanner(System.in).nextLine();
            handleUserInput(line, calorieList);
        } while (!line.equalsIgnoreCase("bye"));
    }

    public static void handleUserInput(String line, CalorieList calorieList) {
        if (line.trim().isEmpty()) {
            System.out.println("Please enter a non empty Input!");
        } else if (line.startsWith("calories in") || line.startsWith("calories out")) {
            calorieList.addEntry(line);
        } else if (line.startsWith("list")) {
            calorieList.printCalorieList();
        } else if (line.startsWith("delete")) {
            calorieList.deleteEntry(line);
        }
    }

    public static void byeMessage() {
        System.out.println("Bye! See you again soon ^^");
    }

    public static void printEmptyInputMessage() {
        System.out.println("Please enter a non-empty input!");
    }

    public static void printLine() {
        System.out.println("\t -------------------------------------------------------------");
    }
}
