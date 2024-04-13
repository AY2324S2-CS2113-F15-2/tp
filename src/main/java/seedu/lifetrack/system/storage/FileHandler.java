//@@author owx0130
package seedu.lifetrack.system.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.sleep.sleeplist.SleepEntry;
import seedu.lifetrack.system.exceptions.FileHandlerException;
import seedu.lifetrack.user.User;

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileDateLaterThanCurrentMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidCaloriesMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidEntryIDMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidDateMessage;

public class FileHandler {

    //public member for lastEntryID calories
    public static int maxHydrationID = 0;

    //general list constants
    protected final int ENTRYID_INDEX = 0;
    protected final int DATE_INDEX = 1;
    protected final int DESCRIPTION_INDEX = 2;

    //hydration list constants
    private final int VOLUME_INDEX = 3;

    //sleep list constants
    private final int DURATION_INDEX = 3;

    //user data constants
    private final int NAME_INDEX = 0;
    private final int HEIGHT_INDEX = 1;
    private final int WEIGHT_INDEX = 2;
    private final int AGE_INDEX = 3;
    private final int SEX_INDEX = 4;
    private final int EXERCISE_INDEX = 5;
    private final int GOAL_INDEX = 6;
    private final int REQ_CAL_INDEX = 7;

    //exception prefix strings
    protected final String NF_EXCEPTION_PREFIX = "For input string: \"";

    //error message for IO exception
    private final String message = "\t Unable to write to file!";

    protected String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    protected void checkDateNotLaterThanCurrent(LocalDate date, int lineNumber) throws FileHandlerException {
        if (date.isAfter(LocalDate.now())) {
            throw new FileHandlerException(getFileDateLaterThanCurrentMessage(lineNumber, filePath));
        }
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
            System.out.println(message);
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
            System.out.println(message);
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
        s.close();
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
        s.close();
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
        s.close();
        return data;
    }
}
