//@@author shawnpong
package seedu.lifetrack.system.parser;

import seedu.lifetrack.Entry;
import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.hydration.hydrationlist.HydrationList;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.*;

public class ParserHydration {
    private static final int VOLUME_IDX = 1;
    private static final int DATE_IDX = 2;
    private static final int HYDRATION_ADD_PADDING = 13;
    private static final int VOLUME_MAX = 10000;
    private static final int HYDRATION_FIND_LENGTH = "hydration find".length();

    /**
     * Parses a string input to create a Liquid object representing liquid intake.
     *
     * This method expects the input string to follow a specific format, where the
     * beverage name and quantity are separated by the delimiters 'b/' and 'v/'.
     * The method extracts these components and creates a Liquid object.
     * If any part of the input is missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing beverage name and quantity
     * @return a Liquid object representing liquid intake
     * @throws InvalidInputException if the input string is missing components or
     *                              contains empty fields
     */
    public static Entry parseHydrationInput(String input, int lastHydrationEntryID) throws InvalidInputException {
        
        int volumeIndex = input.indexOf("v/");
        int dateIndex = input.indexOf("d/");

        checkKeywordsExist(dateIndex, volumeIndex);
        assert volumeIndex != -1 : "The v/ keyword should exist!";
        assert dateIndex != -1 : "The d/ keyword should exist!";

        checkKeywordsCorrectlyOrdered(dateIndex, volumeIndex);
        assert volumeIndex < dateIndex : "The v/ keyword must appear before date/ in the input!";

        String[] parts = input.split("v/|d/");
        String description = getDescriptionFromInput(input, volumeIndex);
        String strVolume = parts[VOLUME_IDX].trim();
//        String strDate = parts[DATE_IDX].trim();

        String strDate = null;
        try {
            strDate = parts[DATE_IDX].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException(getHydrationWhitespaceInInputMessage());
        }

        checkInputsAreNonEmpty(description, strVolume, strDate);
        assert description != "" : "The description field should be a non-empty string!";
        assert strVolume != "" : "The volume field should be a non-empty string!";
        assert strDate != "" : "The date field should be a non-empty string!";

        int volume = getIntegerVolumeFromInput(strVolume);
        checkVolumeIsPositiveInteger(volume);
        checkVolumeMaxAllowable(volume);
        assert volume > 0 : "Volume value must be a positive integer!";

        //@@author rexyyong
        // Convert strDate from type String to date of type LocalDate
        LocalDate date = null;
        try {
            date = getLocalDateFromInput(strDate);
            if (date.isAfter(LocalDate.now())) {
                throw new InvalidInputException(getDateLaterThanPresentDateMessage());
            }
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(getInvalidDateMessage());
        }
        //@@author
        lastHydrationEntryID++;
        return makeNewInputEntry(lastHydrationEntryID, description, volume, date);
    }

    private static void checkVolumeMaxAllowable(int volume) throws InvalidInputException {
        if (volume > VOLUME_MAX) {
            throw new InvalidInputException(getHydrationOverVolumeLimitMessage());
        }
    }

    /**
     * Creates a new HydrationEntry object with the specified description, volume, and date.
     *
     * @param description the description of the hydration entry
     * @param volume the volume of liquid intake
     * @param date the date of the hydration entry
     * @return a new HydrationEntry object with the specified attributes
     */
    private static HydrationEntry makeNewInputEntry(int lastHydrationEntryID, String description, int volume,
                                                    LocalDate date) {
        return new HydrationEntry(lastHydrationEntryID, description, volume, date);
    }

    /**
     * Parses a string representation of a date and returns a LocalDate object.
     *
     * @param strDate the string representation of the date
     * @return a LocalDate object representing the parsed date
     * @throws DateTimeParseException if the input string cannot be parsed into a valid date
     */
    //@@author rexyyong
    public static LocalDate getLocalDateFromInput(String strDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(strDate);
        return date;
    }
    //@@author
    /**
     * Parses the volume from a string and returns the integer value.
     *
     * @param strVolume the string representation of the volume
     * @return the integer value of the volume
     */
    private static int getIntegerVolumeFromInput(String strVolume) {
        int volume = 0;
        try {
            volume = Integer.parseInt(strVolume);
        } catch (NumberFormatException e) {
            System.out.println(getIncorrectVolumeInputMessage());
        }
        return volume;
    }

    /**
     * Checks if the given volume is a positive integer.
     *
     * @param volume the volume value to be checked
     * @throws InvalidInputException if the volume is not a positive integer
     */
    private static void checkVolumeIsPositiveInteger(int volume) throws InvalidInputException {
        if (volume <= 0) {
            throw new InvalidInputException(getHydrationNegativeIntegerVolumeMessage());
        }
    }

    /**
     * Checks if the description, volume, and date fields are non-empty.
     *
     * @param description the description of the hydration entry
     * @param strVolume the string representation of the volume
     * @param date the string representation of the date
     * @throws InvalidInputException if any of the fields are empty
     */
    private static void checkInputsAreNonEmpty(String description, String strVolume, String date)
            throws InvalidInputException {
        if (description.isEmpty() || strVolume.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException(getHydrationEmptyDescriptionMessage());
        }
    }

    /**
     * Extracts the description from the input string.
     *
     * @param inputString the input string containing description, volume, and date
     * @param volumeIndex the index of the 'v/' delimiter
     * @return the description extracted from the input string
     */
    private static String getDescriptionFromInput(String inputString, int volumeIndex) {
        String description;
        description = inputString.substring(HYDRATION_ADD_PADDING, volumeIndex).trim();
        return description;
    }

    /**
     * Checks if the 'v/' and 'd/' keywords are in the correct order in the input string.
     *
     * @param dateIndex the index of the 'd/' keyword
     * @param volumeIndex the index of the 'v/' keyword
     * @throws InvalidInputException if the keywords are in the incorrect order
     */
    private static void checkKeywordsCorrectlyOrdered(int dateIndex, int volumeIndex) throws InvalidInputException {
        if (volumeIndex >= dateIndex) {
            throw new InvalidInputException(getHydrationIncorrectOrderMessage());
        }
    }

    /**
     * Checks if the 'v/' and 'd/' keywords exist in the input string.
     *
     * @param dateIndex the index of the 'd/' keyword
     * @param volumeIndex the index of the 'v/' keyword
     * @throws InvalidInputException if any of the keywords are missing
     */
    private static void checkKeywordsExist(int dateIndex, int volumeIndex) throws InvalidInputException {
        if (dateIndex == -1 || volumeIndex == -1) {
            throw new InvalidInputException(getHydrationMissingKeywordMessage());
        }
    }

    //@@author paturikarthik
    public static void findHydrationListEntries(String input, HydrationList originalList){
        String keyword = input.substring(HYDRATION_FIND_LENGTH).trim();
        HydrationList searchList = new HydrationList();
        for (int i = 0 ; i <originalList.getSize();i++){
            Entry entry = originalList.getEntry(i);
            if (entry.getDescription().contains(keyword)){
                searchList.addHydrationEntry(entry);
            }
        }
        searchList.printFoundHydrationList();
    }

}
