//@@author paturikarthik
package seedu.lifetrack.user.usergoals;

import seedu.lifetrack.user.User;

import static seedu.lifetrack.LifeTrack.calorieList;
import static seedu.lifetrack.LifeTrack.hydrationList;
import static seedu.lifetrack.ui.UserUi.printUserCalorieProgress;
import static seedu.lifetrack.ui.UserUi.printUserHydrationProgress;

public class UserGoals {
    private static final int BMR_WEIGHT_MULTIPLIER = 10;
    private static final double BMR_HEIGHT_MULTIPLIER = 6.25;
    private static final int BMR_AGE_MULTIPLIER = 5;
    private static final int BMR_MALE_MODIFIER = 5;
    private static final int BMR_FEMALE_MODIFIER = -161;
    private static final int PROGRESS_BAR_WIDTH = 50;

    public static void getHealthInfo(User user) {
        double rawBMR = BMR_WEIGHT_MULTIPLIER * user.getWeight() + BMR_HEIGHT_MULTIPLIER * user.getHeight()
                - BMR_AGE_MULTIPLIER * user.getAge();
        String gender = user.getSex();
        int genderBMRModifier = gender.equals("male") ? BMR_MALE_MODIFIER : BMR_FEMALE_MODIFIER;
        int exerciseLevel = user.getExerciseLevels();
        double rawAMR = getAMR(rawBMR + genderBMRModifier, exerciseLevel);
        int goal = user.getGoal();
        int caloriesRequired = adjustAMRWithGoal(rawAMR,goal);
        user.setCaloriesRequired(caloriesRequired);
    }

    private static int adjustAMRWithGoal(double rawAMR, int goal) {
        if (goal == 1){
            rawAMR *= 0.8;
        } else if (goal == 2){
            rawAMR *= 0.9;
        } else if (goal == 4) {
            rawAMR *= 1.1;
        } else if (goal == 5) {
            rawAMR*=1.2;
        }
        return (int) rawAMR;
    }
    private static double getAMR(double calories, int exerciseLevel) {
        if (exerciseLevel == 1) {
            calories *= 1.2;
        } else if (exerciseLevel == 2) {
            calories *= 1.375;
        } else if (exerciseLevel == 3) {
            calories *= 1.55;
        } else if (exerciseLevel == 4) {
            calories *= 1.725;
        } else {
            calories *= 1.9;
        }
        return calories;
    }

    public static void getCaloriesProgressBar(User user) {
        int caloriesRequired = user.getCaloriesRequired();
        int caloriesConsumed = calorieList.getCaloriesConsumed();
        if (caloriesConsumed < 0){
            caloriesConsumed = 0;
        }
        double progress = (double) caloriesConsumed / caloriesRequired;
        int width = PROGRESS_BAR_WIDTH;

        int progressWidth = (int) (width * progress);
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            if (i < progressWidth) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] ");

        int percentage = (int) (progress * 100);
        printUserCalorieProgress(caloriesConsumed,caloriesRequired,progressBar.toString(), percentage);
    }

    public static void getHydrationProgressBar(User user) {
        int hydrationRequired = user.getHydrationRequired();
        int hydrationConsumed = hydrationList.getHydrationConsumed();
        double progress = (double) hydrationConsumed / hydrationRequired;
        int width = PROGRESS_BAR_WIDTH;

        int progressWidth = (int) (width * progress);
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            if (i < progressWidth) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] ");

        int percentage = (int) (progress * 100);
        printUserHydrationProgress(hydrationConsumed,hydrationRequired,progressBar.toString(),percentage);
    }
}
