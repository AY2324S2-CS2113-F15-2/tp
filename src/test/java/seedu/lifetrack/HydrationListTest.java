//@@author shawnpong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.hydration.hydrationlist.HydrationList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HydrationListTest {

    @Test
    public void testDeleteHydrationValidIndex() {
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration in Milo v/200 d/2024-02-22");
        int initialSize = hydrationList.getSize();
        hydrationList.deleteEntry("hydration delete 1");
        assertEquals(initialSize - 1, hydrationList.getSize());
    }

    @Test
    public void testDeleteHydrationInvalidIndex() {
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration in Milo v/200 d/2024-02-22");
        int initialSize = hydrationList.getSize();
        hydrationList.deleteEntry("hydration delete 2"); // Index out of bounds
        hydrationList.deleteEntry("hydration delete -1");
        assertEquals(initialSize, hydrationList.getSize());
    }

    @Test
    public void testPrintHydrationListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t Your hydration list is empty. Add new entries to populate your list :)" +
                lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintHydrationListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration in Milo v/200 d/2024-02-22");
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your hydration list!" + lineSeparator +
                "\t \t EntryID: 1, Date: 2024-02-22, Description: Milo, Volume: 200" + lineSeparator +
                "\t Your Hydration List:" + lineSeparator +
                "\t 1. \t EntryID: 1, Date: 2024-02-22, Description: Milo, Volume: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintHydrationListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration in Milo v/200 d/2024-02-22");
        hydrationList.addEntry("hydration in Water v/300 d/2024-02-22");
        hydrationList.addEntry("hydration in Juice v/150 d/2024-02-22");
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your hydration list!" + lineSeparator +
                "\t \t EntryID: 1, Date: 2024-02-22, Description: Milo, Volume: 200" + lineSeparator +
                "\t The following entry has been added to your hydration list!" + lineSeparator +
                "\t \t EntryID: 2, Date: 2024-02-22, Description: Water, Volume: 300" + lineSeparator +
                "\t The following entry has been added to your hydration list!" + lineSeparator +
                "\t \t EntryID: 3, Date: 2024-02-22, Description: Juice, Volume: 150" + lineSeparator +
                "\t Your Hydration List:" + lineSeparator +
                "\t 1. \t EntryID: 1, Date: 2024-02-22, Description: Milo, Volume: 200" + lineSeparator +
                "\t 2. \t EntryID: 2, Date: 2024-02-22, Description: Water, Volume: 300" + lineSeparator +
                "\t 3. \t EntryID: 3, Date: 2024-02-22, Description: Juice, Volume: 150" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, hydrationList.getSize());
    }
    @Test
    public void testAddEntry() {
        HydrationList hydrationList = new HydrationList();
        int initialSize = hydrationList.getSize();
        hydrationList.addEntry("hydration in Water v/250 d/2024-02-23");
        assertEquals(initialSize + 1, hydrationList.getSize());
    }

    @Test
    public void testDeleteEntryFromEmptyList() {
        HydrationList hydrationList = new HydrationList();
        int initialSize = hydrationList.getSize();
        hydrationList.deleteEntry("hydration delete 1");
        assertEquals(initialSize, hydrationList.getSize());
    }

    @Test
    public void testDeleteEntryWithInvalidFormat() {
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration in Water v/250 d/2024-02-23");
        int initialSize = hydrationList.getSize();
        // Try to delete with invalid format, should not affect the list
        hydrationList.deleteEntry("delete 1");
        assertEquals(initialSize, hydrationList.getSize());
    }

    @Test
    public void testPrintHydrationListWithMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration in Coffee v/150 d/2024-02-22");
        hydrationList.addEntry("hydration in Tea v/200 d/2024-02-22");
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your hydration list!" + lineSeparator +
                "\t \t EntryID: 1, Date: 2024-02-22, Description: Coffee, Volume: 150" + lineSeparator +
                "\t The following entry has been added to your hydration list!" + lineSeparator +
                "\t \t EntryID: 2, Date: 2024-02-22, Description: Tea, Volume: 200" + lineSeparator +
                "\t Your Hydration List:" + lineSeparator +
                "\t 1. \t EntryID: 1, Date: 2024-02-22, Description: Coffee, Volume: 150" + lineSeparator +
                "\t 2. \t EntryID: 2, Date: 2024-02-22, Description: Tea, Volume: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(2, hydrationList.getSize());
    }
}
