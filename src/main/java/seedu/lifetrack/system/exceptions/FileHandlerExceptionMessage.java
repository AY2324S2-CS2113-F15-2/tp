//@@author owx0130
package seedu.lifetrack.system.exceptions;

import static seedu.lifetrack.ui.Ui.printLine;

/**
 * Utility class for managing error messages related to file handler exceptions.
 */
public class FileHandlerExceptionMessage {

    //general error messages
    private static final String TOO_FEW_FIELDS = "\t Too few fields were given in line ";
    private static final String TOO_MANY_FIELDS = "\t Too many fields were given in line ";
    private static final String EMPTY_DESC = "\t An empty description was given in line ";
    private static final String INVALID_DATE = "\t An invalid date was given in line ";
    private static final String INVALID_ENTRYID = "\t An invalid EntryID value was given in line ";

    //calories list error messages
    private static final String INVALID_CALORIES = "\t An invalid calories value was given in line ";
    private static final String INVALID_CARBS = "\t An invalid carbohydrates value was given in line ";
    private static final String INVALID_PROTEINS = "\t An invalid proteins value was given in line ";
    private static final String INVALID_FATS = "\t An invalid fats value was given in line ";
    private static final String INVALID_ENTRYTYPE = "\t An invalid entry type was given in line ";
    private static final String TOO_FEW_MACROS = "\t Too few macronutrient fields were given in line ";
    private static final String MACROS_IN_OUTPUT = "\t Macronutrient fields were given in line ";

    //hydration list error messages
    private static final String INVALID_VOLUME = "\t An invalid volume value was given in line ";

    //sleep list error messages
    private static final String INVALID_DURATION = "\t An invalid duration value was given in line ";

    //user error messages
    private static final String USER_NOT_ADDED = "\t User was not set up due to corrupt data!\n";
    private static final String EMPTY_NAME = "\t An empty name was given in ";
    private static final String INVALID_HEIGHT = "\t An invalid height was given in ";
    private static final String INVALID_WEIGHT = "\t An invalid weight was given in ";
    private static final String INVALID_AGE = "\t An invalid age was given in ";
    private static final String INVALID_SEX = "\t An invalid sex was given in ";
    private static final String INVALID_EX_LEVELS = "\t An invalid exercise level was given in ";
    private static final String INVALID_GOAL = "\t An invalid goal was given in ";
    private static final String INVALID_REQ_CAL = "\t An invalid required calories value was given in ";

    //messages to provide user guidance (general)
    private static final String DATE_FORMAT_GUIDANCE = "\t Please ensure that a date in format YYYY-MM-DD is given";
    private static final String DATE_PERIOD_GUIDANCE = "\t Please ensure that a date no later than today is given";
    private static final String DESC_GUIDANCE = "\t Please ensure that a non-empty description is given";
    private static final String POS_INT_GUIDANCE = "\t Please ensure that a positive integer value is given";
    private static final String POS_FLOAT_GUIDANCE = "\t Please ensure that a positive float value is given";
    private static final String INTEGER_GUIDANCE = "\t Please ensure that an integer value is given";

    //messages to provide user guidance (calories)
    private static final String CALORIES_FIELDS_GUIDANCE = "\t Please ensure that only either five or eight " +
            "(including macronutrients) fields are provided!";
    private static final String ENTRYTYPE_GUIDANCE = "\t Please ensure the entry type is only either \"C_IN\" or " +
            "\"C_OUT\"";
    private static final String MACROS_GUIDANCE = "\t Please ensure that three macronutrient fields are given!";
    private static final String OUTPUT_GUIDANCE = "\t Do not provide macronutrients for a calorie output " +
            "(C_OUT) entry!";

    //messages to provide user guidance (hydration)
    private static final String HYDRATION_FIELDS_GUIDANCE = "\t Please ensure that four fields are provided!";

    //messages to provide user guidance (sleep)
    private static final String SLEEP_FIELDS_GUIDANCE = "\t Please ensure that three fields are provided!";
    
    //messages to provide user guidance (user)
    private static final String USER_FIELDS_GUIDANCE = "\t Please ensure that eight fields are provided!";
    private static final String NAME_GUIDANCE = "\t Please ensure that the name field is not empty!";
    private static final String HEIGHT_GUIDANCE = "\t Please ensure that an integer between 90 and 225 (cm) " +
            "is provided!";
    private static final String WEIGHT_GUIDANCE = "\t Please ensure that an integer between 30 and 200 (kg) " +
            "is provided!";
    private static final String AGE_GUIDANCE = "\t Please ensure that an integer between 13 and 30 (years) " +
            "is provided!";
    private static final String SEX_GUIDANCE = "\t Please ensure that the sex field is only either "+
            "\"male\" or \"female\"!";
    private static final String EX_LEVELS_GOALS_GUIDANCE = "\t Please ensure that an integer between 1 to 5 is provided!";

    private static String getLineNotAddedMessage(int lineNumber, String filePath) {
        if (filePath.equals("data/caloriesData.txt")) {
            return "\t Line " + lineNumber + " was not added into the calories list due to corrupt data!\n";
        } else if (filePath.equals("data/hydrationData.txt")) {
            return "\t Line " + lineNumber + " was not added into the hydration list due to corrupt data!\n";
        } else {
            return "\t Line " + lineNumber + " was not added into the sleep list due to corrupt data!\n";
        }
    }

