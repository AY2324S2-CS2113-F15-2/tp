package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calorielist.CalorieList;


class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 1");
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        calorieList.addEntry("calories in d/2024-03-14 t/13:00 a/Eat c/200");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 2");
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 2"); // Index out of bounds
        calorieList.deleteEntry("delete -1");
        assertEquals(initialSize, calorieList.getSize());
    }

}
