package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectCaloriesInputMessage;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectMacrosInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWhitespaceInInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncompleteMacrosMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getMacrosInCaloriesOutMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getCaloriesIncorrectOrderMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getCaloriesMissingKeywordsMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getWhitespaceInMacrosInputMessage;

class ParserCaloriesTest {

    @Test
    public void parseCaloriesInput_missingKeywords_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger", 0);
        } catch (InvalidInputException e) {
            assertEquals(getCaloriesMissingKeywordsMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/ d/220224", 0);
        } catch (InvalidInputException e) {
            assertEquals(getWhitespaceInInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incorrectlyOrderedInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger d/220224 c/123", 0);
        } catch (InvalidInputException e) {
            assertEquals(getCaloriesIncorrectOrderMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incorrectMacrosInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/123 d/2024-03-22 m/abc", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectMacrosInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incorrectCaloriesInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories out Running c/abc d/220324", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectCaloriesInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteMacrosInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/123 d/220324 m/123,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncompleteMacrosMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_macrosInCaloriesOut_exceptionThrown() {
        try {
            parseCaloriesInput("calories out running c/123 d/220324 m/123,123,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getMacrosInCaloriesOutMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_whitespaceInMacrosInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/123 d/220324 m/123,  ,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getWhitespaceInMacrosInputMessage(), e.getMessage());
        }
    }
}
