package seedu.lifetrack.hydration.hydrationlist;

import seedu.lifetrack.Entry;

import java.time.LocalDate;

public class HydrationEntry extends Entry {

    private int volume;

    public HydrationEntry(String description, int volume, LocalDate date){
        super(description, date);
        this.volume= volume;
    }

    public  int getHydration() {
        return volume;
    }

    public int getVolume() {
        return volume;
    }

    public String toString() {
        return String.format(super.toString() + ", Volume: " + volume);
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";" + volume);
    }
}
