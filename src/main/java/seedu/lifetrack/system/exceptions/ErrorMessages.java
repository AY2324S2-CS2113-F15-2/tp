package seedu.lifetrack.system.exceptions;

/**
 * Utility class for managing error messages related to the application.
 */
public class ErrorMessages {

    private static final String WHITESPACE = "         ";

    public static void printIndexOutOfBoundsError(){
        System.out.println("\t Sorry, this index is invalid. Please enter a positive integer " +
                "within the size of the list.");
    }

    public static void printNumberFormatError() {
        System.out.println("\t Please enter a valid number within the command");
    }

    public static String getIncorrectCaloriesInputMessage() {
        return "\t Please input only positive integers into the calories field!";
    }

    public static String getIncorrectVolumeInputMessage() {
        return "\t Please input only positive integers into the volume field!";
    }

    public static String getIncorrectMacrosInputMessage() {
        return "\t Please input only positive integers into the macronutrients field!";
    }

    public static String getInvalidNumberOfSetUpInputs() {
        return "\t Sorry, this command is invalid. Please enter the setup command in the following format " +
                "user setup {NAME} h/{HEIGHT} w/{WEIGHT} a/{AGE} s/{SEX} e/{EXERCISE LEVELS} g/{GOAL}";
    }

    public static String getInvalidGoalNumberMessage() {
        return "\t Invalid input for goal number. Please enter a number between 1 and 5.";
    }

    public static String getInvalidExerciseLevelsNumberMessage() {
        return "\t Invalid input for exercise level. Please enter a number between 1 and 5.";
    }

    public static String getIOExceptionMessage() {
        return "\t Unable to write to file!";
    }

    public static String getFileNotFoundMessage() {
        return WHITESPACE + "No file found! The initialised list will be empty.";
    }

    public static String getIncorrectSleepInputMessage() {
        return "\t Please input one positive real number into the sleep duration field!";
    }

    public static String getIncorrectSleepDateInputMessage() {
        return "\t Error: Date must be in YYYY-MM-DD format.!";
    }

    public static String getHeightOutOfRangeMessage() {
        return "\t Please enter a valid height!";
    }
    public static String getInvalidHeightNumberMessage(){
        return "\t Please enter your height(in cm) as an integer!";
    }
    public static String getWeightOutOfRangeMessage(){
        return "\t Please enter a valid weight!";
    }
    public static String  getInvalidWeightNumberMessage(){
        return "\t Please enter your weight(in kg) as an integer!";
    }
    public static String getUnderAgeMessage(){
        return "\t You are too young to use this app :(";
    }

    public static String getAgeOutOfRangeMessage(){
        return "\t Please enter a valid age!";
    }

    public static String getInvalidAgeNumberMessage(){
        return "\t Please enter your age as an integer!";
    }
    public static String getTooLongSleepDurationMessage() {
        return "\t Please enter a sleep duration less than 24 hours.";
    }
    public static String getInvalidSleepDateMessage() {
        return "\t Please enter a valid date!";
    }
}
