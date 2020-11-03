package duke.commands;

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

    private final String CONSTRUCTOR;
    private final String COMMAND;
    private final String SHORT_COMMAND;
    private final String TASK_ICON;
    private final String LOWERCASE_NAME;
    private final String LOWERCASE_NAME_PLURAL;
    private final String VERB;

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
        this.CONSTRUCTOR = constructor;
        this.COMMAND = command;
        this.SHORT_COMMAND = shortCommand;
        this.TASK_ICON = taskIcon;
        this.LOWERCASE_NAME = lowercaseName;
        this.LOWERCASE_NAME_PLURAL = lowercaseNamePlural;
        this.VERB = verb;
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
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
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
    public static String getCONSTRUCTOR(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.CONSTRUCTOR;
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
    public static String getCOMMAND(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.COMMAND;
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
    public static String getSHORT_COMMAND(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.SHORT_COMMAND;
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
    public static String getTASK_ICON(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.TASK_ICON;
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
    public static String getLOWERCASE_NAME(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.LOWERCASE_NAME;
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
    public static String getLOWERCASE_NAME_PLURAL(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.LOWERCASE_NAME_PLURAL;
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
    public static String getVERB(String noteType) throws CommandException {

        for(NoteType type: NoteType.values()){
            if(noteType.equals(type.CONSTRUCTOR) || noteType.equals(type.COMMAND) ||
                    noteType.equals(type.SHORT_COMMAND) || noteType.equals(type.toString())){
                return type.VERB;
            }
        }
        throw new CommandException("There is no " + noteType + " type of notes in Duke, yet.");
    }
}
