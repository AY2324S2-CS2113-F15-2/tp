//@@author owx0130
package seedu.lifetrack.system.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.Entry;
import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.system.exceptions.FileHandlerException;

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidEntryIDMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidVolumeMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileHydrationTooFewFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileHydrationTooManyFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidDateMessage;

public class HydrationFileHandler extends FileHandler {

    //class-level member for lastEntryID calories
    public static int maxHydrationID = 0;

    //hydration list constants
    private final int VOLUME_INDEX = 3;

    public HydrationFileHandler(String filePath) {
        super(filePath);
    }

    private void calculateMaxHydrationEntry(int entryID) {
        if (entryID > maxHydrationID) {
            maxHydrationID = entryID;
        }
    }

    private void checkCorrectNumberOfFields(int lineNumber, int dataLength) throws FileHandlerException {
        if (dataLength < 4) {
            throw new FileHandlerException(getFileHydrationTooFewFieldsMessage(lineNumber, filePath));
        } else if (dataLength > 4) {
            throw new FileHandlerException(getFileHydrationTooManyFieldsMessage(lineNumber, filePath));
        }
    }

    private void checkVolumeIsPositive(int lineNumber, int volume) throws FileHandlerException {
        if (volume <= 0) {
            throw new FileHandlerException(getFileInvalidVolumeMessage(lineNumber, filePath));
        }
    }

    private void getSingleHydrationEntry(ArrayList<Entry> entries, String[] words, int lineNumber)
            throws FileHandlerException {
        checkCorrectNumberOfFields(lineNumber, words.length);
        int entryID = Integer.parseInt(words[ENTRYID_INDEX]);
        calculateMaxHydrationEntry(entryID);
        LocalDate date = LocalDate.parse(words[DATE_INDEX]);
        checkDateNotLaterThanCurrent(lineNumber, date);
        String description = words[DESCRIPTION_INDEX];
        checkNonEmptyDescription(lineNumber, description);
        int volume = Integer.parseInt(words[VOLUME_INDEX]);
        checkVolumeIsPositive(lineNumber, volume);
        entries.add(new HydrationEntry(entryID, description, volume, date));
    }

    public ArrayList<Entry> getHydrationEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        int i = 1;
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            try {
                getSingleHydrationEntry(entries, words, i);
            } catch (NumberFormatException e) {
                if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[ENTRYID_INDEX] + "\"")) {
                    System.out.println(getFileInvalidEntryIDMessage(i, filePath));
                } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[VOLUME_INDEX] + "\"")) {
                    System.out.println(getFileInvalidVolumeMessage(i, filePath));
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
