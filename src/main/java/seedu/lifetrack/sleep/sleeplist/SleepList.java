//@@author a-wild-chocolate
package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserSleep;
import seedu.lifetrack.system.storage.SleepFileHandler;
import seedu.lifetrack.ui.SleepListUi;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getIncorrectSleepDeleteMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getSleepDurationSumTooLongMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getEmptySleepListMessage;


public class SleepList {

    private static int DELETE_IDX = 2;
    private static int DELETE_LEN = 3;
    private ArrayList<Entry> sleepList;
    private SleepFileHandler fileHandler;
    private int lastSleepEntryID;

    //constructor for JUnit tests
    public SleepList() {
        sleepList = new ArrayList<>();
    }

    //constructor for usage in terminal
    public SleepList(String filePath) {
        try {
            fileHandler = new SleepFileHandler(filePath);
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
            SleepEntry newSleep = ParserSleep.parseSleepInput(input);
            double sleepRecord = newSleep.getDuration();
            for(int i=0;i<this.sleepList.size();i++) {
                SleepEntry curSleep = (SleepEntry) this.sleepList.get(i);
                if(curSleep.getDate().isEqual(newSleep.getDate())) {
                    sleepRecord += curSleep.getDuration();
                }
            }
            if(sleepRecord>24){
                System.out.println(getSleepDurationSumTooLongMessage());
                return;
            }
            sleepList.add(newSleep);
            SleepListUi.printNewSleepEntry(newSleep);
            //only sort if newly added date is earlier than date in final entry before adding entry
            if (sleepList.size() > 1 &&
                    sleepList.get(sleepList.size() - 2).getDate().compareTo(newSleep.getDate()) > 0 ) {
                sortEntriesByDate();
            }
            updateFile();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortEntriesByDate() {
        Collections.sort(sleepList, new Comparator<Entry>() {
            @Override
            public int compare(Entry entry1, Entry entry2) {
                return entry1.getDate().compareTo(entry2.getDate());
            }
        });
    }
    
    public void deleteSleep(String line) {
        try {
            if(line.split(" ").length!=DELETE_LEN) {
                System.out.println(getIncorrectSleepDeleteMessage());
                return;
            }
            int index = Integer.parseInt(line.split(" ")[DELETE_IDX]) ; //User input format: sleep delete ID
            if(sleepList.isEmpty()){
                System.out.println(getEmptySleepListMessage());
                return;
            }
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

    public double getSleepConsumed(LocalDate date) {
        double totalSleep = 0;
        for (Entry entry : sleepList) {
            if (entry.getDate().isEqual(date)) {
                SleepEntry tempEntry = (SleepEntry) entry;
                totalSleep += tempEntry.getDuration();
            }
        }
        return totalSleep;
    }
}
//@@author
