package edu.augustana;
import java.util.LinkedList;
/**
 * This class is responsible for undo or redo an action
 * in any class that implements the UndoRedoAble interface.
 *
 * @author Dale Skrien (ported to JavaFX by Forrest Stonedahl)
 * @version 1.0 August 2005 (updated to JavaFX Oct. 2018)
 * @version 2.0 December 2022 updated by team Curlew to make it more extensible
 */

//public class UndoRedoHandler {
//
//    private LinkedList<UndoRedo> undoStack;
//    private LinkedList<UndoRedo> redoStack;
//    private UndoRedo data;
//
//   /**
//     * Constructs a UndoRedoHandler
//    */
//    public UndoRedoHandler(UndoRedo LessonPlan) {
//        undoStack = new LinkedList<>();
//        redoStack = new LinkedList<>();
//        this.data = LessonPlan;
//        undoStack.push(LessonPlan.getClone());
//    }
//
//    /**
//     * Saves a clone of the data in the undoStack
//*/
//    public void saveState() {
//        undoStack.push(data.getClone());
//        redoStack.clear();
//    }
//
//    /**
//     * Pushes the current state in the redo stack and Undo the last change in the data
//*/
//    public void undo() {
//        if(undoStack.size()<=1) {
//            return;
//        }else {
//            redoStack.push(undoStack.pop().getClone());
//            //data.setState(undoStack.peek().getClone());
//        }
//
//    }
//
//    /**
//     * To go back to the state that was there before user clicks the undo
//*/
//    public void redo() {
//        if(redoStack.isEmpty()) {
//            return;
//        }else {
//            UndoRedo state =redoStack.pop();
//            undoStack.push(state.getClone());
//            data.setState(state.getClone());
//        }
//
//    }
//
//    /**
//     * Removes the top state in undo stack
//*/
//    public void removeState() {
//        undoStack.pop();
//
//    }
//
//}
