//@@author owx0130
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

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileCaloriesTooFewFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileTooFewMacrosMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileCaloriesTooManyFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidCaloriesMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidCarbsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidEntryIDMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidEntryTypeMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidFatsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidProteinsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileMacrosInOutputMessage;
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

    protected void checkCorrectNumberOfFields(int lineNumber, int dataLength) throws FileHandlerException {
        if (dataLength < 5) {
            throw new FileHandlerException(getFileCaloriesTooFewFieldsMessage(lineNumber, filePath));
        } else if (dataLength > 8) {
            throw new FileHandlerException(getFileCaloriesTooManyFieldsMessage(lineNumber, filePath));
        }
    }

    private void calculateMaxCaloriesEntry(int entryID) {
        if (entryID > maxCaloriesID) {
            maxCaloriesID = entryID;
        }
    }

    private void checkCaloriesIsPositive(int lineNumber, int calories) throws FileHandlerException {
        if (calories <= 0) {
            throw new FileHandlerException(getFileInvalidCaloriesMessage(lineNumber, filePath));
        }
    }

    private void checkMacrosArePositive(int lineNumber, int carbs, int proteins, int fats)
            throws FileHandlerException {
        if (carbs <= 0) {
            throw new FileHandlerException(getFileInvalidCarbsMessage(lineNumber, filePath));
        } else if (proteins <= 0) {
            throw new FileHandlerException(getFileInvalidProteinsMessage(lineNumber, filePath));
        } else if (fats <= 0) {
            throw new FileHandlerException(getFileInvalidFatsMessage(lineNumber, filePath));
        }
    }

    private void checkValidEntryType(int lineNumber, String entryType, int dataLength) throws FileHandlerException {
        if (!(entryType.equals("C_IN") || entryType.equals("C_OUT"))) {
            throw new FileHandlerException(getFileInvalidEntryTypeMessage(lineNumber, filePath));
        }
        
        if (entryType.equals("C_IN") && dataLength != 8) {
            throw new FileHandlerException(getFileTooFewMacrosMessage(lineNumber, filePath));
        } else if (entryType.equals("C_OUT") && dataLength != 5) {
            throw new FileHandlerException(getFileMacrosInOutputMessage(lineNumber, filePath));
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
                checkCorrectNumberOfFields(i, words.length);
                int entryID = Integer.parseInt(words[ENTRYID_INDEX].trim());
                calculateMaxCaloriesEntry(entryID);
                LocalDate date = LocalDate.parse(words[DATE_INDEX].trim());
                checkDateNotLaterThanCurrent(i, date);
                String description = words[DESCRIPTION_INDEX].trim();
                checkNonEmptyDescription(i, description);
                int calories = Integer.parseInt(words[CALORIES_INDEX].trim());
                checkCaloriesIsPositive(i, calories);
                String entryType = words[ENTRY_TYPE_INDEX].trim();
                checkValidEntryType(i, entryType, words.length);
                if (entryType.equals("C_IN") && words.length == 8) {
                    int carbohydrates = Integer.parseInt(words[CARBOHYDRATES_INDEX].trim());
                    int proteins = Integer.parseInt(words[PROTEINS_INDEX].trim());
                    int fats = Integer.parseInt(words[FATS_INDEX].trim());
                    Food food = new Food(carbohydrates, proteins, fats);
                    checkMacrosArePositive(i, carbohydrates, proteins, fats);
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
                } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[CARBOHYDRATES_INDEX] + "\"")) {
                    System.out.println(getFileInvalidCarbsMessage(i, filePath));
                } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[PROTEINS_INDEX] + "\"")) {
                    System.out.println(getFileInvalidProteinsMessage(i, filePath));
                } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[FATS_INDEX] + "\"")) {
                    System.out.println(getFileInvalidFatsMessage(i, filePath));
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
