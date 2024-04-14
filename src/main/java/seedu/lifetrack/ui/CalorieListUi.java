//@@author rexyyong
package seedu.lifetrack.ui;

import seedu.lifetrack.Entry;

public class CalorieListUi {
    private static final String CALORIES_DELETE_SAMPLE_INPUT = "\t Example input: calories delete CALORIES_ID";


    public static void successfulDeletedMessage(Entry toDelete) {
        System.out.println("\t The following calorie record has been successfully deleted!");
        System.out.println("\t " + toDelete.toString());
    }
    public static void unsuccessfulDeletedMessage(int entryID) {
        System.out.println("\t The following calorie record corresponding to caloriesID " + entryID + " could " +
                "not be found\n" + "\t Refer to calories list to see valid caloriesIDs.");
    }

    public static void emptyCalorieList() {
        System.out.println("\t There is nothing to delete as the calorie list is empty!");
    }

    public static void emptyListMessage() {
        System.out.println("\t Your caloric list is empty. Add new entries to populate your list :)");
    }
    public static void emptyFoundListMessage() {
        System.out.println("\t There were no matches to your search!");
    }
    //@@author a-wild-chocolate
    public static String deleteLogIndexMessage() {
        return "\t Sorry, this index is invalid. Please enter a positive integer within the size of the list.";
    }
    
    public static String deleteLogNumberMessage() {
        return "\t Please enter a valid positive integer for the caloriesID you wish to delete.\n" +
                CALORIES_DELETE_SAMPLE_INPUT;
    }
    //@@author
    public static void calorieListHeader() {
        System.out.println("\t Your Caloric List:");
    }

    public static void calorieListFoundHeader() {
        System.out.println("\t Caloric List based on your search:");
    }
    public static void outputCalorieListHeader() {
        System.out.println("");
        System.out.println("\t Your Caloric Outflow List:");
    }

    public static void inputCalorieListHeader() {
        System.out.println("");
        System.out.println("\t Your Caloric Inflow List:");
    }

    public static void printNewCalorieEntry(Entry newEntry) {
        System.out.println("\t The following entry has been added to your caloric list!");
        System.out.println("\t " + newEntry.toString());
    }
}
