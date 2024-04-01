package seedu.lifetrack.system.parser;

import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.calories.Activity;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectCaloriesInputMessage;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectMacrosInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWhitespaceInInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncompleteMacrosMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getMacrosInCaloriesOutMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getCaloriesIncorrectOrderMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getCaloriesMissingKeywordsMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWhitespaceInMacrosInputMessage;

import seedu.lifetrack.Entry;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParserCalories {

    private static final int CARBS_IDX = 0;
    private static final int PROTEINS_IDX = 1;
    private static final int FATS_IDX = 2;
    private static final int CALORIES_OUT_PADDING = 12;

    /**
     * Parses a string input to create an Entry object representing calorie intake.
     *
     * This method expects the input string to follow a specific format, where the
     * description, calorie count, date and macronutrients are separated by the
     * delimiters 'desc/', 'c/', 'date/', and 'm/'. The method extracts these components
     * and creates either an InputEntry or OutputEntry object depending on the user command.
     * If required inputs are missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing date, time, activity,
     *              and calorie count information
     * @return an Entry object representing calorie intake
     * @throws InvalidInputException if the input string is missing components or contains empty fields
     */
    public static Entry parseCaloriesInput(String input) throws InvalidInputException {
        int caloriesIndex = input.indexOf("c/");
        int dateIndex = input.indexOf("d/");
        int macrosIndex = input.indexOf("m/");
        
        checkKeywordsExist(caloriesIndex, dateIndex);
        assert caloriesIndex != -1 : "The c/ keyword should exist!";
        assert dateIndex != -1 : "The d/ keyword should exist!";

        checkKeywordsCorrectlyOrdered(caloriesIndex, dateIndex, macrosIndex);
        assert caloriesIndex < dateIndex : "The c/ keyword must appear before strDate/ in the input!";

        //extract command, description, calories, strDate from input
        String[] parts = input.split("c/|d/|m/");
        String command = parts[0].substring(0, CALORIES_OUT_PADDING).trim();
        String description = getDescriptionFromInput(input, command, caloriesIndex);
        String strCalories = parts[1].trim();

        // Try catch here is needed because if i input , calories in chicken c/1000 d/  , code fails
        // code fails because index out of bounds occurs due to parts[2].trim()
        String strDate = null;
        try {
            strDate = parts[2].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException(getWhitespaceInInputMessage());
        }

        checkInputsAreNonEmpty(description, strCalories, strDate);
        assert description != "" : "The description field should be a non-empty string!";
        assert strCalories != "" : "The calories field should be a non-empty string!";
        assert strDate != "" : "The strDate field should be a non-empty string!";

        //extract macronutrients if user provided it in their input, otherwise initialise it as null
        int[] macros = null;
        if (macrosIndex != -1) {
            if (command.equals("calories out")) {
                throw new InvalidInputException(getMacrosInCaloriesOutMessage());
            }
            String macroString = parts[3].trim();
            try {
                macros = getMacrosFromInput(macroString);
            } catch (InvalidInputException e) {
                throw new InvalidInputException(e.getMessage());
            }
        }

        //convert calories from string to integer
        int calories = getIntegerCaloriesFromInput(strCalories);
        checkCaloriesIsPositiveInteger(calories);
        assert calories > 0 : "Calories value must be a positive integer!";

        //@@author rexyyong
        // Convert strDate from type String to date of type LocalDate
        LocalDate date = null;
        try {
            date = getLocalDateFromInput(strDate);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format");
        }
        //@@author

        if (command.equals("calories out")) {
            return makeNewOutputEntry(description, calories, date);
        } else if (macros == null) {
            return makeNewInputEntry(description, calories, date);
        } else {
            return makeNewInputEntry(description, calories, date, macros);
        }
    }

    //@@author rexyyong
    public static LocalDate getLocalDateFromInput(String strDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(strDate);
        return date;
    }
    //@@author

    private static int getIntegerCaloriesFromInput(String strCalories) {
        int calories = 0;
        try {
            calories = Integer.parseInt(strCalories);
        } catch (NumberFormatException e) {
            System.out.println(getIncorrectCaloriesInputMessage());
        }
        return calories;
    }

    private static String getDescriptionFromInput(String inputString, String command, int caloriesIndex) {
        String description;
        if (command.equals("calories out")) {
            description = inputString.substring(CALORIES_OUT_PADDING, caloriesIndex).trim();
        } else {
            command = inputString.substring(0, CALORIES_OUT_PADDING - 1).trim();
            description = inputString.substring(CALORIES_OUT_PADDING - 1, caloriesIndex).trim();
        }
        return description;
    }

    private static int[] getMacrosFromInput(String macroString) throws InvalidInputException {
        int[] macros = new int[3];
        try {
            String[] macroParts = macroString.split(",");
            int idx = 0;
            for (String macro: macroParts) {
                //throw exception if user inputs whitespace in the macros field i.e. m/123, ,123
                if (macro.trim().isEmpty()) {
                    throw new InvalidInputException(getWhitespaceInMacrosInputMessage());
                }
                macros[idx] = Integer.parseInt(macro.trim());
                idx++;
            }
            //throw exception if there are missing values in the macros field
            if (idx != 3) {
                throw new InvalidInputException(getIncompleteMacrosMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println(getIncorrectMacrosInputMessage());
        }
        return macros;
    }

    private static void checkCaloriesIsPositiveInteger(int calories) throws InvalidInputException {
        if (calories <= 0) {
            throw new InvalidInputException(getIncorrectCaloriesInputMessage());
        }
    }

    private static void checkInputsAreNonEmpty(String description, String strCalories, String date)
            throws InvalidInputException {
        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strCalories.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException(getWhitespaceInInputMessage());
        }
    }

    private static void checkKeywordsExist(int caloriesIndex, int dateIndex) throws InvalidInputException {
        //check that c/ and date/ keywords exist in the input, else throw exception
        if (caloriesIndex == -1 || dateIndex == -1) {
            throw new InvalidInputException(getCaloriesMissingKeywordsMessage());
        }
    }

    private static void checkKeywordsCorrectlyOrdered(int caloriesIndex, int dateIndex, int macrosIndex)
            throws InvalidInputException {        
        if ((macrosIndex != -1 && !(caloriesIndex < dateIndex && dateIndex < macrosIndex)) ||
                (macrosIndex == -1 && !(caloriesIndex < dateIndex))) {
            throw new InvalidInputException(getCaloriesIncorrectOrderMessage());
        }
    }

    private static Entry makeNewOutputEntry(String description, int calories, LocalDate date) {
        Activity newActivity = new Activity();

        return new OutputEntry(description, calories, date, newActivity);
    }

    private static Entry makeNewInputEntry(String description, int calories, LocalDate date) {

        return new InputEntry(description, calories, date);
    }

    private static Entry makeNewInputEntry(String description, int calories, LocalDate date, int[] foodMacros) {

        Food newFood = new Food(foodMacros[CARBS_IDX], foodMacros[PROTEINS_IDX], foodMacros[FATS_IDX]);

        return new InputEntry(description, calories, date, newFood);
    }
}
