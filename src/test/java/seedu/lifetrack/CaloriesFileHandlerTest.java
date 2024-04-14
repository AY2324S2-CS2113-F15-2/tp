package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.system.storage.CaloriesFileHandler;

public class CaloriesFileHandlerTest {

    private String filePath = "sample-data/caloriesTestData.txt";
    private CaloriesFileHandler fileHandler = new CaloriesFileHandler(filePath);
    private ArrayList<Entry> expectedEntries = new ArrayList<>();

    @Test
    public void getCalorieEntriesFromFile_correctCaloriesInInput_entryArrayListReturned() {
        try {
            InputEntry expectedEntry_1 = new InputEntry(1, "burger", 200, LocalDate.parse("2024-02-02"));
            InputEntry expectedEntry_2 = new InputEntry(2, "rice", 190, LocalDate.parse("2024-02-01"));
            InputEntry expectedEntry_3 = new InputEntry(3, "noodle", 180, LocalDate.parse("2024-02-03"));
            expectedEntries.add(expectedEntry_1);
            expectedEntries.add(expectedEntry_2);
            expectedEntries.add(expectedEntry_3);
            fileHandler.writeEntries(expectedEntries);
            ArrayList<Entry> actualEntries = fileHandler.getCalorieEntriesFromFile();
            for (int i = 0; i < expectedEntries.size(); i++) {
                InputEntry expectedEntryOut = (InputEntry)expectedEntries.get(i);
                InputEntry actualEntryOut = (InputEntry)actualEntries.get(i);
                assertEquals(expectedEntryOut.getEntryID(), actualEntryOut.getEntryID());
                assertEquals(expectedEntryOut.getDescription(), actualEntryOut.getDescription());
                assertEquals(expectedEntryOut.getDate(), actualEntryOut.getDate());
                assertEquals(expectedEntryOut.getCalories(), actualEntryOut.getCalories());
            }
            expectedEntries.clear();
        } catch (FileNotFoundException e) {
            return;
        }
    }

    @Test
    public void getCalorieEntriesFromFile_correctCaloriesInInputWithMacros_entryArrayListReturned() {
        try {
            Food food_1 = new Food(100, 200, 300);
            InputEntry expectedEntry_1 = new InputEntry(1, "burger", 200, LocalDate.parse("2024-02-02"), food_1);
            Food food_2 = new Food(200, 400, 600);
            InputEntry expectedEntry_2 = new InputEntry(2, "rice", 190, LocalDate.parse("2024-02-01"), food_2);
            Food food_3 = new Food(300, 600, 900);
            InputEntry expectedEntry_3 = new InputEntry(3, "noodle", 180, LocalDate.parse("2024-02-03"), food_3);
            expectedEntries.add(expectedEntry_1);
            expectedEntries.add(expectedEntry_2);
            expectedEntries.add(expectedEntry_3);
            fileHandler.writeEntries(expectedEntries);
            ArrayList<Entry> actualEntries = fileHandler.getCalorieEntriesFromFile();
            for (int i = 0; i < expectedEntries.size(); i++) {
                InputEntry expectedEntryOut = (InputEntry)expectedEntries.get(i);
                InputEntry actualEntryOut = (InputEntry)actualEntries.get(i);
                Food expectedFoodOut = expectedEntryOut.getFood();
                Food actualFoodOut = actualEntryOut.getFood();
                assertEquals(expectedEntryOut.getEntryID(), actualEntryOut.getEntryID());
                assertEquals(expectedEntryOut.getDescription(), actualEntryOut.getDescription());
                assertEquals(expectedEntryOut.getDate(), actualEntryOut.getDate());
                assertEquals(expectedEntryOut.getCalories(), actualEntryOut.getCalories());
                assertEquals(expectedFoodOut.getCarbohydrates(), actualFoodOut.getCarbohydrates());
                assertEquals(expectedFoodOut.getProteins(), actualFoodOut.getProteins());
                assertEquals(expectedFoodOut.getFats(), actualFoodOut.getFats());
            }
            expectedEntries.clear();
        } catch (FileNotFoundException e) {
            return;
        }
    }

    @Test
    public void getCalorieEntriesFromFile_correctCaloriesOutInput_entryArrayListReturned() {
        try {
            OutputEntry expectedEntry_1 = new OutputEntry(1, "run", 200, LocalDate.parse("2024-02-02"));
            OutputEntry expectedEntry_2 = new OutputEntry(2, "swim", 190, LocalDate.parse("2024-02-01"));
            OutputEntry expectedEntry_3 = new OutputEntry(3, "football", 180, LocalDate.parse("2024-02-03"));
            expectedEntries.add(expectedEntry_1);
            expectedEntries.add(expectedEntry_2);
            expectedEntries.add(expectedEntry_3);
            fileHandler.writeEntries(expectedEntries);
            ArrayList<Entry> actualEntries = fileHandler.getCalorieEntriesFromFile();
            for (int i = 0; i < expectedEntries.size(); i++) {
                OutputEntry expectedEntryOut = (OutputEntry)expectedEntries.get(i);
                OutputEntry actualEntryOut = (OutputEntry)actualEntries.get(i);
                assertEquals(expectedEntryOut.getEntryID(), actualEntryOut.getEntryID());
                assertEquals(expectedEntryOut.getDescription(), actualEntryOut.getDescription());
                assertEquals(expectedEntryOut.getDate(), actualEntryOut.getDate());
                assertEquals(expectedEntryOut.getCalories(), actualEntryOut.getCalories());
            }
            expectedEntries.clear();
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
