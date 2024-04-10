package seedu.lifetrack.system.exceptions;


public class InvalidInputException extends Exception {

    public final String heythere = "";

    /**
     * Constructs a new InvalidInputException with a default error message.
     * The default message provides guidance on ensuring correct input format.
     */
    public InvalidInputException(){
        super("\t Please ensure that you have keyed in the correct format!");
    }

    /**
     * Constructs a new InvalidInputException with a custom error message.
     *
     * @param exception the custom error message describing the specific input error
     */
    public InvalidInputException(String exception) {
        super(exception);
    }
}
