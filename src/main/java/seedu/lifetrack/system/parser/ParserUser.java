package seedu.lifetrack.system.parser;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.user.User;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getAgeOutOfRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHeightOutOfRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidAgeNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidExerciseLevelsNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidGoalNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidHeightNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidNumberOfSetUpInputs;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidWeightNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getUnderAgeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWeightOutOfRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptyUserSetupInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getOutOfExerciseLevelsRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getOutOfGoalRangeMessage;
import static seedu.lifetrack.ui.UserUi.printUserSetUpComplete;

/**
 * Utility Class to parse the commands made with regard to the User class.
 */
public class ParserUser {
    private static final int LENGTH_OF_SETUP_COMMAND = "user setup".length();

    /**
     * Parses the input from user to sieve out name, height, weight, age, gender, exercise levels and goals of the user
     * and sets them accordingly in the User class.
     *
     * @param input input String from the user
     * @param user  current user of the app
     * @throws InvalidInputException if user does not input all the information in the correct order and format
     * @throws NumberFormatException if user does not input a number for exercise levels and goals
     */
    public static void parseSetUp(String input, User user) throws InvalidInputException, NumberFormatException {
        checkEmptyInput(input);
        int heightIndex = input.indexOf("h/");
        int weightIndex = input.indexOf("w/");
        int ageIndex = input.indexOf("a/");
        int sexIndex = input.indexOf("s/");
        int exerciseLevelsIndex = input.indexOf("e/");
        int goalIndex = input.indexOf("g/");

        if (heightIndex == -1 || weightIndex == -1 || ageIndex == -1 || sexIndex == -1
                || exerciseLevelsIndex == -1 || goalIndex == -1) {
            throw new InvalidInputException(getInvalidNumberOfSetUpInputs());
        }
        checkSetUpInputsCorrectOrder(heightIndex, weightIndex, ageIndex, sexIndex, exerciseLevelsIndex, goalIndex);

        String[] parts = input.split("h/|w/|a/|s/|e/|g/");
        if (parts.length != 7) {
            throw new InvalidInputException(getInvalidNumberOfSetUpInputs());
        }
        String name = parts[0].substring(LENGTH_OF_SETUP_COMMAND).trim();
        int height = parseHeightIndex(parts[1].trim());
        int weight = parseWeightIndex(parts[2].trim());
        int age = parseAgeIndex(parts[3].trim());
        String sex = parts[4].trim().toLowerCase();
        int exerciseLevels = parseExerciseLevels(parts[5].trim());
        int goal = parseGoalIndex(parts[6].trim());

        user.setName(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);
        user.setSex(sex);
        user.setExerciseLevels(exerciseLevels);
        user.setGoal(goal);
        user.getHealthInfo();
        int caloriesRequired = user.getCaloriesRequired();
        printUserSetUpComplete(user.getName(), caloriesRequired);
    }

    /**
     * Parses the user's height input for an Integer
     *
     * @param input user's height input
     * @return user's height as an integer
     * @throws InvalidInputException if the height input is not an integer or if the user's height is not between
     *     90 and 225 cm
     */
    private static int parseHeightIndex(String input) throws InvalidInputException {
        try {
            int height = Integer.parseInt(input);
            if (height < 90 || height > 225){
                throw new InvalidInputException(getHeightOutOfRangeMessage());
            }
            return height;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidHeightNumberMessage());
        }
    }

    /**
     * Parses the user's weight input for an Integer
     *
     * @param input user's weight input
     * @return user's weight as an integer
     * @throws InvalidInputException if the weight input is not an integer or if the user's weight is not between
     *     30 and 200 kg
     */
    private static int parseWeightIndex(String input) throws InvalidInputException {
        try {
            int weight = Integer.parseInt(input);
            if (weight<30 || weight > 200){
                throw new InvalidInputException(getWeightOutOfRangeMessage());
            }
            return weight;
        } catch (NumberFormatException e){
            throw new InvalidInputException(getInvalidWeightNumberMessage());
        }
    }

    /**
     * Parses the user's age input for an Integer
     *
     * @param input user's age input
     * @return user's age as an integer
     * @throws InvalidInputException if the age input is not an integer or if the user's age is not between
     *     13 and 110 years old
     */
    private static int parseAgeIndex(String input) throws InvalidInputException{
        try{
            int age = Integer.parseInt(input);
            if(age <13 && age > 0){
                throw new InvalidInputException(getUnderAgeMessage());
            }
            if (age <0 || age > 110) {
                throw new InvalidInputException(getAgeOutOfRangeMessage());
            }
            return age;
        } catch (NumberFormatException e){
            throw new InvalidInputException(getInvalidAgeNumberMessage());
        }
    }

    /**
     * Parses the user's goal input for an Integer and assigns the String equivalent of it
     *
     * @param input user's goal input
     * @return String equivalent of User's goals
     * @throws InvalidInputException if the goal input is not an integer between 1 and 7
     */
    private static int parseGoalIndex(String input) throws InvalidInputException {
        try {
            int goalNumber = Integer.parseInt(input);
            if (goalNumber > 5 || goalNumber < 1) {

                throw new InvalidInputException(getOutOfGoalRangeMessage());
            }
            return goalNumber;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidGoalNumberMessage());
        }
    }

    /**
     * Parses the user's exercise levels input for an Integer and assigns the String equivalent of it
     *
     * @param input user's exercise levels input
     * @return String equivalent of User's exercise levels
     * @throws InvalidInputException if the goal input is not an integer between 1 and 5
     */
    private static int parseExerciseLevels(String input) throws InvalidInputException {
        try {
            int levelInNumber = Integer.parseInt(input);
            if (levelInNumber < 1 || levelInNumber > 5) {
                throw new InvalidInputException(getOutOfExerciseLevelsRangeMessage());
            }
            return levelInNumber;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidExerciseLevelsNumberMessage());
        }
    }

    /**
     * Ensures that the input given by the user is in the correct order
     *
     * @param heightIndex         Index of the input where user's height is specified
     * @param weightIndex         Index of the input where user's weight is specified
     * @param ageIndex            Index of the input where user's age is specified
     * @param sexIndex            Index of the input where user's gender is specified
     * @param exerciseLevelsIndex Index of the input where user's exercise levels is specified in Integer form
     * @param goalIndex           Index of the input where user's goal is specified in Integer form
     * @throws InvalidInputException if the order of the inputs is not correct. The input should be in this order:
     *                               height, weight, age, gender, exercise levels and goal.
     */
    private static void checkSetUpInputsCorrectOrder(int heightIndex, int weightIndex, int ageIndex, int sexIndex,
                                                     int exerciseLevelsIndex,
                                                     int goalIndex) throws InvalidInputException {
        if (!(heightIndex < weightIndex && weightIndex < ageIndex && sexIndex < exerciseLevelsIndex
                && exerciseLevelsIndex < goalIndex)) {
            throw new InvalidInputException(getInvalidNumberOfSetUpInputs());
        }
    }

    /**
     * Checks if User Setup command is empty
     *
     * @param input input from user
     * @throws InvalidInputException if the command is empty
     */
    private static void checkEmptyInput(String input) throws InvalidInputException {
        if (input.substring(LENGTH_OF_SETUP_COMMAND).trim().isEmpty()) {
            throw new InvalidInputException(getEmptyUserSetupInputMessage());
        }
    }
}
