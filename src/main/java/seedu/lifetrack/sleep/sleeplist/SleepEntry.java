package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.Entry;

import java.time.LocalDate;

public class SleepEntry extends Entry {

    private LocalDate date;
    private double duration;
    private int sleepEntryID;

    /***
     * Sleep constructor: date can be empty. If date input is empty, automatically fill with N/A;
     * date should be in format DDMMYY, duration should be a positive real number in hour unit.
     * @param date
     * @param duration
     */
    public SleepEntry (int sleepEntryID, double duration, LocalDate date){
        super(sleepEntryID, "SLEEP", date);
        this.date = date;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getDuration() {
        return duration;
    }

    public String toString() {
        return "\t Date: " + date +
                ", Duration: " + String.format("%.1f", duration) + " hours";
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";" + duration);
    }
}
