//@@author paturikarthik
package seedu.lifetrack.system.parser;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.user.User;

import java.util.Objects;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getAgeOutOfRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptyGenderInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptyNameInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptyUserSetupInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptyUserUpdateFieldMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHeightOutOfRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidAgeNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidExerciseLevelsNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidGenderInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidGoalNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidHeightNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidNumberOfSetUpInputs;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidWeightNumberMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getOutOfExerciseLevelsRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getOutOfGoalRangeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getUnderAgeMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getUnknownUpdateMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWeightOutOfRangeMessage;
import static seedu.lifetrack.ui.UserUi.printNewUserAge;
import static seedu.lifetrack.ui.UserUi.printNewUserExerciseLevels;
import static seedu.lifetrack.ui.UserUi.printNewUserGoal;
import static seedu.lifetrack.ui.UserUi.printNewUserHeight;
import static seedu.lifetrack.ui.UserUi.printNewUserName;
import static seedu.lifetrack.ui.UserUi.printNewUserSex;
import static seedu.lifetrack.ui.UserUi.printNewUserWeight;
import static seedu.lifetrack.ui.UserUi.printUserCaloriesRequired;
import static seedu.lifetrack.ui.UserUi.printUserSetUpComplete;

/**
 * Utility Class to parse the commands made with regard to the User class.
 */
public class ParserUser {
    private static final int LENGTH_OF_SETUP_COMMAND = "user setup".length();
    private static final int USER_INPUT_NAME_INDEX = 0;
    private static final int USER_INPUT_HEIGHT_INDEX = 1;
    private static final int USER_INPUT_WEIGHT_INDEX = 2;
    private static final int USER_INPUT_AGE_INDEX = 3;
    private static final int USER_INPUT_GENDER_INDEX = 4;
    private static final int USER_INPUT_EXERCISE_LEVELS_INDEX = 5;
    private static final int USER_INPUT_GOAL_INDEX = 6;
    private static final int LENGTH_OF_UPDATE_COMMAND = "user update".length();
    private static final int LENGTH_OF_NAME = "name".length();
    private static final int LENGTH_OF_HEIGHT = "height".length();
    private static final int LENGTH_OF_WEIGHT = "weight".length();
    private static final int LENGTH_OF_AGE = "age".length();
    private static final int LENGTH_OF_SEX = "sex".length();
    private static final int LENGTH_OF_EXERCISE_LEVELS = "exercise levels".length();
    private static final int LENGTH_OF_GOAL = "goal".length();

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
        String name = null;
        if (heightIndex != -1) {
            name = parseName(input.substring(LENGTH_OF_SETUP_COMMAND, heightIndex).trim());
            input = input.substring(heightIndex).toLowerCase();
        }
        int weightIndex = input.indexOf("w/");
        int ageIndex = input.indexOf("a/");
        int sexIndex = input.indexOf("s/");
        int exerciseLevelsIndex = input.indexOf("e/");
        int goalIndex = input.indexOf("g/");

        if (heightIndex == -1 || weightIndex == -1 || ageIndex == -1 || sexIndex == -1
                || exerciseLevelsIndex == -1 || goalIndex == -1) {
            throw new InvalidInputException(getInvalidNumberOfSetUpInputs());
        }
        checkSetUpInputsCorrectOrder(weightIndex, ageIndex, sexIndex, exerciseLevelsIndex, goalIndex);

        String[] parts = input.split("h/|w/|a/|s/|e/|g/");
        if (parts.length != 7) {
            throw new InvalidInputException(getInvalidNumberOfSetUpInputs());
        }
        int height = parseHeightIndex(parts[USER_INPUT_HEIGHT_INDEX].trim());
        int weight = parseWeightIndex(parts[USER_INPUT_WEIGHT_INDEX].trim());
        int age = parseAgeIndex(parts[USER_INPUT_AGE_INDEX].trim());
        String sex = parseGenderIndex(parts[USER_INPUT_GENDER_INDEX].trim().toLowerCase());
        int exerciseLevels = parseExerciseLevels(parts[USER_INPUT_EXERCISE_LEVELS_INDEX].trim());
        int goal = parseGoalIndex(parts[USER_INPUT_GOAL_INDEX].trim());

