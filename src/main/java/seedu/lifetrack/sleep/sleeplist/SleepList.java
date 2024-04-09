//@@author a-wild-chocolate
package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.system.exceptions.ErrorMessages;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserSleep;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.ui.SleepListUi;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SleepList {

    private ArrayList<Entry> sleepList;
    private FileHandler fileHandler;
    private int lastSleepEntryID;

    //constructor for JUnit tests
    public SleepList() {
        sleepList = new ArrayList<>();
    }

    //constructor for usage in terminal
    public SleepList(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            sleepList = fileHandler.getSleepEntriesFromFile();
            this.lastSleepEntryID = loadLastEntryID();
        } catch (FileNotFoundException e) {
            sleepList = new ArrayList<>();
            System.out.println(ErrorMessages.getFileNotFoundMessage());
        }
    }

    private void updateFile() {
        if (fileHandler != null) {
            fileHandler.writeEntries(sleepList);
        }
    }

    public Entry getSleep(int index) {
        assert index >= 0 && index < sleepList.size() : "Index out of bounds";
        return sleepList.get(index);
    }

    public void addSleep(String input) {
        try {
            Entry newSleep = ParserSleep.parseSleepInput(input, lastSleepEntryID);
            sleepList.add(newSleep);
            updateFile();
            SleepListUi.printNewSleepEntry(newSleep);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteSleep(String line) {
        try {
            int index = Integer.parseInt(line.split(" ")[2]) ; //User input format: sleep delete INDEX, here get index
            Entry toDelete = sleepList.get(index-1);
            sleepList.remove(index - 1);
            updateFile();
            SleepListUi.successfulDeletedMessage(toDelete);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(SleepListUi.deleteLogIndexMessage());
        } catch (NumberFormatException e) {
            System.out.println(SleepListUi.deleteLogNumberMessage());
        }
    }

    public void printSleepList() {
        if (this.sleepList.isEmpty()) {
            SleepListUi.emptyListMessage();
        } else {
            SleepListUi.sleepListHeader();
            for (int i = 0; i < sleepList.size(); i++) {
                Entry entry = sleepList.get(i);
                System.out.println("\t " + (i + 1) + ". " + entry);
            }
        }
    }

    public int getSize() {
        return sleepList.size();
    }

    private int loadLastEntryID() {
        return 0; // Default value if file doesn't exist or error occurs
    }
}
//@@author
