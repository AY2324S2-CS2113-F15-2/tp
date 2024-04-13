package seedu.lifetrack.system.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.system.exceptions.FileHandlerException;

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidCaloriesMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidEntryIDMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidDateMessage;

public class CaloriesFileHandler extends FileHandler {

    //class-level member for lastEntryID calories
    public static int maxCaloriesID = 0;

    //calorie list constants
    private final int ENTRY_TYPE_INDEX = 3;
    private final int CALORIES_INDEX = 4;
    private final int CARBOHYDRATES_INDEX = 5;
    private final int PROTEINS_INDEX = 6;
    private final int FATS_INDEX = 7;

    public CaloriesFileHandler(String filePath) {
        super(filePath);
    }

    private void calculateMaxCaloriesEntry(int entryID) {
        if (entryID > maxCaloriesID) {
            maxCaloriesID = entryID;
        }
    }

    public ArrayList<Entry> getCalorieEntriesFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Entry> entries = new ArrayList<>();
        String line = "";
        int i = 1;
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            try {
                int entryID = Integer.parseInt(words[ENTRYID_INDEX]);
                calculateMaxCaloriesEntry(entryID);
                LocalDate date = LocalDate.parse(words[DATE_INDEX]);
                checkDateNotLaterThanCurrent(date, i);
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
            } catch (NumberFormatException e) {
                if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[ENTRYID_INDEX] + "\"")) {
                    System.out.println(getFileInvalidEntryIDMessage(i, filePath));
                } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[CALORIES_INDEX] + "\"")) {
                    System.out.println(getFileInvalidCaloriesMessage(i, filePath));
                }
            } catch (DateTimeParseException e) {
                System.out.println(getFileInvalidDateMessage(i, filePath));
            } catch (FileHandlerException e) {
                System.out.println(e.getMessage());
            } finally {
                i++;
            }
        }
        s.close();
        return entries;
    }
}
