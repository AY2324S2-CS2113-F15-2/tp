//@@author owx0130
package seedu.lifetrack.system.exceptions;

/**
 * Utility class for managing error messages related to invalid input exceptions.
 */
public class InvalidInputExceptionMessage {

    private static final String HEADER = "\t Invalid input!\n";
    private static final String CALORIES_IN_INPUT = "\t Example input: calories in DESCRIPTION " +
            "c/INTEGER_CALORIES d/DATE [m/MACROS]";
    private static final String CALORIES_OUT_INPUT = "\t Example input: calories out DESCRIPTION " +
            "c/INTEGER_CALORIES d/DATE";
    private static final String MACROS_INPUT = "\t Example input: ....... m/CARBS_INT, PROTEIN_INT, FATS_INT";
    private static final String SLEEP_IN_INPUT = "\t Example input: sleep add 7.5 d/2024-03-11" ;
    private static final String HYDRATION_IN_INPUT = "\t Example input: hydration in Milo v/1000 d/2024-04-19" ;
    private static final String USER_SETUP_INPUT = "\t Example input: user set up Tom h/170 w/80 a/25 s/male e/4 g/3";

    //calories list related methods
    public static String getCaloriesIncorrectOrderMessage() {
        String message = "\t Please ensure that you have keyed the input in the correct order!\n";
        return HEADER + message + CALORIES_IN_INPUT;
    }

    public static String getCaloriesMissingKeywordsMessage() {
        String message = "\t Please ensure that you have entered all keywords!\n";
        return HEADER + message + CALORIES_IN_INPUT;
    }

    public static String getWhitespaceInInputMessage() {
        String message = "\t Please ensure that there is no whitespace in your input!\n";
        return HEADER + message + CALORIES_IN_INPUT;
    }

    public static String getEmptyMacrosMessage() {
        String message = "\t Your macronutrients field is empty!\n";
        return HEADER + message + MACROS_INPUT;
    }

    public static String getIncompleteMacrosMessage() {
        String message = "\t Please ensure that all macronutrients fields are filled up!\n";
        return HEADER + message + MACROS_INPUT;
    }

    public static String getWhitespaceInMacrosInputMessage() {
        String message = "\t Please ensure that there is no whitespace in your macros input!\n";
        return HEADER + message + MACROS_INPUT;
    }

    public static String getMacrosInCaloriesOutMessage() {
        String message = "\t Calorie output entry cannot have macros!\n";
        return HEADER + message + CALORIES_OUT_INPUT;
    }

    //hydration list related methods
    public static String getHydrationMissingKeywordMessage() {
        String message = "\t Please ensure that you have entered all keywords!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    public static String getSleepMissingKeywordMessage() {
        String message = "\t Please ensure that you have entered all keywords!\n";
        return HEADER + message + SLEEP_IN_INPUT;
    }

    public static String getHydrationIncorrectOrderMessage() {
        String message = "\t Please ensure that you have keyed the input in the correct order!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    public static String getHydrationEmptyDescriptionMessage() {
        String message = "\t Please ensure that beverage and volume is not empty!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    public static String getHydrationNegativeIntegerVolumeMessage() {
        String message = "\t Please ensure that positive integer value is keyed in for volume!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    // User related Messages
    public static String getOutOfGoalRangeMessage() {
        return "\t Please key in a number between 1 and 5! 1 being quick fat loss " +
                "and 5 being quick bulking";
    }

    public static String getOutOfExerciseLevelsRangeMessage() {
        return "\t Please key in a number between 1 and 5! 1 being little exercise done per week and 5 being" +
                " very heavy levels of exercise done per week.";
    }

    public static String getEmptyUserSetupInputMessage() {
        return HEADER + "\t Please key in the relevant user fields!\n" + USER_SETUP_INPUT;
    }
}