        user.setName(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);
        user.setSex(sex);
        user.setExerciseLevels(exerciseLevels);
        user.setGoal(goal);
        user.getHealthInfo();
        printUserSetUpComplete(user);
    }

    private static String parseName(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException(getEmptyNameInputMessage());
        }
        return input;
    }

    /**
     * Parses the user's height input for an Integer
     *
     * @param input user's height input
     * @return user's height as an integer
     * @throws InvalidInputException if the height input is not an integer or if the user's height is not between
     *                               90 and 225 cm
     */
    private static int parseHeightIndex(String input) throws InvalidInputException {
        try {
            int height = Integer.parseInt(input);
            if (height < 90 || height > 225) {
                throw new InvalidInputException(getHeightOutOfRangeMessage());
            }
            return height;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidHeightNumberMessage());
        }
    }

    /**
     * Parses the user's gender input for a String
     *
     * @param input user's gender input
     * @return user's gender as a string
     * @throws InvalidInputException if the gender input is empty or if it is not male/m or female/f
     */
    private static String parseGenderIndex(String input) throws InvalidInputException {
        try {
            if (input.isEmpty()) {
                throw new InvalidInputException(getEmptyGenderInputMessage());
            }
            if (input.equals("male") || input.equals("m")) {
                return "male";
            }
            if (input.equals("female") || input.equals("f")) {
                return "female";
            }
            throw new InvalidInputException(getInvalidGenderInputMessage());
        } catch (NullPointerException e) {
            throw new InvalidInputException(getEmptyUserSetupInputMessage());
        }
    }

    /**
     * Parses the user's weight input for an Integer
     *
     * @param input user's weight input
     * @return user's weight as an integer
     * @throws InvalidInputException if the weight input is not an integer or if the user's weight is not between
     *                               30 and 200 kg
     */
    private static int parseWeightIndex(String input) throws InvalidInputException {
        try {
            int weight = Integer.parseInt(input);
            if (weight < 30 || weight > 200) {
                throw new InvalidInputException(getWeightOutOfRangeMessage());
            }
            return weight;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidWeightNumberMessage());
        }
    }

    /**
     * Parses the user's age input for an Integer
     *
     * @param input user's age input
     * @return user's age as an integer
     * @throws InvalidInputException if the age input is not an integer or if the user's age is not between
     *                               13 and 30 years old
     */
    private static int parseAgeIndex(String input) throws InvalidInputException {
        try {
            int age = Integer.parseInt(input);
            if (age < 13 && age > 0) {
                throw new InvalidInputException(getUnderAgeMessage());
            }
            if (age < 0 || age > 30) {
                throw new InvalidInputException(getAgeOutOfRangeMessage());
            }
            return age;
        } catch (NumberFormatException e) {
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
     * @param weightIndex         Index of the input where user's weight is specified
     * @param ageIndex            Index of the input where user's age is specified
     * @param sexIndex            Index of the input where user's gender is specified
     * @param exerciseLevelsIndex Index of the input where user's exercise levels is specified in Integer form
     * @param goalIndex           Index of the input where user's goal is specified in Integer form
     * @throws InvalidInputException if the order of the inputs is not correct. The input should be in this order:
     *                               height, weight, age, gender, exercise levels and goal.
     */
    private static void checkSetUpInputsCorrectOrder(int weightIndex, int ageIndex, int sexIndex,
                                                     int exerciseLevelsIndex,
                                                     int goalIndex) throws InvalidInputException {
        if (!(weightIndex < ageIndex && sexIndex < exerciseLevelsIndex
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

    /**
     * Parses "user update" command to update the relevant field of the user. Recalculates the user's calorific goal as
     * well.
     *
     * @param input input from the user
     * @param user  current User
     * @throws InvalidInputException if the command is empty, the update field is empty, if the field given to update
     *                               is unknown or if the value to update is not correct.
     */
    public static void parseUpdate(String input, User user) throws InvalidInputException {
        checkEmptyUpdateInput(input);
        String fieldToUpdate = input.substring(LENGTH_OF_UPDATE_COMMAND).trim();
        checkEmptyUpdateField(fieldToUpdate);
        if (fieldToUpdate.startsWith("name ")) {
            String name = parseName(fieldToUpdate.substring(LENGTH_OF_NAME).trim());
            user.setName(name);
            assert Objects.equals(user.getName(), name);
            printNewUserName(name);
        } else if (fieldToUpdate.startsWith("height ")) {
            int height = parseHeightIndex(fieldToUpdate.toLowerCase().substring(LENGTH_OF_HEIGHT).trim());
            user.setHeight(height);
            assert Objects.equals(user.getHeight(), height);
            printNewUserHeight(height);
            user.getHealthInfo();
            printUserCaloriesRequired(user.getCaloriesRequired());
        } else if (fieldToUpdate.startsWith("weight ")) {
            int weight = parseWeightIndex(fieldToUpdate.toLowerCase().substring(LENGTH_OF_WEIGHT).trim());
            user.setWeight(weight);
            assert Objects.equals(user.getWeight(), weight);
            printNewUserWeight(weight);
            user.getHealthInfo();
            printUserCaloriesRequired(user.getCaloriesRequired());
        } else if (fieldToUpdate.startsWith("age ")) {
            int age = parseAgeIndex(fieldToUpdate.toLowerCase().substring(LENGTH_OF_AGE).trim());
            user.setAge(age);
            assert Objects.equals(user.getAge(), age);
            printNewUserAge(age);
            user.getHealthInfo();
            printUserCaloriesRequired(user.getCaloriesRequired());
        } else if (fieldToUpdate.startsWith("exercise levels ")) {
            int level = parseExerciseLevels(fieldToUpdate.substring(LENGTH_OF_EXERCISE_LEVELS).trim());
            user.setExerciseLevels(level);
            assert Objects.equals(user.getExerciseLevels(), level);
            printNewUserExerciseLevels(user, level);
            user.getHealthInfo();
            printUserCaloriesRequired(user.getCaloriesRequired());
        } else if (fieldToUpdate.startsWith("goal ")) {
            int goal = parseGoalIndex(fieldToUpdate.toLowerCase().substring(LENGTH_OF_GOAL).trim());
            user.setGoal(goal);
            assert Objects.equals(user.getGoal(), goal);
            printNewUserGoal(user, goal);
            user.getHealthInfo();
            printUserCaloriesRequired(user.getCaloriesRequired());
        } else if (fieldToUpdate.startsWith("sex ")) {
            String sex = parseGenderIndex(fieldToUpdate.toLowerCase().substring(LENGTH_OF_SEX).trim());
            user.setSex(sex);
            assert Objects.equals(user.getSex(), sex);
            printNewUserSex(sex);
            user.getHealthInfo();
            printUserCaloriesRequired(user.getCaloriesRequired());
        } else {
            throw new InvalidInputException(getUnknownUpdateMessage());
        }
    }

    /**
     * Checks if the "user update" command is empty
     *
     * @param input input from the user
     * @throws InvalidInputException if the command is empty
     */
    private static void checkEmptyUpdateInput(String input) throws InvalidInputException {
        if (input.substring(LENGTH_OF_UPDATE_COMMAND).trim().isBlank()) {
            throw new InvalidInputException(getEmptyUserUpdateFieldMessage());
        }
    }

    /**
     * Checks if the field to update is empty
     *
     * @param input input from the user
     * @throws InvalidInputException if the field is empty
     */
    private static void checkEmptyUpdateField(String input) throws InvalidInputException {
        if (input.trim().isEmpty()) {
            throw new InvalidInputException(getEmptyUserUpdateFieldMessage());
        }
    }

}
