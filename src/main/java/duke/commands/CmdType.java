package duke.commands;

public enum CmdType {


    COMMANDS("#commands", "#cmd"),
    DELETE("#delete", "#del"),
    EXITDUKE("#exitduke", "#ext"),
    LISTNOTES("#listnotes", "#lst"),
    MARKDONE("#markdone", "#mkd"),
    SAVENOTES("#savenotes", "#sav"),
    TRANSFER("#transfer", "#txf"),
    WIPEDUKE("#wipeduke", "#wpe");

    private final String command;
    private final String shortCommand;

    CmdType(String command, String shortCommand) {
        this.command = command;
        this.shortCommand = shortCommand;
    }

    public static CmdType getKey(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type;
            }
        }
        throw new CommandException("There is no " + cmdType + "type of command in Duke, yet.");
    }

    public static String getCommand(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type.command;
            }
        }
        throw new CommandException("There is no " + cmdType + "type of command in Duke, yet.");
    }

    public static String getShortCommand(String cmdType) throws CommandException {

        for(CmdType type: CmdType.values()){
            if(cmdType.equals(type.command) || cmdType.equals(type.shortCommand) || cmdType.equals(type.toString())){
                return type.shortCommand;
            }
        }
        throw new CommandException("There is no " + cmdType + "type of command in Duke, yet.");
    }
}
