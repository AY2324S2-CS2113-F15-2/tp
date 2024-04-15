//@@author owx0130
package seedu.lifetrack.system.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.Entry;
import seedu.lifetrack.sleep.sleeplist.SleepEntry;
import seedu.lifetrack.system.exceptions.FileHandlerException;

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidEntryIDMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileSleepTooFewFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileSleepTooManyFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidDateMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidDurationMessage;

public class SleepFileHandler extends FileHandler {

    //sleep list constants
    private final int DURATION_INDEX = 2;

    public SleepFileHandler(String filePath) {
        super(filePath);
    }

    private void checkCorrectNumberOfFields(int lineNumber, int dataLength) throws FileHandlerException {
        if (dataLength < 3) {
            throw new FileHandlerException(getFileSleepTooFewFieldsMessage(lineNumber, filePath));
        } else if (dataLength > 3) {
            throw new FileHandlerException(getFileSleepTooManyFieldsMessage(lineNumber, filePath));
        }
    }

    private void checkDurationIsPositive(int lineNumber, double duration) throws FileHandlerException {
        if (duration <= 0) {
            throw new FileHandlerException(getFileInvalidDurationMessage(lineNumber, filePath));
        }
    }

    private void getSingleSleepEntry(ArrayList<Entry> entries, String[] words, int lineNumber)
            throws FileHandlerException {
        checkCorrectNumberOfFields(lineNumber, words.length);
        int entryID = Integer.parseInt(words[ENTRYID_INDEX]);
        checkPositiveEntryID(lineNumber, entryID);
        LocalDate date = LocalDate.parse(words[DATE_INDEX]);
        checkDateNotLaterThanCurrent(lineNumber, date);
        double duration = Double.parseDouble(words[DURATION_INDEX]);
        checkDurationIsPositive(lineNumber, duration);
        entries.add(new SleepEntry(entryID, duration, date));
    }

    public ArrayList<Entry> getSleepEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        int i = 1;
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            try {
                getSingleSleepEntry(entries, words, i);
            } catch (NumberFormatException e) {
                if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[ENTRYID_INDEX] + "\"")) {
                    System.out.println(getFileInvalidEntryIDMessage(i, filePath));
                } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[DURATION_INDEX] + "\"")) {
                    System.out.println(getFileInvalidDurationMessage(i, filePath));
                }
            } catch (DateTimeParseException e) {
                System.out.println(getFileInvalidDateMessage(i, filePath));
            } catch (FileHandlerException e) {
                System.out.println(e.getMessage());
            } finally {
                i++;
            }
        }
        s.close();
        return entries;
    }
}
