//@@author a-wild-chocolate
package seedu.lifetrack.system.parser;

import seedu.lifetrack.system.exceptions.InvalidInputException;

import seedu.lifetrack.sleep.sleeplist.SleepEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncorrectSleepInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getTooLongSleepDurationMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getInvalidDateMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getSleepMissingKeywordMessage;

public class ParserSleep {
    private static final int DURATION_IDX = 0;
    private static final int DATE_IDX = 1;
    private static final int STRING_PARTS_LEN = 2;
    private static final String SLEEP_HEADER = "sleep add ";
    private static final String DATE_ICON = "d/";


    public static SleepEntry parseSleepInput(String input) throws InvalidInputException {
        LocalDate date = null;
        double duration = 0;
        int dateIndex = input.indexOf(DATE_ICON);

        checkKeywordsExist(dateIndex);
        assert dateIndex != -1 : "The d/ keyword should exist!";

        String[] parts = input.substring(SLEEP_HEADER.length()).split(DATE_ICON);
        checkValidFormat(parts);

        String strDate = parts[DATE_IDX].trim();
        String strDuration = parts[DURATION_IDX].trim();

        duration = parseDuration(strDuration);
        date = parseDate(strDate);

        SleepEntry newSleep = new SleepEntry(duration, date);


        return newSleep;
    }

    private static double parseDuration(String durationStr) throws InvalidInputException {
        double duration=0;

        try {
            duration = Double.parseDouble(durationStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getIncorrectSleepInputMessage());
        }

        if (duration <= 0) {
            throw new InvalidInputException(getIncorrectSleepInputMessage());
        } else if (duration >= 24) {
            throw new InvalidInputException(getTooLongSleepDurationMessage());
        }
        return duration;
    }


    private static LocalDate parseDate(String strDate) throws InvalidInputException {
        try {
            return LocalDate.parse(strDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(getInvalidDateMessage());
        }
    }

    private static void checkKeywordsExist(int dateIndex) throws InvalidInputException {
        if (dateIndex == -1 ) {
            throw new InvalidInputException(getSleepMissingKeywordMessage());
        }
    }

    private static void checkValidFormat(String[] parts) throws InvalidInputException {
        if (parts.length != STRING_PARTS_LEN || parts[DURATION_IDX].isEmpty()) {
            throw new InvalidInputException("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<strDate>");
        }

    }
}
//@@author
