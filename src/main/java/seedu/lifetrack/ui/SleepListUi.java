//@@author a-wild-chocolate
package seedu.lifetrack.ui;

import seedu.lifetrack.Entry;

public class SleepListUi {

    public static void successfulDeletedMessage(Entry toDelete) {
        System.out.println("\t The following sleep record has been successfully deleted!");
        System.out.println("\t " + toDelete.toString());
    }

    public static void emptyListMessage() {
        System.out.println("\t Your sleep list is empty. Add new entries to populate your list :)");
    }

    public static String deleteLogIndexMessage() {
        return "\t Sorry, this index is invalid. Please enter a positive integer within the size of the list.";
    }

    public static String deleteLogNumberMessage() {
        return "\t Please enter a valid index!";
    }

    public static void sleepListHeader() {
        System.out.println("\t Your Sleep List:");
    }
    //@@author

    public static void printNewSleepEntry(Entry newEntry) {
        System.out.println("\t The following entry has been added to your sleep list!");
        System.out.println("\t " + newEntry.toString());
    }
}
//@@author
