package duke.task;

import duke.command.DukeException;

public class Event extends Task {

    protected String at;

    public Event() {
        this("", "");
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSavableString() {
        return String.format("%s|%s", super.toSavableString(), at);
    }

    @Override
    public void fromSavableString(String savableString) throws DukeException {
        super.fromSavableString(savableString);

        String[] arrString = savableString.split(separator);
        if (arrString.length < 4) {
            throw new DukeException("Rabak Sial, wrong data format for Event!", DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        this.at = arrString[3].trim();
    }
}