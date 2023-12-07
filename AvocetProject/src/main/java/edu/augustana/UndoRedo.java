package edu.augustana;

public interface UndoRedo {
    public abstract UndoRedo getClone();

    public abstract void setState();
}
