//@@author rexyyong
package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;

import java.time.LocalDate;

/**
 * Represents an entry for calories output.
 * Extends the Entry class and includes additional fields and methods specific to output entries.
 */
public class OutputEntry extends Entry {

    private int calories;

    /**
     * Constructs a new OutputEntry object with the given description, calories, and date.
     *
     * @param description the description of the entry
     * @param calories the number of calories burnt
     * @param date the date of the entry
     */
    public OutputEntry(int lastEntryID, String description, int calories, LocalDate date) {
        super(lastEntryID, description, date);
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return String.format(super.toString() + ", Calories: " + calories);
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";C_OUT;" + calories);
    }
}
