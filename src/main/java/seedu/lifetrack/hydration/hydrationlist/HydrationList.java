//@@author shawnpong
package seedu.lifetrack.hydration.hydrationlist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserCalories;
import seedu.lifetrack.system.parser.ParserHydration;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.ui.CalorieListUi;
import seedu.lifetrack.ui.HydrationListUI;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a list of liquid entries.
 * Provides methods to add, delete, and print liquid entries.
 */
public class HydrationList {

    private static Logger logr = Logger.getLogger(CalorieList.class.getName());

    private final int SIZE_OF_DELETE = 16;

    private final int NO_INDEX_FOUND = -1;
    private ArrayList<Entry> hydrationArrayList;
    private FileHandler fileHandler;
    private int lastHydrationEntryID;

    //constructor for JUnit tests
    public HydrationList() {
        hydrationArrayList = new ArrayList<>();
    }

    //constructor for usage in terminal
    public HydrationList(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            hydrationArrayList = fileHandler.getHydrationEntriesFromFile();
            this.lastHydrationEntryID = loadLastEntryID();
        } catch (FileNotFoundException e) {
            hydrationArrayList = new ArrayList<>();
        }
    }

    /**
     * Updates the file with the current list of hydration entries.
     */
    private void updateFile() {
        if (fileHandler != null) {
            fileHandler.writeEntries(hydrationArrayList);
        }
    }

    /**
     * Retrieves the liquid entry at the specified index.
     *
     * @param index the index of the liquid entry to retrieve
     * @return the liquid entry at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Entry getEntry(int index) {
        return hydrationArrayList.get(index);
    }

    /**
     * Deletes the liquid entry indicated by the provided line.
     *
     * @param line the string containing the index of the liquid record to delete
     */
    public void deleteEntry(String line) {
        assert (line.startsWith("hydration delete")) : "ensures that input is correct";
        try {
            int entryID = Integer.parseInt(line.substring(SIZE_OF_DELETE).trim());
            int index = getIndexFromEntryID(entryID);
            if (hydrationArrayList.isEmpty()) {
                HydrationListUI.emptyHydrationList();
            } else if (index == NO_INDEX_FOUND) {
                HydrationListUI.unsuccessfulDeletedMessage(entryID);
            } else {
                Entry toDelete = hydrationArrayList.get(index);
                hydrationArrayList.remove((index));
                HydrationListUI.successfulDeletedMessage(toDelete);
                updateFile();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(HydrationListUI.deleteLogIndexMessage());
        } catch (NumberFormatException e) {
            System.out.println(HydrationListUI.deleteLogNumberMessage());
        }
    }

    public int getIndexFromEntryID(int lastEntryID) {
        for (int i = 0; i < hydrationArrayList.size(); i++) {
            if (hydrationArrayList.get(i).getLastEntryID() == lastEntryID) {
                return i;
            }
        }
        return NO_INDEX_FOUND;
    }

    /**
     * Adds a new liquid entry based on the provided input.
     *
     * @param input the input string containing liquid entry information
     */
    public void addEntry(String input) {
        assert (input.startsWith("hydration in")) : "ensures that input is correct";
        logr.setLevel(Level.SEVERE);
        try {
            Entry newEntry = ParserHydration.parseHydrationInput(input, lastHydrationEntryID);
            hydrationArrayList.add(newEntry);
            updateFile();
            HydrationListUI.printNewHydrationEntry(newEntry);
            lastHydrationEntryID++;
            if (hydrationArrayList.size() > 1 &&
                    hydrationArrayList.get(hydrationArrayList.size() - 2).getDate().compareTo(newEntry.getDate()) > 0) {
                sortEntriesByDate();
            }
        } catch (InvalidInputException e) {
            logr.log(Level.WARNING, e.getMessage(), e);
            System.out.println(e.getMessage());
        }
    }

    public void sortEntriesByDate() {
        Collections.sort(hydrationArrayList, new Comparator<Entry>() {
            @Override
            public int compare(Entry entry1, Entry entry2) {
                return entry1.getDate().compareTo(entry2.getDate());
            }
        });
    }

    /**
     * Prints the list of liquid entries.
     * If the list is empty, prints a message indicating that the list is empty.
     */
    public void printHydrationList() {
        if (hydrationArrayList.isEmpty()) {
            HydrationListUI.emptyListMessage();
        } else {
            HydrationListUI.hydrationListHeader();
            printHydrationInflow();
        }
    }

    public void printHydrationInflow() {
        int serialNumber = 1;
        for (Entry value : hydrationArrayList) {
            if (value instanceof HydrationEntry) {
                Entry entry = value;
                System.out.println("\t " + serialNumber + ". " + entry);
                serialNumber++;
            }
        }
    }

    /**
     * Retrieves the total amount of hydration consumed.
     *
     * @return the total amount of hydration consumed
     */
    public int getHydrationConsumed(LocalDate date) {
        int totalHydration = 0;
        for (Entry entry : hydrationArrayList) {
            if (entry.getDate().isEqual(date)) {
                HydrationEntry tempEntry = (HydrationEntry) entry;
                totalHydration += tempEntry.getHydration();
            }
        }
        return totalHydration;
    }

    public int getHydrationConsumedCurrentDay() {
        int totalHydration = 0;
        for (Entry entry : hydrationArrayList) {
            if (entry.getDate().isEqual(LocalDate.now())) {
                HydrationEntry tempEntry = (HydrationEntry) entry;
                totalHydration += tempEntry.getHydration();
            }
        }
        return totalHydration;
    }


    /**
     * Retrieves the size of the liquid list.
     *
     * @return the number of liquid entries in the list
     */
    public int getSize() {
        return hydrationArrayList.size();
    }

    private int loadLastEntryID() {
        return FileHandler.getMaxHydrationID();
    }

    //@@author paturikarthik
    public void findEntries(String input){
        ParserHydration.findHydrationListEntries(input,this);
    }

    public void addHydrationEntry(Entry entry){
        this.hydrationArrayList.add(entry);
    }

    public void printFoundHydrationList() {
        if (hydrationArrayList.isEmpty()) {
            HydrationListUI.emptyFoundListMessage();
        } else {
            HydrationListUI.hydrationListFoundHeader();
            printHydrationInflow();
        }
    }

}
