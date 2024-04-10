//@@author owx0130
package seedu.lifetrack.system.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.sleep.sleeplist.SleepEntry;
import seedu.lifetrack.system.exceptions.ErrorMessages;
import seedu.lifetrack.user.User;

public class FileHandler {

    //public member for lastEntryID calories
    public static int maxCaloriesID = 0;
    public static int maxHydrationID = 0;
    //general list constants
    private static final int ENTRYID_INDEX = 0;
    private static final int DATE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;

    //calorie list constants
    private static final int ENTRY_TYPE_INDEX = 3;
    private static final int CALORIES_INDEX = 4;
    private static final int CARBOHYDRATES_INDEX = 5;
    private static final int PROTEINS_INDEX = 6;
    private static final int FATS_INDEX = 7;

    //liquids list constants
    private static final int VOLUME_INDEX = 3;

    //sleep list constants
    private static final int DURATION_INDEX = 3;

    //user data constants
    private static final int NAME_INDEX = 0;
    private static final int HEIGHT_INDEX = 1;
    private static final int WEIGHT_INDEX = 2;
    private static final int AGE_INDEX = 3;
    private static final int SEX_INDEX = 4;
    private static final int EXERCISE_INDEX = 5;
    private static final int GOAL_INDEX = 6;
    private static final int REQ_CAL_INDEX = 7;

    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public static int getMaxCaloriesID() {
        return maxCaloriesID;
    }

    public static int getMaxHydrationID() {
        return maxHydrationID;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void writeUserData(User user) {
        try {
            writeToFile(user.toFileFriendlyString());
        } catch (IOException e) {
            System.out.println(ErrorMessages.getIOExceptionMessage());
        }
    }

    public void writeEntries(ArrayList<Entry> entries) {
        try {
            String newData = "";
            for (Entry entry : entries) {
                newData += entry.toFileFriendlyString() + System.lineSeparator();
            }
            writeToFile(newData);
        } catch (IOException e) {
            System.out.println(ErrorMessages.getIOExceptionMessage());
        }
    }

    public ArrayList<Entry> getCalorieEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            int entryID = Integer.parseInt(words[ENTRYID_INDEX]);
            calculateMaxCaloriesEntry(entryID);
            LocalDate date = LocalDate.parse(words[DATE_INDEX]);
            String description = words[DESCRIPTION_INDEX];
            int calories = Integer.parseInt(words[CALORIES_INDEX]);
            String entryType = words[ENTRY_TYPE_INDEX];
            if (entryType.equals("C_IN") && words.length == 8) {
                int carbohydrates = Integer.parseInt(words[CARBOHYDRATES_INDEX]);
                int proteins = Integer.parseInt(words[PROTEINS_INDEX]);
                int fats = Integer.parseInt(words[FATS_INDEX]);
                Food food = new Food(carbohydrates, proteins, fats);
                entries.add(new InputEntry(entryID, description, calories, date, food));
            } else if (entryType.equals("C_IN")) {
                entries.add(new InputEntry(entryID, description, calories, date));
            } else {
                entries.add(new OutputEntry(entryID, description, calories, date));
            }
        }
        return entries;
    }

    // Method calculates the max calories entry ID
    public void calculateMaxCaloriesEntry(int entryID) {
        if (entryID > maxCaloriesID) {
            maxCaloriesID = entryID;
        }
    }

    public void calculateMaxHydrationEntry(int entryID) {
        if (entryID > maxHydrationID) {
            maxHydrationID = entryID;
        }
    }

    public ArrayList<Entry> getHydrationEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            int lastHydrationEntryID = Integer.parseInt(words[ENTRYID_INDEX]);
            LocalDate date = LocalDate.parse(words[DATE_INDEX]);
            String description = words[DESCRIPTION_INDEX];
            int volume = Integer.parseInt(words[VOLUME_INDEX]);
            entries.add(new HydrationEntry(lastHydrationEntryID, description, volume, date));
        }
        return entries;
    }

    public ArrayList<Entry> getSleepEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            int lastSleepEntryID = Integer.parseInt(words[ENTRYID_INDEX]);
            LocalDate date = LocalDate.parse(words[DATE_INDEX]);
            double duration = Double.parseDouble(words[DURATION_INDEX]);
            entries.add(new SleepEntry(lastSleepEntryID, duration, date));
        }
        return entries;
    }
    
    public ArrayList<String> getUserDataFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> data = new ArrayList<>();
        String line = s.nextLine();
        String[] words = line.split(";");
        data.add(words[NAME_INDEX]);
        data.add(words[HEIGHT_INDEX]);
        data.add(words[WEIGHT_INDEX]);
        data.add(words[AGE_INDEX]);
        data.add(words[SEX_INDEX]);
        data.add(words[EXERCISE_INDEX]);
        data.add(words[GOAL_INDEX]);
        data.add(words[REQ_CAL_INDEX]);
        return data;
    }
}
