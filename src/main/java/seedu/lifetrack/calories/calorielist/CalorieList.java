//@@author rexyyong
package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserCalories;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.ui.CalorieListUi;

import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CalorieList {

    private static Logger logr = Logger.getLogger(CalorieList.class.getName());

    private final int SIZE_OF_DELETE = 16;

    //constant for finding entry index from entryID
    private final int NO_INDEX_FOUND = -1;
    private ArrayList<Entry> calorieArrayList;
    private FileHandler fileHandler;
    private int lastEntryID;

    /**
     * Constructs a new CalorieList object.
     * This constructor is used for JUnit tests and initializes an empty list of calorie entries.
     */
    public CalorieList() {
        calorieArrayList = new ArrayList<>();
    }

    /**
     * Constructs a new CalorieList object using a file path.
     * This constructor is intended for usage in a terminal environment.
     *
     * @param filePath the path to the file containing calorie entries
     */
    public CalorieList(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            calorieArrayList = fileHandler.getCalorieEntriesFromFile();
            // Initialize lastEntryID from stored data or default to 0 if not available
            this.lastEntryID = loadLastEntryID();
        } catch (FileNotFoundException e) {
            calorieArrayList = new ArrayList<>();
        }
    }

    /**
     * Updates the file with the current list of calorie entries.
     * If the file handler is not initialized, no action is taken.
     */
    private void updateFile() {
        if (fileHandler != null) {
            fileHandler.writeEntries(calorieArrayList);
        }
    }

    public Entry getEntry(int index) {
        return calorieArrayList.get(index);
    }


    /**
     * Deletes a calorie entry from the list based on the provided entryID.
     * entryID should be in an integer from 1 to size of the list, and be present in the calorieArrayList.
     *
     * @param line the string containing the entryID of calorie record user want to delete
     */
    //@@author a-wild-chocolate
    public void deleteEntry(String line) {
        assert (line.startsWith("calories delete") ) : "ensures that input is correct";

        try {
            int entryID = Integer.parseInt(line.substring(SIZE_OF_DELETE).trim());
            int index = getIndexFromEntryID(entryID);
            if (index == NO_INDEX_FOUND) {
                CalorieListUi.unsuccessfulDeletedMessage(entryID);
            } else {
                Entry toDelete = calorieArrayList.get(index);
                calorieArrayList.remove((index));
                CalorieListUi.successfulDeletedMessage(toDelete);
                updateFile();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(CalorieListUi.deleteLogNumberMessage());
        } catch (NumberFormatException e) {
            System.out.println(CalorieListUi.deleteLogNumberMessage());
        }
    }
    //@@author

    /**
     * Returns the index of the entry in the calorie ArrayList based on the given entry ID.
     *
     * @param entryID the entry ID to search for in the list
     * @return the index of the entry with the specified entry ID, or NO_INDEX_FOUND if not found
     */
    public int getIndexFromEntryID(int entryID) {
        for (int i = 0; i < calorieArrayList.size(); i++) {
            if (calorieArrayList.get(i).getLastEntryID() == entryID) {
                return i;
            }
        }
        return NO_INDEX_FOUND;
    }

    /**
     * Parses a string input representing calorie intake and adds it to the calorie list.
     *
     * This method takes a string input representing calorie intake information and
     * attempts to parse it using the parseCaloriesInput method from the ParserCalories class.
     * If the input format is incorrect or contains missing components, it catches
     * the InvalidInputException and prints an error message. Otherwise, it adds
     * the parsed Entry object to the calorieArrayList.
     * Additionally, if the date of the newly added entry is earlier than the date of the final
     * entry before adding the new entry, the list is sorted by date.
     *
     * @param input the input string containing date, time, activity, and calorie count
     */
    public void addEntry(String input) {
        assert (input.startsWith("calories in") || input.startsWith("calories out")) : "ensures that input is correct";
        logr.setLevel(Level.SEVERE);
        try {
            Entry newEntry = ParserCalories.parseCaloriesInput(input, lastEntryID);
            calorieArrayList.add(newEntry);
            updateFile();
            CalorieListUi.printNewCalorieEntry(newEntry);
            lastEntryID ++;
            //only sort if newly added date is earlier than date in final entry before adding entry
            if (calorieArrayList.size() > 1 &&
                    calorieArrayList.get(calorieArrayList.size() - 2).getDate().compareTo(newEntry.getDate()) > 0 ) {
                sortEntriesByDate();
            }
        } catch (InvalidInputException e) {
            logr.log(Level.WARNING, e.getMessage(), e);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the list of calorie entries along with its activity description.
     * If the list is empty, it prints a message indicating that the list is empty.
     * Otherwise, it prints each entry's activity description and calorie count.
     * It prints calorieInflow entries, followed by calorieOutflow entries.
     */
    public void printCalorieList() {
        if (calorieArrayList.isEmpty()) {
            CalorieListUi.emptyListMessage();
        } else {
            CalorieListUi.calorieListHeader();
            printCalorieInflow();
            printCalorieOutflow();
        }
    }

    /**
     * Prints the list of calorie entries representing calorie intake along with their activity descriptions.
     * This method prints each entry's activity description and calorie count if it represents calorie intake.
     */
    public void printCalorieInflow() {
        CalorieListUi.inputCalorieListHeader();
        int serialNumber = 1;
        for (Entry value : calorieArrayList) {
            if (value instanceof InputEntry) {
                Entry entry = value;
                System.out.println("\t " + serialNumber + ". " + entry);
                serialNumber++;
            }
        }
    }

    /**
     * Prints the list of calorie entries representing calorie expenditure along with their activity descriptions.
     * This method prints each entry's activity description and calorie count if it represents calorie expenditure.
     */
    public void printCalorieOutflow() {
        CalorieListUi.outputCalorieListHeader();
        int serialNumber = 1;
        for (Entry value : calorieArrayList) {
            if (value instanceof OutputEntry) {
                Entry entry = value;
                System.out.println("\t " + serialNumber + ". " + entry);
                serialNumber++;
            }
        }
    }


    /**
     * Returns the size of the list of calorie entries.
     *
     * @return the number of calorie entries in the list
     */
    public int getSize() {
        return calorieArrayList.size();
    }

    /**
     * Calculates and returns the total number of calories consumed from all entries in the list.
     *
     * @return the total number of calories consumed
     */
    public int getCaloriesConsumed() {
        int totalCalories = 0;
        for (int i = 0; i < calorieArrayList.size(); i++) {
            InputEntry tempEntry = (InputEntry) calorieArrayList.get(i);
            totalCalories += tempEntry.getCalories();
        }
        return totalCalories;
    }

    /**
     * Loads the last entry ID from a text file.
     * This method retrieves the last entry ID from the file using the FileHandler.getMaxCaloriesID method.
     * If the file doesn't exist or an error occurs during the process, it returns a default value.
     *
     * @return the last entry ID loaded from the file, or a default value if the file doesn't exist or an error occurs
     */
    private int loadLastEntryID() {
        return FileHandler.getMaxCaloriesID(); // Default value if file doesn't exist or error occurs
    }

    /**
     * Sorts the list of calorie entries by date.
     * This method uses the Collections.sort(List, Comparator) method to sort the list of
     * calorie entries in ascending order based on their dates. It provides a custom comparator that
     * compares the dates of two entries and returns the result of the comparison.
     */
    public void sortEntriesByDate() {
        Collections.sort(calorieArrayList, new Comparator<Entry>() {
            @Override
            public int compare(Entry entry1, Entry entry2) {
                return entry1.getDate().compareTo(entry2.getDate());
            }
        });
    }
}
