package edu.augustana;

public class ChangesMadeManager {
    private static boolean changesMade = false;

    public static boolean isThereChanges(){
        return changesMade;
    }
    public static void setChangesMade(boolean value){
        changesMade = value;
    }
}
