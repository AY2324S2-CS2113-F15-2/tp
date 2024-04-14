//@@author owx0130
package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;

import java.time.LocalDate;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncorrectCaloriesInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncorrectMacrosInputMessage;
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
            parseCaloriesInput("calories in burger c/ d/2024-02-02", 0);
        } catch (InvalidInputException e) {
            assertEquals(getWhitespaceInInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incorrectlyOrderedInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger d/2024-02-02 c/123", 0);
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
            parseCaloriesInput("calories out Running c/abc d/2024-02-02", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectCaloriesInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteMacrosInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/123 d/2024-02-02 m/123,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncompleteMacrosMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_macrosInCaloriesOut_exceptionThrown() {
        try {
            parseCaloriesInput("calories out running c/123 d/2024-02-02 m/123,123,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getMacrosInCaloriesOutMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_whitespaceInMacrosInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/123 d/2024-02-02 m/123,  ,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getWhitespaceInMacrosInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_negativeMacrosInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/123 d/2024-02-02 m/123,-3,132", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectMacrosInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_negativeCaloriesInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in burger c/-123 d/2024-02-02 m/123,123,123", 0);
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectCaloriesInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_correctCaloriesInInput_entryReturned() {
        try {
            InputEntry expectedEntry = new InputEntry(1, "burger", 123, LocalDate.parse("2024-02-02"));
            InputEntry entry = (InputEntry)parseCaloriesInput("calories in burger c/123 d/2024-02-02", 0);
            assertEquals(expectedEntry.getEntryID(), entry.getEntryID());
            assertEquals(expectedEntry.getDescription(), entry.getDescription());
            assertEquals(expectedEntry.getDate(), entry.getDate());
            assertEquals(expectedEntry.getCalories(), entry.getCalories());
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectCaloriesInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_correctCaloriesInInputWithMacros_entryReturned() {
        try {
            Food expectedFood = new Food(10, 10, 10);
            InputEntry expectedEntry = new InputEntry(1, "burger", 123, LocalDate.parse("2024-02-02"), expectedFood);
            InputEntry actualEntry = 
                    (InputEntry)parseCaloriesInput("calories in burger c/123 d/2024-02-02 m/10,10,10", 0);
            assertEquals(expectedEntry.getEntryID(), actualEntry.getEntryID());
            assertEquals(expectedEntry.getDescription(), actualEntry.getDescription());
            assertEquals(expectedEntry.getDate(), actualEntry.getDate());
            assertEquals(expectedEntry.getCalories(), actualEntry.getCalories());
            Food actualFood = actualEntry.getFood();
            assertEquals(expectedFood.getCarbohydrates(), actualFood.getCarbohydrates());
            assertEquals(expectedFood.getProteins(), actualFood.getProteins());
            assertEquals(expectedFood.getFats(), actualFood.getFats());
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectCaloriesInputMessage(), e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_correctCaloriesOutInput_entryReturned() {
        try {
            OutputEntry expectedEntry = new OutputEntry(1, "run", 123, LocalDate.parse("2024-02-02"));
            OutputEntry entry = (OutputEntry)parseCaloriesInput("calories out run c/123 d/2024-02-02", 0);
            assertEquals(expectedEntry.getEntryID(), entry.getEntryID());
            assertEquals(expectedEntry.getDescription(), entry.getDescription());
            assertEquals(expectedEntry.getDate(), entry.getDate());
            assertEquals(expectedEntry.getCalories(), entry.getCalories());
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectCaloriesInputMessage(), e.getMessage());
        }
    }
}
