//@@author owx0130
package seedu.lifetrack.system.exceptions;

import static seedu.lifetrack.ui.Ui.printLine;

/**
 * Utility class for managing error messages related to file handler exceptions.
 */
public class FileHandlerExceptionMessage {

    //prefixes for error messages
    private static final String INVALID_ENTRYID = "\t An invalid EntryID value was given in line ";
    private static final String INVALID_CALORIES = "\t An invalid calories value was given in line ";
    private static final String INVALID_DATE = "\t An invalid date was given in line ";
    private static final String INVALID_CARBS = "\t An invalid carbohydrates value was given in line ";

    //messages to provide user guidance
    private static final String INTEGER_GUIDANCE = "\t Please ensure that an integer value is given";
    private static final String DATE_FORMAT_GUIDANCE = "\t Please ensure that a date in format YYYY-MM-DD is given";
    private static final String DATE_PERIOD_GUIDANCE = "\t Please ensure that a date no later than today is given";

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

    public static String getFileInvalidEntryIDMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_ENTRYID + lineNumber + " of " + filePath + "!\n" + suffix + INTEGER_GUIDANCE;
    }

    public static String getFileInvalidCaloriesMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_CALORIES + lineNumber + " of " + filePath + "!\n" + suffix + INTEGER_GUIDANCE;
    }

    public static String getFileInvalidCarbsMessage(int lineNumber, String filePath) {
        String suffix = getLineNotAddedMessage(lineNumber, filePath);
        printLine();
        return INVALID_CARBS + lineNumber + " of " + filePath + "!\n" + suffix + INTEGER_GUIDANCE;
    }

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
}
