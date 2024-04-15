//@@author rexyyong
package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;

import java.time.LocalDate;

/**
 * Represents an entry for calories intake.
 * Extends the Entry class and includes additional fields and methods specific to input entries.
 */
public class InputEntry extends Entry {
    
    private Food food;
    private int calories;
    private boolean doesFoodExist = false;

    /**
     * Constructs a new InputEntry object with the given description, calories, and date.
     *
     * @param description the description of the entry
     * @param calories the number of calories consumed
     * @param date the date of the entry
     */
    public InputEntry(int lastEntryID, String description, int calories, LocalDate date) {
        super(lastEntryID, description, date);
        this.calories = calories;
    }

    /**
     * Constructs a new InputEntry object with the given description, calories, date,
     * and food details with macronutrients.
     *
     * @param description the description of the entry
     * @param calories the number of calories consumed
     * @param date the date of the entry
     * @param food the food details with macronutrients associated with the entry
     */
    public InputEntry(int lastEntryID, String description, int calories, LocalDate date, Food food) {
        super(lastEntryID, description, date);
        this.food = food;
        this.calories = calories;
        this.doesFoodExist = true;
    }

    public Food getFood() {
        return food;
    }

    public int getCalories() {
        return this.calories;
    }

    public String toString() {
        return (super.toString() + ", Calories: " + calories + (doesFoodExist ?
                " (C: " + food.getCarbohydrates() +
                ", P: " + food.getProteins() +
                ", F: " + food.getFats() + ")"
                : ""));
    }

    public String toFileFriendlyString() {
        return (super.toFileFriendlyString() + ";C_IN;" + calories +
                (doesFoodExist ? ";" + food.getCarbohydrates() + ";" + food.getProteins() + ";" + food.getFats()
                : ""));
    }
}
