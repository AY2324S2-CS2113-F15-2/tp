//@@author paturikarthik
package seedu.lifetrack.user.usergoals;

import seedu.lifetrack.user.User;

import java.time.LocalDate;

import static seedu.lifetrack.LifeTrack.calorieList;
import static seedu.lifetrack.LifeTrack.hydrationList;
import static seedu.lifetrack.LifeTrack.sleepList;
import static seedu.lifetrack.ui.UserUi.printUserCalorieProgress;
import static seedu.lifetrack.ui.UserUi.printUserHydrationProgress;
import static seedu.lifetrack.ui.UserUi.printUserSleepProgress;

public class UserGoals {
    private static final int BMR_WEIGHT_MULTIPLIER = 10;
    private static final double BMR_HEIGHT_MULTIPLIER = 6.25;
    private static final int BMR_AGE_MULTIPLIER = 5;
    private static final int BMR_MALE_MODIFIER = 5;
    private static final int BMR_FEMALE_MODIFIER = -161;
    private static final int PROGRESS_BAR_WIDTH = 50;
    private static final int INDEX_OF_TODAY = 0;
    private static final int INDEX_OF_YESTERDAY = 1;
    private static final int INDEX_OF_DAY_BEFORE = 2;
    private static final int NUMBER_OF_DAYS_TO_TRACK = 3;
    private static final int NUMBER_OF_DAYS_TO_YESTERDAY = -1;
    private static final int NUMBER_OF_DAYS_TO_DAY_BEFORE = -2;

    public static void getHealthInfo(User user) {
        double rawBMR = BMR_WEIGHT_MULTIPLIER * user.getWeight() + BMR_HEIGHT_MULTIPLIER * user.getHeight()
                - BMR_AGE_MULTIPLIER * user.getAge();
        String gender = user.getSex();
        int genderBMRModifier = gender.equals("male") ? BMR_MALE_MODIFIER : BMR_FEMALE_MODIFIER;
        int exerciseLevel = user.getExerciseLevels();
        double rawAMR = getAMR(rawBMR + genderBMRModifier, exerciseLevel);
        int goal = user.getGoal();
        int caloriesRequired = adjustAMRWithGoal(rawAMR, goal);
        user.setCaloriesRequired(caloriesRequired);
    }

    private static int adjustAMRWithGoal(double rawAMR, int goal) {
        if (goal == 1) {
            rawAMR *= 0.8;
        } else if (goal == 2) {
            rawAMR *= 0.9;
        } else if (goal == 4) {
            rawAMR *= 1.1;
        } else if (goal == 5) {
            rawAMR *= 1.2;
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
        int caloriesConsumedToday = calorieList.getCaloriesConsumed(LocalDate.now());
        if (caloriesConsumedToday < 0) {
            caloriesConsumedToday = 0;
        }
        double progressToday = (double) caloriesConsumedToday / caloriesRequired;
        int caloriesConsumedYesterday = calorieList.getCaloriesConsumed
                (LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_YESTERDAY));
        if (caloriesConsumedYesterday < 0) {
            caloriesConsumedYesterday = 0;
        }
        double progressYesterday = (double) caloriesConsumedYesterday / caloriesRequired;
        int width = PROGRESS_BAR_WIDTH;

        int caloriesConsumedDayBefore = calorieList.getCaloriesConsumed
                (LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_DAY_BEFORE));
        if (caloriesConsumedDayBefore < 0) {
            caloriesConsumedDayBefore = 0;
        }
        double progressDayBefore = (double) caloriesConsumedDayBefore / caloriesRequired;

        double[] progress = new double[NUMBER_OF_DAYS_TO_TRACK];
        progress[INDEX_OF_TODAY] = progressToday;
        progress[INDEX_OF_YESTERDAY] = progressYesterday;
        progress[INDEX_OF_DAY_BEFORE] = progressDayBefore;

        int[] caloriesConsumed = new int[NUMBER_OF_DAYS_TO_TRACK];
        caloriesConsumed[INDEX_OF_TODAY] = caloriesConsumedToday;
        caloriesConsumed[INDEX_OF_YESTERDAY] = caloriesConsumedYesterday;
        caloriesConsumed[INDEX_OF_DAY_BEFORE] = caloriesConsumedDayBefore;

