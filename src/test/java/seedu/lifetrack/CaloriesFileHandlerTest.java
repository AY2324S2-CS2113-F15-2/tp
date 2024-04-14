package seedu.lifetrack;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.system.storage.CaloriesFileHandler;

public class CaloriesFileHandlerTest {

    public String filePath = "/sample-data/caloriesTestData.txt";
    public CaloriesFileHandler fileHandler = new CaloriesFileHandler(filePath);
/*
    @Test
    public void getCalorieEntriesFromFile_correctFileInput_entryArrayListReturned() {
        try {
            ArrayList<Entry> expectedEntries = new ArrayList<>();
            fileHandler.writeEntries(null);
            ArrayList<Entry> entries = fileHandler.getCalorieEntriesFromFile();
        } catch (FileNotFoundException e) {
            return;
        }
    }*/
}
