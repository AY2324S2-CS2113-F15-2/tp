package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.sleep.sleeplist.SleepList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepListTest {
    @Test
    public void testDeleteSleepValidIndex(){
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-03-03");
        sleepList.addSleep("sleep add 8 d/2024-12-10");
        sleepList.printSleepList();
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete "+sleepList.getSleep(0).getEntryID());
        assertEquals(initialSize - 1, sleepList.getSize());
    }
    @Test
    public void testDeleteSleepInvalidIndex() {
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-13-10");
        sleepList.addSleep("sleep add 8");
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete 20"); // Index out of bounds
        sleepList.deleteSleep("sleep delete -2");
        assertEquals(initialSize, sleepList.getSize());
    }
    @Test
    public void testPrintSleepListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t Your sleep list is empty. Add new entries to populate your list :)" +
                lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testPrintSleepListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-03-11");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Sleep ID: 0, Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator +
                "\t Your Sleep List:" + lineSeparator +
                "\t 1. \t Sleep ID: 0, Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintSleepListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-03-11");
        sleepList.addSleep("sleep add 8.0 d/2024-04-09");
        sleepList.addSleep("sleep add 4.2 d/2024-02-21");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Sleep ID: 1, Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator +
                "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Sleep ID: 2, Date: 2024-04-09, Duration: 8.0 hours" + lineSeparator +
                "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Sleep ID: 3, Date: 2024-02-21, Duration: 4.2 hours" + lineSeparator +
                "\t Your Sleep List:" + lineSeparator +
                "\t 1. \t Sleep ID: 1, Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator +
                "\t 2. \t Sleep ID: 2, Date: 2024-04-09, Duration: 8.0 hours" + lineSeparator +
                "\t 3. \t Sleep ID: 3, Date: 2024-02-21, Duration: 4.2 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, sleepList.getSize());
    }
}
