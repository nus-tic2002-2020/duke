package duke.io;

import duke.command.DukeException;

public interface Savable {
    void setSeparator(String separator);
    String getSeparator();
    String toString();
    String toSavableString();
    void fromSavableString(String savableString) throws DukeException;
}