    //general messages
    public static String getFileInvalidDateMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_DATE + lineNumber + " of " + filePath + "!\n" + suffix + DATE_FORMAT_GUIDANCE;
    }

    public static String getFileDateLaterThanCurrentMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_DATE + lineNumber + " of " + filePath + "!\n" + suffix + DATE_PERIOD_GUIDANCE;
    }

    public static String getFileEmptyDescriptionMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return EMPTY_DESC + lineNumber + " of " + filePath + "!\n" + suffix + DESC_GUIDANCE;
    }

    public static String getFileInvalidEntryIDMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_ENTRYID + lineNumber + " of " + filePath + "!\n" + suffix + INTEGER_GUIDANCE;
    }

    //calories list related messages
    public static String getFileCaloriesTooManyFieldsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_MANY_FIELDS + lineNumber + " of " + filePath + "!\n" + suffix + CALORIES_FIELDS_GUIDANCE;
    }

    public static String getFileCaloriesTooFewFieldsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_FEW_FIELDS + lineNumber + " of " + filePath + "!\n" + suffix + CALORIES_FIELDS_GUIDANCE;
    }

    public static String getFileInvalidCaloriesMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_CALORIES + lineNumber + " of " + filePath + "!\n" + suffix + POS_INT_GUIDANCE;
    }

    public static String getFileInvalidCarbsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_CARBS + lineNumber + " of " + filePath + "!\n" + suffix + POS_INT_GUIDANCE;
    }

    public static String getFileInvalidProteinsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_PROTEINS + lineNumber + " of " + filePath + "!\n" + suffix + POS_INT_GUIDANCE;
    }

    public static String getFileInvalidFatsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_FATS + lineNumber + " of " + filePath + "!\n" + suffix + POS_INT_GUIDANCE;
    }

    public static String getFileInvalidEntryTypeMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_ENTRYTYPE + lineNumber + " of " + filePath + "!\n" + suffix + ENTRYTYPE_GUIDANCE;
    }

    public static String getFileTooFewMacrosMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_FEW_MACROS + lineNumber + " of " + filePath + "!\n" + suffix + MACROS_GUIDANCE;
    }

    public static String getFileMacrosInOutputMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return MACROS_IN_OUTPUT + lineNumber + " of " + filePath + "!\n" + suffix + OUTPUT_GUIDANCE;
    }

    //hydration list related messages
    public static String getFileHydrationTooManyFieldsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_MANY_FIELDS + lineNumber + " of " + filePath + "!\n" + suffix + HYDRATION_FIELDS_GUIDANCE;
    }

    public static String getFileHydrationTooFewFieldsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_FEW_FIELDS + lineNumber + " of " + filePath + "!\n" + suffix + HYDRATION_FIELDS_GUIDANCE;
    }

    public static String getFileInvalidVolumeMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_VOLUME + lineNumber + " of " + filePath + "!\n" + suffix + POS_INT_GUIDANCE;
    }

    //sleep list related messages
    public static String getFileSleepTooManyFieldsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_MANY_FIELDS + lineNumber + " of " + filePath + "!\n" + suffix + SLEEP_FIELDS_GUIDANCE;
    }

    public static String getFileSleepTooFewFieldsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_FEW_FIELDS + lineNumber + " of " + filePath + "!\n" + suffix + SLEEP_FIELDS_GUIDANCE;
    }

    public static String getFileInvalidDurationMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_DURATION + lineNumber + " of " + filePath + "!\n" + suffix + POS_FLOAT_GUIDANCE;
    }

    //user related messages
    public static String getFileUserTooManyFieldsMessage(String filePath) {
        printLine();
        return TOO_MANY_FIELDS + " 1 of " + filePath + "!\n" + USER_NOT_ADDED + USER_FIELDS_GUIDANCE;
    }

    public static String getFileUserTooFewFieldsMessage(String filePath) {
        printLine();
        return TOO_FEW_FIELDS + " 1 of " + filePath + "!\n" + USER_NOT_ADDED + USER_FIELDS_GUIDANCE;
    }

    public static String getFileUserEmptyNameMessage(String filePath) {
        printLine();
        return EMPTY_NAME + filePath + "!\n" + USER_NOT_ADDED + NAME_GUIDANCE;
    }

    public static String getFileInvalidHeightMessage(String filePath) {
        printLine();
        return INVALID_HEIGHT + filePath + "!\n" + USER_NOT_ADDED + HEIGHT_GUIDANCE;
    }

    public static String getFileInvalidWeightMessage(String filePath) {
        printLine();
        return INVALID_WEIGHT + filePath + "!\n" + USER_NOT_ADDED + WEIGHT_GUIDANCE;
    }

    public static String getFileInvalidAgeMessage(String filePath) {
        printLine();
        return INVALID_AGE + filePath + "!\n" + USER_NOT_ADDED + AGE_GUIDANCE;
    }

    public static String getFileInvalidSexMessage(String filePath) {
        printLine();
        return INVALID_SEX + filePath + "!\n" + USER_NOT_ADDED + SEX_GUIDANCE;
    }

    public static String getFileInvalidExerciseLevelMessage(String filePath) {
        printLine();
        return INVALID_EX_LEVELS + filePath + "!\n" + USER_NOT_ADDED + EX_LEVELS_GOALS_GUIDANCE;
    }

    public static String getFileInvalidGoalMessage(String filePath) {
        printLine();
        return INVALID_GOAL + filePath + "!\n" + USER_NOT_ADDED + EX_LEVELS_GOALS_GUIDANCE;
    }

    public static String getFileInvalidReqCalMessage(String filePath) {
        printLine();
        return INVALID_REQ_CAL + filePath + "!\n" + USER_NOT_ADDED + POS_INT_GUIDANCE;
    }
}
