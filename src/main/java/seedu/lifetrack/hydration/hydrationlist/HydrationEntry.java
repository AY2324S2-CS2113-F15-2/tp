//@@author shawnpong
package seedu.lifetrack.hydration.hydrationlist;

import seedu.lifetrack.Entry;

import java.time.LocalDate;

public class HydrationEntry extends Entry {

    private int volume;

    public HydrationEntry(int lastHydrationEntryID, String description, int volume, LocalDate date){
        super(lastHydrationEntryID, description, date);
        this.volume= volume;
    }

    public  int getHydration() {
        return volume;
    }

    public int getVolume() {
        return volume;
    }

    public String toString() {
        return (super.toString() + ", Volume: " + volume);
    }

    public String toFileFriendlyString() {
        return (super.toFileFriendlyString() + ";" + volume);
    }
}
