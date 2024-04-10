package seedu.lifetrack.ui;

import seedu.lifetrack.user.User;

public class UserUi {
    private static final String CHANGE_MADE_MESSAGE = "\t The following change has been made:\n";

    public static void printUserCaloriesRequired(int caloriesRequired) {
        System.out.println("\t You need to consume " + caloriesRequired + " calories per day to hit your goals!");
    }

    public static void printUserCalorieProgress(int caloriesConsumed, int caloriesRequired, String progressBar,
                                                int percentage) {
        System.out.print("\t Calories:\n");
        System.out.printf("\t You have consumed " + caloriesConsumed + " calories out of your goal of "
                + caloriesRequired + " calories so far.\n");
        System.out.printf("\t %s %d%%\n\n", progressBar, percentage);
    }

    public static void printUserHydrationProgress(int hydrationConsumed, int hydrationRequired, String progressBar,
                                                  int percentage) {
        System.out.print("\t Hydration:\n");
        System.out.printf("\t You have consumed " + hydrationConsumed + "ml out of your goal of "
                + hydrationRequired + "ml so far.\n");
        System.out.printf("\t %s %d%%\n", progressBar, percentage);
    }

    public static void printUserSetUpComplete(User user) {
        System.out.println("\t Hello, " + user.getName() + "! Thank you for completing the setup :)");
        printUserCaloriesRequired(user.getCaloriesRequired());
        printUserDetails(user);
    }

    public static void printNoUserYetMessage() {
        System.out.println("\t Please set up your profile first!");
    }

    public static void printNewUserName(String name) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Name: " + name);
    }

    public static void printNewUserAge(int age) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Age: " + age);
    }

    public static void printNewUserHeight(int height) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Height: " + height);
    }

    public static void printNewUserWeight(int weight) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Weight: " + weight);
    }

    public static void printNewUserSex(String sex) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Sex: " + sex);
    }

    public static void printNewUserExerciseLevels(User user, int level) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Exercise Levels: " + level + " out of 5 ("
                + user.getExerciseLevelAsString() + ")");
    }

    public static void printNewUserGoal(User user, int goal) {
        System.out.println(CHANGE_MADE_MESSAGE + "\t Goal: " + goal + " out of 5 ("
                + user.getGoalAsString() + ")");
    }

    public static void printUserDetails(User user) {
        System.out.println("\t User details:\n" +
                "\t Name: " + user.getName() + "\n"
                + "\t Height: " + user.getHeight() + "\n"
                + "\t Weight: " + user.getWeight() + "\n"
                + "\t Age: " + user.getAge() + "\n"
                + "\t Sex: " + user.getSex() + "\n"
                + "\t Exercise Levels: " + user.getExerciseLevels() + " out of 5 ("
                + user.getExerciseLevelAsString() + ")" + "\n"
                + "\t Goal: " + user.getGoal() + " out of 5 (" + user.getGoalAsString() + ")" + "\n");
    }
}
