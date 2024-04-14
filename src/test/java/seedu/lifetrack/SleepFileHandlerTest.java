package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.sleep.sleeplist.SleepEntry;
import seedu.lifetrack.system.storage.SleepFileHandler;

public class SleepFileHandlerTest {
    
    private String filePath = "sample-data/sleepTestData.txt";
    private SleepFileHandler fileHandler = new SleepFileHandler(filePath);
    private ArrayList<Entry> expectedEntries = new ArrayList<>();

    @Test
    public void getSleepEntriesFromFile_correctSleepAddInput_entryArrayListReturned() {
        try {
            String expectedEntry_1 = "1;2024-02-02;20.3\n";
            String expectedEntry_2 = "2;2024-02-01;19.2\n";
            String expectedEntry_3 = "3;2024-02-03;18.1\n";
            fileHandler.writeToFile(expectedEntry_1 + expectedEntry_2 + expectedEntry_3);
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
        } catch (IOException e) {
            return;
        }
    }
}
