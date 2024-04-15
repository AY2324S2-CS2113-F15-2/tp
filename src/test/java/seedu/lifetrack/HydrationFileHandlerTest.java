package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.system.storage.HydrationFileHandler;

public class HydrationFileHandlerTest {
    
    private String filePath = "sample-data/hydrationTestData.txt";
    private HydrationFileHandler fileHandler = new HydrationFileHandler(filePath);
    private ArrayList<Entry> expectedEntries = new ArrayList<>();

    @Test
    public void getHydrationEntriesFromFile_correctHydrationInInput_allEntriesRetrievedFromFile() {
        try {
            HydrationEntry expectedEntry_1 = new HydrationEntry(1, "milo", 200, LocalDate.parse("2024-02-02"));
            HydrationEntry expectedEntry_2 = new HydrationEntry(2, "coffee", 190, LocalDate.parse("2024-02-01"));
            HydrationEntry expectedEntry_3 = new HydrationEntry(3, "tea", 180, LocalDate.parse("2024-02-03"));
            expectedEntries.add(expectedEntry_1);
            expectedEntries.add(expectedEntry_2);
            expectedEntries.add(expectedEntry_3);
            fileHandler.writeEntries(expectedEntries);
            ArrayList<Entry> actualEntries = fileHandler.getHydrationEntriesFromFile();
            for (int i = 0; i < expectedEntries.size(); i++) {
                HydrationEntry expectedEntryOut = (HydrationEntry)expectedEntries.get(i);
                HydrationEntry actualEntryOut = (HydrationEntry)actualEntries.get(i);
                assertEquals(expectedEntryOut.getEntryID(), actualEntryOut.getEntryID());
                assertEquals(expectedEntryOut.getDescription(), actualEntryOut.getDescription());
                assertEquals(expectedEntryOut.getDate(), actualEntryOut.getDate());
                assertEquals(expectedEntryOut.getVolume(), actualEntryOut.getVolume());
            }
            expectedEntries.clear();
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
