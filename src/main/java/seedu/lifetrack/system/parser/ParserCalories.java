//@@author owx0130
package seedu.lifetrack.system.parser;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getCaloriesIncorrectOrderMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getCaloriesMissingKeywordsMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getDateLaterThanPresentDateMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptyMacrosMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncompleteMacrosMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncorrectCaloriesInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncorrectMacrosInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidDateMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getMacrosInCaloriesOutMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWhitespaceInInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWhitespaceInMacrosInputMessage;

public class ParserCalories {

    private static final int CARBS_IDX = 0;
    private static final int PROTEINS_IDX = 1;
    private static final int FATS_IDX = 2;
    private static final int CALORIES_OUT_PADDING = 12;
    private static final int CALORIES_FIND_LENGTH = "calories find".length();

    /**
     * Parses a string input to create an Entry object representing calorie intake.
     * <p>
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
    public static Entry parseCaloriesInput(String input, int lastEntryID) throws InvalidInputException {
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

        // Try catch here is needed because if user inputs "calories in chicken c/1000 d/  "",
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
            try {
                String macroString = parts[3].trim();
                macros = getMacrosFromInput(macroString);
            } catch (InvalidInputException e) {
                throw new InvalidInputException(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException(getEmptyMacrosMessage());
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

            if (date.isAfter(LocalDate.now())) {
                throw new InvalidInputException(getDateLaterThanPresentDateMessage());
            }
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(getInvalidDateMessage());
        }
        //@@author owx0130

        lastEntryID++;
        if (command.equals("calories out")) {
            return makeNewOutputEntry(lastEntryID, description, calories, date);
        } else if (macros == null) {
            return makeNewInputEntry(lastEntryID, description, calories, date);
        } else {
            return makeNewInputEntry(lastEntryID, description, calories, date, macros);
        }
    }

    //@@author rexyyong

    /**
     * Parses a string representation of a date and returns a LocalDate object.
     *
     * @param strDate the string representation of the date
     * @return the LocalDate object parsed from the input string
     * @throws DateTimeParseException if the input string cannot be parsed as a valid date
     */
    public static LocalDate getLocalDateFromInput(String strDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(strDate);
        return date;
    }
    //@@author owx0130

    /**
     * Parses a string representation of calories and returns an integer value.
     *
     * @param strCalories the string representation of calories
     * @return the integer value of calories parsed from the input string
     */
    private static int getIntegerCaloriesFromInput(String strCalories) {
        int calories = 0;
        try {
            calories = Integer.parseInt(strCalories);
        } catch (NumberFormatException e) {
            System.out.println(getIncorrectCaloriesInputMessage());
        }
        return calories;
    }

    /**
     * Extracts the description from the input string based on the command and the index of calories.
     *
     * @param inputString   the input string containing the description
     * @param command       the command string indicating the type of entry ("calories in" or "calories out")
     * @param caloriesIndex the index of the "calories" keyword in the input string
     * @return the description extracted from the input string
     */
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

    /**
     * Parses a string containing macros (carbs, proteins, fats) and returns an array of integers.
     * The string should be in the format "carbs,proteins,fats".
     *
     * @param macroString the string containing macros separated by commas
     * @return an array of integers representing macros [carbs, proteins, fats]
     * @throws InvalidInputException if the input string is not in the correct format or contains invalid values
     */
    private static int[] getMacrosFromInput(String macroString) throws InvalidInputException {
        int[] macros = new int[3];
        try {
            String[] macroParts = macroString.split(",");
            int idx = 0;
            for (String macro : macroParts) {
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
            throw new InvalidInputException(getIncorrectMacrosInputMessage());
        }
        return macros;
    }

    /**
     * Checks if the calorie value is a positive integer.
     *
     * @param calories the calorie value to check
     * @throws InvalidInputException if the calorie value is not a positive integer
     */
    private static void checkCaloriesIsPositiveInteger(int calories) throws InvalidInputException {
        if (calories <= 0) {
            throw new InvalidInputException(getIncorrectCaloriesInputMessage());
        }
    }

    /**
     * Checks if the inputs (description, calories, and date) are non-empty strings.
     *
     * @param description the description of the entry
     * @param strCalories the string representation of calories
     * @param date        the date of the entry
     * @throws InvalidInputException if any of the inputs are empty strings
     */
    private static void checkInputsAreNonEmpty(String description, String strCalories, String date)
            throws InvalidInputException {
        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strCalories.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException(getWhitespaceInInputMessage());
        }
    }

