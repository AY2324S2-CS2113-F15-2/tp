//@@author a-wild-chocolate
package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserSleep;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.ui.SleepListUi;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SleepList {

    private static int DELETE_IDX = 2;
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
            Entry newSleep = ParserSleep.parseSleepInput(input);
            sleepList.add(newSleep);
            updateFile();
            SleepListUi.printNewSleepEntry(newSleep);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteSleep(String line) {
        try {
            int index = Integer.parseInt(line.split(" ")[DELETE_IDX]) ; //User input format: sleep delete ID
            for(int i=0; i<sleepList.size(); i++) {
                SleepEntry cur_sleep = (SleepEntry) sleepList.get(i);
                if(cur_sleep.getSleepEntryID()==index) {
                    sleepList.remove(i);
                    updateFile();
                    SleepListUi.successfulDeletedMessage(cur_sleep);
                    return;
                }
            }
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
