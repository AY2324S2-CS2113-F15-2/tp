//@@author owx0130
package seedu.lifetrack.system.exceptions;

public class FileHandlerException extends Exception {
    
    /**
     * Constructs a new FileHandlerException with a default error message.
     * The default message provides guidance on ensuring correct input format.
     */
    public FileHandlerException(){
        super("\t There was an error reading/writing to the file!");
    }

    /**
     * Constructs a new FileHandlerException with a custom error message.
     *
     * @param exception the custom error message describing the specific input error
     */
    public FileHandlerException(String exception) {
        super(exception);
    }
}