    /**
     * Checks if the keywords for calories and date exist in the input string.
     *
     * @param caloriesIndex the index of the "c/" keyword in the input
     * @param dateIndex     the index of the "date/" keyword in the input
     * @throws InvalidInputException if the keywords are missing
     */
    private static void checkKeywordsExist(int caloriesIndex, int dateIndex) throws InvalidInputException {
        //check that c/ and date/ keywords exist in the input, else throw exception
        if (caloriesIndex == -1 || dateIndex == -1) {
            throw new InvalidInputException(getCaloriesMissingKeywordsMessage());
        }
    }

    /**
     * Checks if the keywords for calories and date are correctly ordered in the input string.
     *
     * @param caloriesIndex the index of the "c/" keyword in the input
     * @param dateIndex     the index of the "date/" keyword in the input
     * @param macrosIndex   the index of the "macros/" keyword in the input
     * @throws InvalidInputException if the keywords are not correctly ordered
     */
    private static void checkKeywordsCorrectlyOrdered(int caloriesIndex, int dateIndex, int macrosIndex)
            throws InvalidInputException {
        if ((macrosIndex != -1 && !(caloriesIndex < dateIndex && dateIndex < macrosIndex)) ||
                (macrosIndex == -1 && !(caloriesIndex < dateIndex))) {
            throw new InvalidInputException(getCaloriesIncorrectOrderMessage());
        }
    }

    /**
     * Creates a new output entry with the given description, calories, and date.
     *
     * @param description the description of the entry
     * @param calories    the number of calories
     * @param date        the date of the entry
     * @return a new OutputEntry object
     */
    private static Entry makeNewOutputEntry(int lastEntryID, String description, int calories, LocalDate date) {
        return new OutputEntry(lastEntryID, description, calories, date);
    }

    /**
     * Creates a new input entry with the given description, calories, and date.
     *
     * @param description the description of the entry
     * @param calories    the number of calories
     * @param date        the date of the entry
     * @return a new InputEntry object
     */
    private static Entry makeNewInputEntry(int lastEntryID, String description, int calories, LocalDate date) {

        return new InputEntry(lastEntryID, description, calories, date);
    }

    /**
     * Creates a new input entry with the given description, calories, date, and food macros.
     *
     * @param description the description of the entry
     * @param calories    the number of calories
     * @param date        the date of the entry
     * @param foodMacros  an array containing food macros (carbs, proteins, fats)
     * @return a new InputEntry object with food macros
     */
    private static Entry makeNewInputEntry(int lastEntryID, String description, int calories, LocalDate date,
                                           int[] foodMacros) {

        Food newFood = new Food(foodMacros[CARBS_IDX], foodMacros[PROTEINS_IDX], foodMacros[FATS_IDX]);

        return new InputEntry(lastEntryID, description, calories, date, newFood);
    }

    //@@author paturikarthik
    public static void findCalorieListEntries(String input, CalorieList originalList) {
        String keyword = input.substring(CALORIES_FIND_LENGTH).trim();
        CalorieList searchList = new CalorieList();
        for (int i = 0; i < originalList.getSize(); i++) {
            Entry entry = originalList.getEntry(i);
            if (entry.getDescription().contains(keyword)) {
                if (entry instanceof InputEntry) {
                    Entry entryToAdd = makeNewInputEntry(entry.getLastEntryID(), entry.getDescription()
                            , ((InputEntry) entry).getCalories(), entry.getDate());
                    searchList.addCalorieEntry(entryToAdd);
                }
            }
        }
        searchList.printFoundCalorieList();
    }
}
