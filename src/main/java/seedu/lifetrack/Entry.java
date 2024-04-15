//@@author owx0130
package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;

import java.time.LocalDate;

public abstract class Entry {

    private String description;
    private LocalDate date;
    private int entryID;

    public Entry(int lastEntryID, String description, LocalDate date){
        this.entryID = lastEntryID;
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getEntryID() {
        return entryID;
    }

    public String toString() {
        if(this instanceof InputEntry) {
            return ("\t caloriesID: " + entryID + ", Date: " + date +
                    ", Description: " + description);
        } else if (this instanceof OutputEntry) {
            return ("\t caloriesID: " + entryID + ", Date: " + date + ", " +
                    "Description: " + description);
        } else {
            return ("\t hydrationID: " + entryID + ", Date: " + date + ", " +
                    "Description: " + description);
        }
    }

    public String toFileFriendlyString() {
        return (entryID + ";" + date + ";" + description);
    }
}
