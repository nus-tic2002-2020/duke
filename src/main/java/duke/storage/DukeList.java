package duke.storage;

import duke.notes.Task;
import java.util.ArrayList;

/**
 * An object that holds all the {@code Task} objects in {@code Duke} for quick access.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class DukeList {

    private ArrayList<Task> notes;

    /**
     * This method is used to construct a {@code DukeList} object.
     *
     * @param tasks The ArrayList of {@code Task} objects to be held.
     */
    public DukeList(ArrayList<Task> tasks) {
        this.notes = tasks;
    }

    /**
     * This method is used to initialise a {@code DukeList} object.
     *
     */
    @SuppressWarnings("unused")
    public DukeList() {
        notes = new ArrayList<>();
    }

    /**
     * This method is used to access the ArrayList of {@code Task} objects held.
     *
     * @return ArrayList<Task> The ArrayList of {@code Task} objects held.
     */
    public ArrayList<Task> getNotes() {
        return this.notes;
    }

    /**
     * This method is used to replace the ArrayList of {@code Task} held in the {@code DukeList} object.
     *
     * @param newNotes The new ArrayList of {@code Task} objects to be held.
     */
    public void replaceNotes(ArrayList<Task> newNotes) {
        this.notes = newNotes;
    }

}
