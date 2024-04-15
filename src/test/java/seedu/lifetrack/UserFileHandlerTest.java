package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.system.storage.UserFileHandler;
import seedu.lifetrack.user.User;

public class UserFileHandlerTest {
    
    //user data constants
    private final int NAME_INDEX = 0;
    private final int HEIGHT_INDEX = 1;
    private final int WEIGHT_INDEX = 2;
    private final int AGE_INDEX = 3;
    private final int SEX_INDEX = 4;
    private final int EXERCISE_INDEX = 5;
    private final int GOAL_INDEX = 6;
    private final int REQ_CAL_INDEX = 7;

    private String filePath = "sample-data/userTestData.txt";
    private UserFileHandler fileHandler = new UserFileHandler(filePath);

    @Test
    public void getUserDataFromFile_correctUserInput_allUserDataRetrievedFromFile() {
        try {
            User expectedUser = new User();
            expectedUser.setName("john");
            expectedUser.setHeight(170);
            expectedUser.setWeight(90);
            expectedUser.setAge(23);
            expectedUser.setSex("male");
            expectedUser.setExerciseLevels(5);
            expectedUser.setGoal(5);
            expectedUser.setCaloriesRequired(1900);
            fileHandler.writeUserData(expectedUser);
            ArrayList<String> data = fileHandler.getUserDataFromFile();
            assertEquals(expectedUser.getName(), data.get(NAME_INDEX));
            assertEquals(expectedUser.getHeight(), Integer.parseInt(data.get(HEIGHT_INDEX)));
            assertEquals(expectedUser.getWeight(), Integer.parseInt(data.get(WEIGHT_INDEX)));
            assertEquals(expectedUser.getAge(), Integer.parseInt(data.get(AGE_INDEX)));
            assertEquals(expectedUser.getSex(), data.get(SEX_INDEX));
            assertEquals(expectedUser.getExerciseLevels(), Integer.parseInt(data.get(EXERCISE_INDEX)));
            assertEquals(expectedUser.getGoal(), Integer.parseInt(data.get(GOAL_INDEX)));
            assertEquals(expectedUser.getCaloriesRequired(), Integer.parseInt(data.get(REQ_CAL_INDEX)));
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
