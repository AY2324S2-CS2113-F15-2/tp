package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.sleep.sleeplist.SleepEntry;
import seedu.lifetrack.system.storage.SleepFileHandler;

public class SleepFileHandlerTest {
    
    private String filePath = "sample-data/sleepTestData.txt";
    private SleepFileHandler fileHandler = new SleepFileHandler(filePath);
    private ArrayList<Entry> expectedEntries = new ArrayList<>();

    @Test
    public void getSleepEntriesFromFile_correctSleepAddInput_allEntriesRetrievedFromFile() {
        try {
            SleepEntry expectedEntry_1 = new SleepEntry(1, 20.3, LocalDate.parse("2024-02-02"));
            SleepEntry expectedEntry_2 = new SleepEntry(2, 19.2, LocalDate.parse("2024-02-01"));
            SleepEntry expectedEntry_3 = new SleepEntry(3, 18.1, LocalDate.parse("2024-02-03"));
            expectedEntries.add(expectedEntry_1);
            expectedEntries.add(expectedEntry_2);
            expectedEntries.add(expectedEntry_3);
            fileHandler.writeEntries(expectedEntries);
            ArrayList<Entry> actualEntries = fileHandler.getSleepEntriesFromFile();
            for (int i = 0; i < expectedEntries.size(); i++) {
                SleepEntry expectedEntryOut = (SleepEntry)expectedEntries.get(i);
                SleepEntry actualEntryOut = (SleepEntry)actualEntries.get(i);
                assertEquals(expectedEntryOut.getEntryID(), actualEntryOut.getEntryID());
                assertEquals(expectedEntryOut.getDescription(), actualEntryOut.getDescription());
                assertEquals(expectedEntryOut.getDate(), actualEntryOut.getDate());
                assertEquals(expectedEntryOut.getDuration(), actualEntryOut.getDuration());
            }
            expectedEntries.clear();
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
