//@@author shawnpong
package seedu.lifetrack.ui;

import seedu.lifetrack.Entry;

public class HydrationListUI {

    public static void successfulDeletedMessage(Entry toDelete) {
        System.out.println("\t The following hydration record has been successfully deleted!");
        System.out.println("\t " + toDelete.toString());
    }
    public static void unsuccessfulDeletedMessage(int entryID) {
        System.out.println("\t The following hydration record corresponding to entry ID " + entryID + " could " +
                "not be found");
    }

    public static void emptyListMessage() {
        System.out.println("\t Your hydration list is empty. Add new entries to populate your list :)");
    }
    public static void emptyFoundListMessage() {
        System.out.println("\t There were no matches to your search!");
    }
    public static void hydrationListFoundHeader() {
        System.out.println("\t Hydration List based on your search:");
    }

    public static String deleteLogIndexMessage() {
        return "\t Sorry, this index is invalid. Please enter a positive integer within the size of the list.";
    }

    public static String deleteLogNumberMessage() {
        return "\t Please enter a valid index!";
    }

    public static void hydrationListHeader() {
        System.out.println("\t Your Hydration List:");
    }

    public static void printNewHydrationEntry(Entry newEntry) {
        System.out.println("\t The following entry has been added to your hydration list!");
        System.out.println("\t " + newEntry.toString());
    }

    public static void emptyHydrationList() {
        System.out.println("\t There is nothing to delete as the hydration list is empty!");
    }
}
