//@@author owx0130
package seedu.lifetrack.system.exceptions;

import static seedu.lifetrack.ui.Ui.printLine;

/**
 * Utility class for managing error messages related to file handler exceptions.
 */
public class FileHandlerExceptionMessage {

    //general error messages
    private static final String TOO_FEW_ARGUMENTS = "\t Too few arguments were given in line ";
    private static final String TOO_MANY_ARGUMENTS = "\t Too many arguments were given in line ";
    private static final String EMPTY_DESC = "\t An empty description was given in line ";
    private static final String INVALID_DATE = "\t An invalid date was given in line ";

    //calories list error messages
    private static final String INVALID_ENTRYID = "\t An invalid EntryID value was given in line ";
    private static final String INVALID_CALORIES = "\t An invalid calories value was given in line ";
    private static final String INVALID_CARBS = "\t An invalid carbohydrates value was given in line ";
    private static final String INVALID_PROTEINS = "\t An invalid proteins value was given in line ";
    private static final String INVALID_FATS = "\t An invalid fats value was given in line ";
    private static final String INVALID_ENTRYTYPE = "\t An invalid entry type was given in line ";
    private static final String TOO_FEW_MACROS = "\t Too few macronutrient arguments were given in line ";
    private static final String MACROS_IN_OUTPUT = "\t Macronutrient arguments were given in line ";


    //messages to provide user guidance (general)
    private static final String DATE_FORMAT_GUIDANCE = "\t Please ensure that a date in format YYYY-MM-DD is given";
    private static final String DATE_PERIOD_GUIDANCE = "\t Please ensure that a date no later than today is given";
    private static final String DESC_GUIDANCE = "\t Please ensure that a non-empty description is given";
    private static final String POS_INT_GUIDANCE = "\t Please ensure that a positive integer value is given";
    private static final String INTEGER_GUIDANCE = "\t Please ensure that an integer value is given";

    //messages to provide user guidance (calories)
    private static final String CALORIES_FORMAT_GUIDANCE = "\t Please ensure that only either five or eight " +
            "(including macronutrients) arguments are given!";
    private static final String ENTRYTYPE_GUIDANCE = "\t Please ensure the entry type is only either \"C_IN\" or " +
            "\"C_OUT\"";
    private static final String MACROS_GUIDANCE = "\t Please ensure that three macronutrient arguments are given!";
    private static final String OUTPUT_GUIDANCE = "\t Do not provide macronutrients for a calorie output " +
            "(C_OUT) entry!";


    private static String getLineNotAddedMessage(int lineNumber, String filePath) {
        if (filePath.equals("data/caloriesData.txt")) {
            return "\t Line " + lineNumber + " was not added into the calories list due to corrupt data!\n";
        } else if (filePath.equals("data/hydrationData.txt")) {
            return "\t Line " + lineNumber + " was added into the hydration list due to corrupt data!\n";
        } else if (filePath.equals("data/sleepData.txt")){
            return "\t Line " + lineNumber + " was added into the sleep list due to corrupt data!\n";
        } else {
            return "\t The user was not set due to corrupt data!\n";
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

    //calories list related messages
    public static String getFileCaloriesTooManyArgumentsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_MANY_ARGUMENTS + lineNumber + " of " + filePath + "!\n" + suffix + CALORIES_FORMAT_GUIDANCE;
    }

    public static String getFileCaloriesTooFewArgumentsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return TOO_FEW_ARGUMENTS + lineNumber + " of " + filePath + "!\n" + suffix + CALORIES_FORMAT_GUIDANCE;
    }

    public static String getFileInvalidEntryIDMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_ENTRYID + lineNumber + " of " + filePath + "!\n" + suffix + INTEGER_GUIDANCE;
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
}
