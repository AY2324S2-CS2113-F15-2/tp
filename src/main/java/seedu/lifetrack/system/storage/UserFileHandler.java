package seedu.lifetrack.system.storage;

import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidAgeMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidExerciseLevelMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidGoalMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidHeightMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidReqCalMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidSexMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileInvalidWeightMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileUserEmptyNameMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileUserTooFewFieldsMessage;
import static seedu.lifetrack.system.exceptions.FileHandlerExceptionMessage.getFileUserTooManyFieldsMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.lifetrack.system.exceptions.FileHandlerException;
import seedu.lifetrack.user.User;

public class UserFileHandler extends FileHandler {
    
    //user data constants
    private final int NAME_INDEX = 0;
    private final int HEIGHT_INDEX = 1;
    private final int WEIGHT_INDEX = 2;
    private final int AGE_INDEX = 3;
    private final int SEX_INDEX = 4;
    private final int EXERCISE_LEVEL_INDEX = 5;
    private final int GOAL_INDEX = 6;
    private final int REQ_CAL_INDEX = 7;

    public UserFileHandler(String filePath) {
        super(filePath);
    }

    private void checkCorrectNumberOfFields(int dataLength) throws FileHandlerException {
        if (dataLength < 8) {
            throw new FileHandlerException(getFileUserTooFewFieldsMessage(filePath));
        } else if (dataLength > 8) {
            throw new FileHandlerException(getFileUserTooManyFieldsMessage(filePath));
        }
    }

    private void checkNonEmptyName(String name) throws FileHandlerException {
        if (name.equals("")) {
            throw new FileHandlerException(getFileUserEmptyNameMessage(filePath));
        }
    }

    private void checkValidHeight(int height) throws FileHandlerException {
        if (!(height >= 90 && height <= 225)) {
            throw new FileHandlerException(getFileInvalidHeightMessage(filePath));
        }
    }

    private void checkValidWeight(int weight) throws FileHandlerException {
        if (!(weight >= 30 && weight <= 200)) {
            throw new FileHandlerException(getFileInvalidWeightMessage(filePath));
        }
    }

    private void checkValidAge(int age) throws FileHandlerException {
        if (!(age >= 13 && age <= 30)) {
            throw new FileHandlerException(getFileInvalidAgeMessage(filePath));
        }
    }

    private void checkValidSex(String sex) throws FileHandlerException {
        if (!(sex.equals("male") || sex.equals("female"))) {
            throw new FileHandlerException(getFileInvalidSexMessage(filePath));
        }
    }

    private void checkValidExerciseLevel(int exerciseLevel) throws FileHandlerException {
        if (!(exerciseLevel >= 1 && exerciseLevel <= 5)) {
            throw new FileHandlerException(getFileInvalidExerciseLevelMessage(filePath));
        }
    }

    private void checkValidGoal(int goal) throws FileHandlerException {
        if (!(goal >= 1 && goal <= 5)) {
            throw new FileHandlerException(getFileInvalidGoalMessage(filePath));
        }
    }

    private void checkReqCalIsPositive(int requiredCals) throws FileHandlerException {
        if (requiredCals <= 0) {
            throw new FileHandlerException(getFileInvalidReqCalMessage(filePath));
        }
    }

    public ArrayList<String> getUserDataFromFile(User user) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> data = new ArrayList<>();
        String line = s.nextLine();
        String[] words = line.split(";");
        try {
            checkCorrectNumberOfFields(words.length);
            String name = words[NAME_INDEX];
            checkNonEmptyName(name);
            int height = Integer.parseInt(words[HEIGHT_INDEX]);
            checkValidHeight(height);
            int weight = Integer.parseInt(words[WEIGHT_INDEX]);
            checkValidWeight(weight);
            int age = Integer.parseInt(words[AGE_INDEX]);
            checkValidAge(age);
            String sex = words[SEX_INDEX];
            checkValidSex(sex);
            int exerciseLevel = Integer.parseInt(words[EXERCISE_LEVEL_INDEX]);
            checkValidExerciseLevel(exerciseLevel);
            int goal = Integer.parseInt(words[GOAL_INDEX]);
            checkValidGoal(goal);
            int requiredCals = Integer.parseInt(words[REQ_CAL_INDEX]);
            checkReqCalIsPositive(requiredCals);
            data.add(words[NAME_INDEX]);
            data.add(words[HEIGHT_INDEX]);
            data.add(words[WEIGHT_INDEX]);
            data.add(words[AGE_INDEX]);
            data.add(words[SEX_INDEX]);
            data.add(words[EXERCISE_LEVEL_INDEX]);
            data.add(words[GOAL_INDEX]);
            data.add(words[REQ_CAL_INDEX]);
        } catch (NumberFormatException e) {
            if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[HEIGHT_INDEX] + "\"")) {
                System.out.println(getFileInvalidHeightMessage(filePath));
            } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[WEIGHT_INDEX] + "\"")) {
                System.out.println(getFileInvalidWeightMessage(filePath));
            } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[AGE_INDEX] + "\"")) {
                System.out.println(getFileInvalidAgeMessage(filePath));
            } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[EXERCISE_LEVEL_INDEX] + "\"")) {
                System.out.println(getFileInvalidExerciseLevelMessage(filePath));
            } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[GOAL_INDEX] + "\"")) {
                System.out.println(getFileInvalidGoalMessage(filePath));
            } else if (e.getMessage().equals(NF_EXCEPTION_PREFIX + words[REQ_CAL_INDEX] + "\"")) {
                System.out.println(getFileInvalidReqCalMessage(filePath));
            }
        } catch (FileHandlerException e) {
            System.out.println(e.getMessage());
        }
        s.close();
        return data;
    }
}
