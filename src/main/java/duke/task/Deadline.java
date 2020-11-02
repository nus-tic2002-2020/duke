package duke.task;

import duke.command.DukeException;

public class Deadline extends Task {

    protected String by;

    public Deadline() {
        this("", "");
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSavableString() {
        return String.format("%s|%s", super.toSavableString(), by);
    }

    @Override
    public void fromSavableString(String savableString) throws DukeException {
        super.fromSavableString(savableString);

        String[] arrString = savableString.split(separator);
        if (arrString.length < 4) {
            throw new DukeException("Rabak Sial, wrong data format for Deadline!", DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        this.by = arrString[3].trim();
    }
}