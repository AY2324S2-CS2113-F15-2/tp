//@@author owx0130
package seedu.lifetrack;

import java.time.LocalDate;

public abstract class Entry {

    private String description;
    private LocalDate date;

    private int lastEntryID;

    public Entry(int lastEntryID, String description, LocalDate date){
        this.lastEntryID = lastEntryID;
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLastEntryID() {
        return lastEntryID;
    }

    public String toString() {
        return String.format("\t EntryID: " + lastEntryID + ", Date: " + date + ", Description: " + description);
    }

    public String toFileFriendlyString() {
        return String.format(lastEntryID + ";" + date + ";" + description);
    }
}
