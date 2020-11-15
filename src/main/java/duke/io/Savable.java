package duke.io;

import duke.command.DukeException;

/**
 * Object savable to disk by {@link Storage} must implement these interface.
 */
public interface Savable {

    /**
     * Implement setter for separator.
     * Default is "|" character.
     * @param separator character(s) for splitting disk data into fields
     */
    void setSeparator(String separator);

    /**
     * Implement getter for separator.
     * @return separator character(s) for splitting disk data into object fields
     */
    String getSeparator();

    /**
     * Implement method to covert raw disk data to user friendly string.
     * @return printable string for data
     */
    String toString();

    /**
     * Implement method to convert object attributes to raw separated fields
     * for saving to disk.
     * @return raw data format for disk storage
     */
    String toSavableString();

    /**
     * Implement parser to covert raw disk data to object attribute fields.
     * @param savableString as raw data format
     * @throws DukeException throw any Duke related error
     */
    void fromSavableString(String savableString) throws DukeException;
}
