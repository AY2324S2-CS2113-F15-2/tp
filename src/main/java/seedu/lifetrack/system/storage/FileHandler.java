//@@author owx0130
package seedu.lifetrack.system.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import seedu.lifetrack.Entry;
import seedu.lifetrack.system.exceptions.FileHandlerException;
import seedu.lifetrack.user.User;

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileDateLaterThanCurrentMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileEmptyDescriptionMessage;

public class FileHandler {

    //general list constants
    protected final int ENTRYID_INDEX = 0;
    protected final int DATE_INDEX = 1;
    protected final int DESCRIPTION_INDEX = 2;

    //NumberFormatException exception message prefix
    protected final String NF_EXCEPTION_PREFIX = "For input string: \"";

    //error message for IO exception
    protected final String message = "\t Unable to write to file!";

    protected String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    protected void checkDateNotLaterThanCurrent(int lineNumber, LocalDate date) throws FileHandlerException {
        if (date.isAfter(LocalDate.now())) {
            throw new FileHandlerException(getFileDateLaterThanCurrentMessage(lineNumber, filePath));
        }
    }

    protected void checkNonEmptyDescription(int lineNumber, String description) throws FileHandlerException {
        if (description.equals("")) {
            throw new FileHandlerException(getFileEmptyDescriptionMessage(lineNumber, filePath));
        }
    }

    protected void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void writeEntries(ArrayList<Entry> entries) {
        try {
            String newData = "";
            for (Entry entry : entries) {
                newData += entry.toFileFriendlyString() + System.lineSeparator();
            }
            writeToFile(newData);
        } catch (IOException e) {
            System.out.println(message);
        }
    }
}
