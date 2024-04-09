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
            parseSleepInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n"+"\t Please ensure that you have entered all keywords!\n"+
                    "\t Example input: sleep add 7.5 d/2024-03-11", e.getMessage());
        }
    }
    @Test
    public void parseSleepInput_inputContains2Date_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add d/2024-12-12 d/2024-11-11";
        // Call methods to test
        try {
            parseSleepInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<strDate>", e.getMessage());
        }
    }
    @Test
    public void parseSleepInput_inputMissingDuration_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add d/2024-02-11";
        // Call methods to test
        try {
            parseSleepInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<strDate>", e.getMessage());
        }
    }

    @Test
    public void parseSleepInput_inputNonPositiveValueForDuration_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "sleep add -2 d/2024-03-11";

        // Call methods to test
        try {
            parseSleepInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Please input one positive real number into the sleep duration field!"
                    , e.getMessage());
        }
    }
    @Test
    public void parseSleepInput_missingKeywords_exceptionThrown() {

        try {
            parseSleepInput("sleep add");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n"+"\t Please ensure that you have entered all keywords!\n"+
                    "\t Example input: sleep add 7.5 d/2024-03-11", e.getMessage());
        }
    }
}
