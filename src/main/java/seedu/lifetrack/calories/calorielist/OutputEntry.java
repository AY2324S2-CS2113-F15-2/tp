//@@author rexyyong
package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Activity;

import java.time.LocalDate;

/**
 * Represents an entry for calories output.
 * Extends the Entry class and includes additional fields and methods specific to output entries.
 */
public class OutputEntry extends Entry {

    private Activity activity;
    private int calories;
    private boolean doesActivityExist = false;

    /**
     * Constructs a new OutputEntry object with the given description, calories, and date.
     *
     * @param description the description of the entry
     * @param calories the number of calories burnt
     * @param date the date of the entry
     */
    public OutputEntry(String description, int calories, LocalDate date) {
        super(description, date);
        this.calories = calories;
    }

    /**
     * Constructs a new OutputEntry object with the given description, calories, date, and activity details.
     *
     * @param description the description of the entry
     * @param calories the number of calories burnt
     * @param date the date of the entry
     * @param activity the activity details associated with the entry
     */
    public OutputEntry(String description, int calories, LocalDate date, Activity activity) {
        super(description, date);
        this.activity = activity;
        this.calories = calories;
        this.doesActivityExist = true;
    }

    public Activity getActivity() {
        return activity;
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
