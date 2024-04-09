package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.system.parser.ParserSleep.parseSleepInput;

public class ParserSleepTest {
    @Test
    public void parseSleepInput_inputContains2Duration_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add 8.0 9.2";
        // Call methods to test
        try {
            parseSleepInput(invalidInput, 0);
        } catch (InvalidInputException e) {
            assertEquals("Invalid date format", e.getMessage());
        }
    }
    @Test
    public void parseSleepInput_inputContains2Date_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add d/110324 d/280524";
        // Call methods to test
        try {
            parseSleepInput(invalidInput, 0);
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<strDate>", e.getMessage());
        }
    }
    @Test
    public void parseSleepInput_inputMissingDuration_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add d/110324";
        // Call methods to test
        try {
            parseSleepInput(invalidInput, 0);
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<strDate>", e.getMessage());
        }
    }

    @Test
    public void parseSleepInput_inputNonPositiveValueForDuration_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add -2 d/110324";

        // Call methods to test
        try {
            parseSleepInput(invalidInput, 0);
        } catch (InvalidInputException e) {
            assertEquals("\t Please input only positive real number into the sleep duration field!"
                    , e.getMessage());
        }
    }
    @Test
    public void parseLiquidInput_missingKeywords_exceptionThrown() {

        try {
            parseSleepInput("sleep add", 0);
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<strDate>", e.getMessage());
        }
    }
}
