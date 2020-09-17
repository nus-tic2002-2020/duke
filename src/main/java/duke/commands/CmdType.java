package duke.commands;

/**
 * This enum lists all the generic commands available in {@code Duke}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public enum CmdType {


    COMMANDS("#commands", "#cmd"),
    DELETE("#delete", "#del"),
    EDITDATE("#editdate", "#edt"),
    EDITDESC("#editdesc", "#eds"),
    EXITDUKE("#exitduke", "#xit"),
    EXTDLINE("#extend", "#xtd"),
    LISTBILLS("#listbills", "#lbp"),
    LISTBUDGETS("#listbudgets", "#lbg"),
    LISTBIRTHDAYS("#listbirthdays", "#lbd"),
    LISTDEADLINES("#listdeadlines", "#ldl"),
    LISTEVENTS("#listevents", "#lev"),
    LISTSHOPLISTS("#listshoplists", "#lsl"),
    LISTTODOS("#listtodos", "#ltd"),
    LISTWEDDINGS("#listweddings", "#lwd"),
    LISTNOTES("#listnotes", "#lnt"),
    LISTNXT24("#listnxt24", "#n24"),
    LISTNXT48("#listnxt48", "#n48"),
    LISTNXT72("#listnxt72", "#n72"),
    MARKDONE("#markdone", "#mkd"),
    SAVENOTES("#savenotes", "#sav"),
    TRANSFER("#transfer", "#txf"),
    UNDOLAST("#undo", "#und"),
    WIPEDUKE("#wipeduke", "#wpe");

    private final String command;
    private final String shortCommand;

    CmdType(String command, String shortCommand) {
        this.command = command;
        this.shortCommand = shortCommand;
    }

    /**
     * This method assesses the command text used and returns the corresponding generic command to execute.
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
     * This method returns the corresponding standard command text for the generic command or short command text provided.
     *
     * @param cmdType The generic command or short command text entered by the user.
     * @return CmdType The corresponding standard command text.
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
     * This method returns the corresponding short command text for the generic command or standard command text provided.
     *
     * @param cmdType The generic command or standard command text entered by the user.
     * @return CmdType The corresponding short command text.
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
}
