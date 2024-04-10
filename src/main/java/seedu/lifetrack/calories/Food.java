//@@author owx0130
package seedu.lifetrack.calories;

public class Food {
    
    private int carbohydrates;
    private int proteins;
    private int fats;

    public Food(int carbohydrates, int proteins, int fats) {
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fats = fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }
}
