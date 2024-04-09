//@@author a-wild-chocolate
package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.Entry;

import java.time.LocalDate;

public class SleepEntry extends Entry {

    private LocalDate date;
    private double duration;
    private int sleepEntryID;
    private static int sleepEntryNum=0;

    /***
     * Sleep constructor: date can be empty. If date input is empty, automatically fill with N/A;
     * date should be in format DDMMYY, duration should be a positive real number in hour unit.
     * @param date
     * @param duration
     */
    public SleepEntry (double duration, LocalDate date){
        super(sleepEntryNum++, "SLEEP", date);
        this.date = date;
        this.duration = duration;
    }
    public SleepEntry (int sleepEntryID,double duration, LocalDate date){
        super(sleepEntryID, "SLEEP", date);
        if(sleepEntryNum<sleepEntryID){
            sleepEntryNum=sleepEntryID+1;
        }
        this.date = date;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getDuration() {
        return duration;
    }

    public int getSleepEntryID() {return sleepEntryID;}

    public String toString() {
        return "\t Date: " + date +
                ", Duration: " + String.format("%.1f", duration) + " hours";
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";" + duration);
    }
}
//@@author
