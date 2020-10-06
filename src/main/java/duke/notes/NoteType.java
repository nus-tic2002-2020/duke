package duke.notes;

import duke.commands.CommandException;

/**
 * This enum lists all the {@code Note} objects available in {@code Duke}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
@SuppressWarnings("unused")
public enum NoteType {

    BILL("Bill", "@bill", "@bl", "[BL]",
            "bill payment", "bill payments", "due on"),
    BIRTHDAY("Birthday", "@birthday", "@bd","[BD]",
            "birthday", "birthdays", "falling on"),
    DEADLINE("Deadline", "@deadline", "@dl","[DL]",
            "deadline task", "deadlines", "due by"),
    EVENT("Event", "@event", "@ev","[EV]",
            "event", "events", "taking place on"),
    SHOPLIST("Shoplist", "@shoplist", "@sl","[SL]",
            "shopping list item", "shopping list items", ""),
    TASK("Task", "@task", "@tk","[TK]",
            "task", "tasks", ""),
    WEDDING("Wedding", "@wedding", "@wd","[WD]",
            "wedding", "weddings", "happening on"),
    NOTE("Note", "N.A.", "N.A.", "N.A.",
            "note", "notes", "on");

    private final String constructor;
    private final String command;
    private final String shortCommand;
    private final String taskIcon;
    private final String lowercaseName;
    private final String lowercaseNamePlural;
    private final String verb;

    /**
     * This method constructs the various {@code NoteType} enum items.
     *
     * @param constructor The constructor used to create the {@code Note} object.
     * @param command The standard text required to activate the command to create the {@code Note} object.
     * @param shortCommand The short text required to activate the command to create the {@code Note} object.
     * @param taskIcon The icon to be displayed for the {@code Note} object.
     * @param lowercaseName The singular noun in lowercase to be displayed for the {@code Note} object.
     * @param lowercaseNamePlural The plural noun in lowercase to be displayed for the {@code Note} object.
     * @param verb The verb to be used for the {@code Note} object.
     */
    NoteType(String constructor, String command, String shortCommand, String taskIcon,
             String lowercaseName, String lowercaseNamePlural, String verb) {
        this.constructor = constructor;
        this.command = command;
        this.shortCommand = shortCommand;
        this.taskIcon = taskIcon;
        this.lowercaseName = lowercaseName;
        this.lowercaseNamePlural = lowercaseNamePlural;
        this.verb = verb;
    }

    /**
     * This method returns the associated {@code NoteType} for the
     * constructor or command text provided.
     *
     * @param noteType The constructor or command text entered by the user.
     * @return NoteType The associated NoteType.
     * @exception CommandException If no matching command could be found.
     */
    public static NoteType getKey(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the associated constructor for the
     * constructor or command text provided.
     *
     * @param noteType The constructor or command text entered by the user.
     * @return String The constructor associated with the command.
     * @exception CommandException If no matching command could be found.
     */
    public static String getConstructor(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.constructor;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the corresponding standard command text for the
     * constructor or command text provided.
     *
     * @param noteType The generic command or short command text entered by the user.
     * @return String The corresponding standard command text.
     * @exception CommandException If no matching standard command text could be found.
     */
    public static String getCommand(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.command;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the corresponding short command text for the
     * constructor or command text provided.
     *
     * @param noteType The generic command or standard command text entered by the user.
     * @return String The corresponding short command text.
     * @exception CommandException If no matching short command text could be found.
     */
    public static String getShortCommand(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.shortCommand;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the corresponding task icon for the
     * constructor or command text provided.
     *
     * @param noteType The generic command or standard command text entered by the user.
     * @return String The corresponding short command text.
     * @exception CommandException If no matching short command text could be found.
     */
    public static String getTaskIcon(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.taskIcon;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the corresponding singular noun in lowercase for the
     * constructor or command text provided.
     *
     * @param noteType The generic command or standard command text entered by the user.
     * @return String The corresponding short command text.
     * @exception CommandException If no matching short command text could be found.
     */
    public static String getLowercaseName(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.lowercaseName;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the corresponding plural noun in lowercase for the
     * constructor or command text provided.
     *
     * @param noteType The generic command or standard command text entered by the user.
     * @return String The corresponding short command text.
     * @exception CommandException If no matching short command text could be found.
     */
    public static String getLowercaseNamePlural(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.lowercaseNamePlural;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }

    /**
     * This method returns the corresponding verb to be used for the
     * constructor or command text provided.
     *
     * @param noteType The generic command or standard command text entered by the user.
     * @return String The corresponding short command text.
     * @exception CommandException If no matching short command text could be found.
     */
    public static String getVerb(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.constructor) || noteType.equals(type.command) ||
                    noteType.equals(type.shortCommand) || noteType.equals(type.toString())){
                return type.verb;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }
}
