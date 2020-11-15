package duke.command;

/**
 * Command type enum.
 * Each enum value may have multiple matching command text.
 */
public enum CommandType {

    ADD(new String[] {
            "todo",
            "deadline",
            "event"
    }),
    LIST(new String[] {"list"}),
    DONE(new String[] {"done"}),
    DELETE(new String[] {"delete"}),
    EXIT(new String[] {"bye"}),
    FIND(new String[] {"find"});

    private String[] commands;

    /**
     * Constructor to associate multiple string values to each CommandType
     * @param commands string values in a string array matching each CommandType
     */
    CommandType(String[] commands) {
        this.commands = commands;
    }

    /**
     * Getter for CommandType matching given command string value
     * @param command string value
     * @return CommandType enum
     */
    public static CommandType valueofCommand(String command) {
        for (CommandType t: values()) {
            for (String c: t.commands) {
                if (c.equals(command)) {
                    return t;
                }
            }
        }
        return null;
    }
}
