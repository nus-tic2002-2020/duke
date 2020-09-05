package duke.notes;

import duke.commands.*;

public enum NoteType {

    BILL("Bill", "@bill", "@bl", "[BL]", "bill payment"),
    BIRTHDAY("Birthday", "@birthday", "@bd","[BD]", "birthday"),
    DEADLINE("Deadline", "@deadline", "@dl","[DL]", "deadline task"),
    EVENT("Event", "@event", "@ev","[EV]", "event"),
    SHOPLIST("Shoplist", "@shoplist", "@sl","[SL]", "shopping list item"),
    TODO("Todo", "@todo", "@td","[TD]", "todo task"),
    WEDDING("Wedding", "@wedding", "@wd","[WD]", "wedding");

    private final String constructor;
    private final String command;
    private final String shortCommand;
    private final String taskIcon;
    private final String lowercaseName;

    NoteType(String constructor, String command, String shortCommand, String taskIcon, String lowercaseName) {
        this.constructor = constructor;
        this.command = command;
        this.shortCommand = shortCommand;
        this.taskIcon = taskIcon;
        this.lowercaseName = lowercaseName;
    }

    public static NoteType getKey(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) || noteType.equals(type.shortCommand)){
                return type;
            }
        }
        throw new CommandException("There is no " + noteType + "type of notes in Duke, yet.");
    }

    public static String getConstructor(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) || noteType.equals(type.shortCommand)){
                return type.constructor;
            }
        }
        throw new CommandException("There is no " + noteType + "type of notes in Duke, yet.");
    }

    public static String getCommand(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) || noteType.equals(type.shortCommand)){
                return type.command;
            }
        }
        throw new CommandException("There is no " + noteType + "type of notes in Duke, yet.");
    }

    public static String getShortCommand(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) || noteType.equals(type.shortCommand)){
                return type.shortCommand;
            }
        }
        throw new CommandException("There is no " + noteType + "type of notes in Duke, yet.");
    }

    public static String getTaskIcon(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) || noteType.equals(type.shortCommand)){
                return type.taskIcon;
            }
        }
        throw new CommandException("There is no " + noteType + "type of notes in Duke, yet.");
    }

    public static String getLowercaseName(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) || noteType.equals(type.shortCommand)){
                return type.lowercaseName;
            }
        }
        throw new CommandException("There is no " + noteType + "type of notes in Duke, yet.");
    }
}
