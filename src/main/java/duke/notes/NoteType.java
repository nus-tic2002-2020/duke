package duke.notes;

import duke.commands.*;

public enum NoteType {

    BILL("Bill", "@bill", "@bl", "[BL]", "bill payment", "bill payments"),
    BIRTHDAY("Birthday", "@birthday", "@bd","[BD]", "birthday", "birthdays"),
    DEADLINE("Deadline", "@deadline", "@dl","[DL]", "deadline task", "deadines"),
    EVENT("Event", "@event", "@ev","[EV]", "event", "events"),
    SHOPLIST("Shoplist", "@shoplist", "@sl","[SL]", "shopping list item", "shopping list items"),
    TODO("Todo", "@todo", "@td","[TD]", "todo task", "todo tasks"),
    WEDDING("Wedding", "@wedding", "@wd","[WD]", "wedding", "weddings");

    private final String constructor;
    private final String command;
    private final String shortCommand;
    private final String taskIcon;
    private final String lowercaseName;
    private final String lowercaseNamePlural;

    NoteType(String constructor, String command, String shortCommand, String taskIcon,
             String lowercaseName, String lowercaseNamePlural) {
        this.constructor = constructor;
        this.command = command;
        this.shortCommand = shortCommand;
        this.taskIcon = taskIcon;
        this.lowercaseName = lowercaseName;
        this.lowercaseNamePlural = lowercaseNamePlural;
    }

    public static NoteType getKey(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    public static String getConstructor(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.constructor;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    public static String getCommand(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.command;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    public static String getShortCommand(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.shortCommand;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    public static String getTaskIcon(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.taskIcon;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    public static String getLowercaseName(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.lowercaseName;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    public static String getLowercaseNamePlural(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.lowercaseNamePlural;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }
}
