package edu.augustana;

/**
 * Manages and keeps track of changes made in the application.
 */
public class ChangesMadeManager {

    /** A flag indicating whether changes have been made. */
    private static boolean changesMade = false;

    /**
     * Checks whether changes have been made.
     *
     * @return True if changes have been made, false otherwise.
     */
    public static boolean isThereChanges(){
        return changesMade;
    }

    /**
     * Sets the flag indicating whether changes have been made.
     *
     * @param value The value to set the changesMade flag to.
     */
    public static void setChangesMade(boolean value){
        changesMade = value;
    }
}
