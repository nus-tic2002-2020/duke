package duke.command;

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

    CommandType(String[] commands) {
        this.commands = commands;
    }

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