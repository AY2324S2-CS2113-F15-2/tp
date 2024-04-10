//@@author paturikarthik
package seedu.lifetrack.user;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.user.usergoals.UserGoals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static seedu.lifetrack.system.parser.ParserUser.parseSetUp;
import static seedu.lifetrack.system.parser.ParserUser.parseUpdate;

public class User {

    private FileHandler fileHandler;
    private String name;
    private int height;
    private int weight;
    private int age;
    private String sex;
    private int exerciseLevels;
    private int goal;

    private int caloriesRequired;
    private int hydrationRequired = 2000;

    //user data constants
    private final int NAME_INDEX = 0;
    private final int HEIGHT_INDEX = 1;
    private final int WEIGHT_INDEX = 2;
    private final int AGE_INDEX = 3;
    private final int SEX_INDEX = 4;
    private final int EXERCISE_INDEX = 5;
    private final int GOAL_INDEX = 6;
    private final int REQ_CAL_INDEX = 7;

    //constructor for JUnit tests
    public User() {

    }

    //constructor for usage in terminal
    public User(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            ArrayList<String> data = fileHandler.getUserDataFromFile();
            name = data.get(NAME_INDEX);
            height = Integer.parseInt(data.get(HEIGHT_INDEX));
            weight = Integer.parseInt(data.get(WEIGHT_INDEX));
            age = Integer.parseInt(data.get(AGE_INDEX));
            sex = data.get(SEX_INDEX);
            exerciseLevels = Integer.parseInt(data.get(EXERCISE_INDEX));
            goal = Integer.parseInt(data.get(GOAL_INDEX));
            caloriesRequired = Integer.parseInt(data.get(REQ_CAL_INDEX));
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public void setUp(String line) {
        try {
            parseSetUp(line, this);
            fileHandler.writeUserData(this);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String line) {
        try {
            parseUpdate(line, this);
            fileHandler.writeUserData(this);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setExerciseLevels(int exerciseLevels) {
        this.exerciseLevels = exerciseLevels;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getName() {
        return name;
    }
    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getExerciseLevels() {
        return exerciseLevels;
    }

    public int getGoal() {
        return goal;
    }

    public void getHealthInfo() {
        UserGoals.getHealthInfo(this);
    }

    public void setCaloriesRequired(int caloriesRequired) {
        this.caloriesRequired = caloriesRequired;
    }

    public int getCaloriesRequired() {
        return caloriesRequired;
    }

    public int getHydrationRequired() {
        return hydrationRequired;
    }

    public String toFileFriendlyString() {
        return String.format(name + ";" + height + ";" + weight + ";" + age + ";" + sex + ";" +
                exerciseLevels + ";" + goal + ";" + caloriesRequired);
    }

    public void getCaloriesProgressBar() {
        UserGoals.getCaloriesProgressBar(this);
    }

    public void getHydrationProgressBar() {
        UserGoals.getHydrationProgressBar(this);
    }
}
