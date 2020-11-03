package duke.storage;

import duke.notes.Note;

import java.util.ArrayList;

/**
 * An object that holds all the {@code Note} objects in {@code Duke} for quick access.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class DukeList {

    private ArrayList<Note> notes;

    /**
     * This method is used to construct a {@code DukeList} object.
     *
     * @param notes The ArrayList of {@code Note} objects to be held.
     */
    public DukeList(ArrayList<Note> notes) {
        this.notes = notes;
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
     * This method is used to access the ArrayList of {@code Note} objects held.
     *
     * @return ArrayList<Note> The ArrayList of {@code Note} objects held.
     */
    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    /**
     * This method is used to replace the ArrayList of {@code Note} held in the {@code DukeList} object.
     *
     * @param newNotes The new ArrayList of {@code Note} objects to be held.
     */
    void replaceNotes(ArrayList<Note> newNotes) {
        this.notes = newNotes;
    }

}
