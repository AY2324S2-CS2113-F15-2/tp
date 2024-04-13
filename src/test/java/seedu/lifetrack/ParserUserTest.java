package seedu.lifetrack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserUser;
import seedu.lifetrack.user.User;

public class ParserUserTest {

    @Test
    public void parseUserSetUp_validInput_success() throws InvalidInputException {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        ParserUser.parseSetUp(input, user);
        Assertions.assertEquals("karthik", user.getName());
        Assertions.assertEquals(175, user.getHeight());
        Assertions.assertEquals(70, user.getWeight());
        Assertions.assertEquals(25, user.getAge());
        Assertions.assertEquals("male", user.getSex());
        Assertions.assertEquals(3, user.getExerciseLevels());
        Assertions.assertEquals(2, user.getGoal());
    }

    @Test
    public void parseUserSetUp_emptyInput_throwsException() {
        String input = "user setup";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_inCorrectOrder_throwsException() {
        String input = "user setup karthik w/175 h/70 a/25 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidNumberOfInputs_throwException() {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/2 h/140";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingName_throwsException() {
        String input = "user setup h/175 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingHeight_throwsException() {
        String input = "user setup karthik h/ w/70 a/25 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingWeight_throwsException() {
        String input = "user setup karthik h/175 w/ a/25 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingAge_throwsException() {
        String input = "user setup karthik h/175 w/70 a/ s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingSex_throwsException() {
        String input = "user setup karthik h/175 w/70 a/25 s/ e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingExerciseLevels_throwsException() {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/ g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_missingGoal_throwsException() {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidHeight_throwsInvalidInputException() {
        String input = "user setup karthik h/80 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidWeight_throwsInvalidInputException() {
        String input = "user setup karthik h/175 w/20 a/25 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidAge_throwsInvalidInputException() {
        String input = "user setup karthik h/175 w/70 a/10 s/male e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidGender_throwsInvalidInputException() {
        String input = "user setup karthik h/175 w/70 a/25 s/non-binary e/3 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidExerciseLevels_throwsInvalidInputException() {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/6 g/2";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserSetUp_invalidGoal_throwsInvalidInputException() {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/7";
        User user = new User();
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseSetUp(input, user));
    }

    @Test
    public void parseUserUpdate_validNameSetUp() throws InvalidInputException {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        ParserUser.parseSetUp(input, user);
        String name = "user update name John";
        String height = "user update height 174";
        String weight = "user update weight 90";
        String age = "user update age 18";
        String sex = "user update sex female";
        String exerciseLevel = "user update exercise levels 2";
        String goal = "user update goal 1";
        ParserUser.parseUpdate(name, user);
        ParserUser.parseUpdate(height, user);
        ParserUser.parseUpdate(weight, user);
        ParserUser.parseUpdate(age, user);
        ParserUser.parseUpdate(sex, user);
        ParserUser.parseUpdate(exerciseLevel, user);
        ParserUser.parseUpdate(goal, user);
        Assertions.assertEquals("John", user.getName());
        Assertions.assertEquals(174, user.getHeight());
        Assertions.assertEquals(90, user.getWeight());
        Assertions.assertEquals(18, user.getAge());
        Assertions.assertEquals("female", user.getSex());
        Assertions.assertEquals(2, user.getExerciseLevels());
        Assertions.assertEquals(1, user.getGoal());
    }

    @Test
    public void parseUserUpdate_emptyCommand_throwsException() throws InvalidInputException {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        ParserUser.parseSetUp(input, user);
        String test = "user update";
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseUpdate(test, user));
    }

    @Test
    public void parseUserUpdate_emptyField_throwsException() throws InvalidInputException {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        ParserUser.parseSetUp(input, user);
        String test = "user update name";
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseUpdate(test, user));
    }

    @Test
    public void parseUserUpdate_unknownInput_throwsException() throws InvalidInputException {
        String input = "user setup karthik h/175 w/70 a/25 s/male e/3 g/2";
        User user = new User();
        ParserUser.parseSetUp(input, user);
        String test = "user update abcd abcd";
        Assertions.assertThrows(InvalidInputException.class, () -> ParserUser.parseUpdate(test, user));
    }

}
