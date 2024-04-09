package seedu.lifetrack.hydration.hydrationlist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.system.exceptions.ErrorMessages;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserHydration;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.ui.HydrationListUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
            System.out.println(ErrorMessages.getFileNotFoundMessage());
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
        try {
            int entryID = Integer.parseInt(line.substring(SIZE_OF_DELETE).trim());
            int index = getIndexFromEntryID(entryID);
            if (index == NO_INDEX_FOUND) {
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
        logr.setLevel(Level.WARNING);
        try {
            Entry newEntry = ParserHydration.parseHydrationInput(input, lastHydrationEntryID);
            hydrationArrayList.add(newEntry);
            updateFile();
            HydrationListUI.printNewHydrationEntry(newEntry);
        } catch (InvalidInputException e) {
            logr.log(Level.WARNING, e.getMessage(), e);
        }
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
            for (int i = 0; i < hydrationArrayList.size(); i++) {
                Entry entry = hydrationArrayList.get(i);
                System.out.println("\t " + (i + 1) + ". " + entry);
            }
        }
    }

    /**
     * Retrieves the total amount of hydration consumed.
     *
     * @return the total amount of hydration consumed
     */
    public int getHydrationConsumed() {
        int totalHydration = 0;
        for (int i = 0; i < hydrationArrayList.size(); i++) {
            HydrationEntry tempEntry = (HydrationEntry) hydrationArrayList.get(i);
            totalHydration += tempEntry.getHydration();
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
}