        for (int date = 0; date < NUMBER_OF_DAYS_TO_TRACK; date++) {
            int progressWidth = (int) (width * progress[date]);
            StringBuilder progressBar = new StringBuilder("[");
            for (int i = 0; i < width; i++) {
                if (i < progressWidth) {
                    progressBar.append("=");
                } else {
                    progressBar.append(" ");
                }
            }
            progressBar.append("] ");

            int percentage = (int) (progress[date] * 100);
            printUserCalorieProgress(caloriesConsumed[date], caloriesRequired, progressBar.toString(),
                    percentage, date);
        }
    }

    public static void getHydrationProgressBar(User user) {
        int hydrationRequired = user.getHydrationRequired();
        int hydrationConsumedToday = hydrationList.getHydrationConsumed(LocalDate.now());
        if (hydrationConsumedToday < 0) {
            hydrationConsumedToday = 0;
        }
        double progressToday = (double) hydrationConsumedToday / hydrationRequired;
        int hydrationConsumedYesterday = hydrationList.getHydrationConsumed
                (LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_YESTERDAY));
        if (hydrationConsumedYesterday < 0) {
            hydrationConsumedYesterday = 0;
        }
        double progressYesterday = (double) hydrationConsumedYesterday / hydrationRequired;
        int width = PROGRESS_BAR_WIDTH;

        int hydrationConsumedDayBefore = hydrationList.getHydrationConsumed
                (LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_DAY_BEFORE));
        if (hydrationConsumedDayBefore < 0) {
            hydrationConsumedDayBefore = 0;
        }
        double progressDayBefore = (double) hydrationConsumedDayBefore / hydrationRequired;

        double[] progress = new double[NUMBER_OF_DAYS_TO_TRACK];
        progress[INDEX_OF_TODAY] = progressToday;
        progress[INDEX_OF_YESTERDAY] = progressYesterday;
        progress[INDEX_OF_DAY_BEFORE] = progressDayBefore;

        int[] hydrationConsumed = new int[NUMBER_OF_DAYS_TO_TRACK];
        hydrationConsumed[INDEX_OF_TODAY] = hydrationConsumedToday;
        hydrationConsumed[INDEX_OF_YESTERDAY] = hydrationConsumedYesterday;
        hydrationConsumed[INDEX_OF_DAY_BEFORE] = hydrationConsumedDayBefore;

        for (int date = 0; date < NUMBER_OF_DAYS_TO_TRACK; date++) {

            int progressWidth = (int) (width * progress[date]);
            StringBuilder progressBar = new StringBuilder("[");
            for (int i = 0; i < width; i++) {
                if (i < progressWidth) {
                    progressBar.append("=");
                } else {
                    progressBar.append(" ");
                }
            }
            progressBar.append("] ");

            int percentage = (int) (progress[date] * 100);
            printUserHydrationProgress(hydrationConsumed[date], hydrationRequired, progressBar.toString(),
                    percentage, date);
        }
    }

    public static void getSleepProgressBar(User user) {
        int sleepRequired = user.getSleepRequired();
        int sleepConsumedToday = sleepList.getSleepConsumed(LocalDate.now());
        if (sleepConsumedToday < 0) {
            sleepConsumedToday = 0;
        }
        double progressToday = (double) sleepConsumedToday / sleepRequired;
        int sleepConsumedYesterday = sleepList.getSleepConsumed
                (LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_YESTERDAY));
        if (sleepConsumedYesterday < 0) {
            sleepConsumedYesterday = 0;
        }
        double progressYesterday = (double) sleepConsumedYesterday / sleepRequired;
        int width = PROGRESS_BAR_WIDTH;

        int sleepConsumedDayBefore = sleepList.getSleepConsumed
                (LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_DAY_BEFORE));
        if (sleepConsumedDayBefore < 0) {
            sleepConsumedDayBefore = 0;
        }
        double progressDayBefore = (double) sleepConsumedDayBefore / sleepRequired;

        double[] progress = new double[NUMBER_OF_DAYS_TO_TRACK];
        progress[INDEX_OF_TODAY] = progressToday;
        progress[INDEX_OF_YESTERDAY] = progressYesterday;
        progress[INDEX_OF_DAY_BEFORE] = progressDayBefore;

        int[] sleepConsumed = new int[NUMBER_OF_DAYS_TO_TRACK];
        sleepConsumed[INDEX_OF_TODAY] = sleepConsumedToday;
        sleepConsumed[INDEX_OF_YESTERDAY] = sleepConsumedYesterday;
        sleepConsumed[INDEX_OF_DAY_BEFORE] = sleepConsumedDayBefore;

        for (int date = 0; date < NUMBER_OF_DAYS_TO_TRACK; date++) {
            int progressWidth = (int) (width * progress[date]);
            StringBuilder progressBar = new StringBuilder("[");
            for (int i = 0; i < width; i++) {
                if (i < progressWidth) {
                    progressBar.append("=");
                } else {
                    progressBar.append(" ");
                }
            }
            progressBar.append("] ");

            int percentage = (int) (progress[date] * 100);
            printUserSleepProgress(sleepConsumed[date], sleepRequired, progressBar.toString(),
                    percentage, date);
        }
    }
}
