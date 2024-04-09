package seedu.lifetrack.system.parser;

import seedu.lifetrack.system.exceptions.InvalidInputException;

import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectSleepInputMessage;

import seedu.lifetrack.sleep.sleeplist.SleepEntry;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//@@author a-wild-chocloate
public class ParserSleep {

    public static SleepEntry parseSleepInput(String input, int lastSleepEntryID) throws InvalidInputException {
        try {
            String strDate = "N/A"; // Default if no strDate is provided
            LocalDate date = null;
            double duration = 0;
            String[] parts = input.split(" ");
            for (String part : parts) {
                if (part.matches("^-?\\d+(\\.\\d+)?$")) {
                    duration = Double.parseDouble(part);
                    if (duration < 0) {
                        throw new InvalidInputException(getIncorrectSleepInputMessage());
                    }
                } else if (part.startsWith("d/")) {
                    strDate = part.substring(2);
                }
            }
            if (duration == 0) {
                throw new InvalidInputException("Please ensure that you have keyed in the correct format: " +
                        "sleep add <duration> d/<strDate>");
            }
            //@author
            //@@author rexyyong
            // Parse str date to date of type LocalDate
            try {
                date = getLocalDateFromInput(strDate);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Invalid date format");
            }
            lastSleepEntryID++;
            //@@author
            return new SleepEntry(lastSleepEntryID, duration, date);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<date>");
        }
    }

    //@@author rexyyong
    public static LocalDate getLocalDateFromInput(String strDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(strDate);
        return date;
    }
    //@@author
}
