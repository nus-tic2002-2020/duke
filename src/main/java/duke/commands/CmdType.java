package duke.commands;

import duke.notes.NoteType;

/**
 * This enum lists all the generic commands available in {@code Duke}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public enum CmdType {

    COMMANDS("#commands", "#cmd", null, 0),
    DELETE("#delete", "#del", null, 0),
    EDITDATE("#editdate", "#edt", null, 0),
    EDITDESC("#editdesc", "#eds", null, 0),
    EXITDUKE("#exitduke", "#xit", null, 0),
    EXTDLINE("#extend", "#xtd", null, 0),
    LISTBILLS("#listbills", "#lbp", NoteType.BILL, 1),
    LISTBUDGETS("#listbudgets", "#lbg", NoteType.TASK, 1),
    LISTBIRTHDAYS("#listbirthdays", "#lbd", NoteType.BIRTHDAY, 1),
    LISTDEADLINES("#listdeadlines", "#ldl", NoteType.DEADLINE, 1),
    LISTEVENTS("#listevents", "#lev", NoteType.EVENT, 1),
    LISTSHOPLISTS("#listshoplists", "#lsl", NoteType.SHOPLIST, 0),
    LISTTODOS("#listtodos", "#ltd", NoteType.TODO, 1),
    LISTWEDDINGS("#listweddings", "#lwd", NoteType.WEDDING, 1),
    LISTNOTES("#listnotes", "#lnt", NoteType.TASK, 0),
    LISTNXT24("#listnxt24", "#n24", NoteType.TASK, 1),
    LISTNXT48("#listnxt48", "#n48", NoteType.TASK, 2),
    LISTNXT72("#listnxt72", "#n72", NoteType.TASK, 3),
    MARKDONE("#markdone", "#mkd", null, 0),
    SAVENOTES("#savenotes", "#sav", null, 0),
    TRANSFER("#transfer", "#txf", null, 0),
    UNDOLAST("#undo", "#und", null, 0),
    WIPEDUKE("#wipeduke", "#wpe", null, 0);

    private final String command;
    private final String shortCommand;
    private final NoteType relevantNoteType;
    private final int timelineDays;

    /**
     * This method constructs the various {@code CmdType} enum items.
     *
     * @param command The standard text required to activate the command.
     * @param shortCommand The short text required to activate the command.
     * @param relevantNoteType The {@code NoteType} associated with the command.
     * @param timelineDays The timeline associated with the {@code ListCommand} objects.
     */
    CmdType(String command, String shortCommand, NoteType relevantNoteType, int timelineDays) {
        this.command = command;
        this.shortCommand = shortCommand;
        this.relevantNoteType = relevantNoteType;
        this.timelineDays = timelineDays;
    }

    /**
     * This method returns the associated {@code CmdType}
     * for the generic command or short command text provided.
     *
     * @param cmdType The command text entered by the user.
     * @return CmdType The generic command to execute.
     * @exception CommandException If no matching command could be found.
     */
    public static CmdType getKey(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type;
            }
        }
        throw new CommandException("There is no " + cmdType + " type of command in Duke, yet.");
    }

    /**
     * This method returns the corresponding standard command text
     * for the generic command or short command text provided.
     *
     * @param cmdType The generic command or short command text entered by the user.
     * @return String The corresponding standard command text.
     * @exception CommandException If no matching standard command text could be found.
     */
    public static String getCommand(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type.command;
            }
        }
        throw new CommandException("There is no " + cmdType + " type of command in Duke, yet.");
    }

    /**
     * This method returns the corresponding short command text
     * for the generic command or short command text provided.
     *
     * @param cmdType The generic command or standard command text entered by the user.
     * @return String The corresponding short command text.
     * @exception CommandException If no matching short command text could be found.
     */
    public static String getShortCommand(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type.shortCommand;
            }
        }
        throw new CommandException("There is no " + cmdType + " type of command in Duke, yet.");
    }

    /**
     * This method returns the relevant {@code NoteType}
     * for the generic command or short command text provided.
     *
     * @param cmdType The generic command or standard command text entered by the user.
     * @return NoteType The corresponding relevant NoteType.
     * @exception CommandException If no matching NoteType could be found.
     */
    public static NoteType getNoteType(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type.relevantNoteType;
            }
        }
        throw new CommandException("There is no " + cmdType + " type of command in Duke, yet.");
    }

    /**
     * This method returns the relevant timeline element applicable to {@code ListCommand} objects
     * for the generic command or short command text provided.
     *
     * @param cmdType The generic command or standard command text entered by the user.
     * @return int The corresponding relevant timeline element applicable, returns {@code 0} if not applicable.
     * @exception CommandException If no matching timeline element could be found.
     */
    public static int getTimelineDays(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type.timelineDays;
            }
        }
        throw new CommandException("There is no " + cmdType + " type of command in Duke, yet.");
    }
}
