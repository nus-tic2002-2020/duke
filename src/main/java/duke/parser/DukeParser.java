package duke.parser;

import duke.commands.*;
import duke.notes.*;
import java.util.ArrayList;

public interface DukeParser {

    //METHODS-------------------------------------------
    static DukeCommand readCommand(String input) throws CommandException {

        ArrayList<String> inputs = new ArrayList<String>();
        try {

            if (input.startsWith("#")) {

                String[] inputTokens = input.split(" ", 2);
                String cmdType = inputTokens[0];
                String[] delimiters = new String[]{};
                inputs.add(cmdType);
                switch (CmdType.getKey(cmdType).toString()) {
                    case "COMMANDS", "LISTNOTES" -> {
                        if (inputTokens.length == 1) {
                            return new InfoCommand(inputs);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind" + cmdType + ".");
                        }
                    }
                    case "DELETE" -> {
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind" + cmdType + ".");
                        } else {
                            String[] deleteTokens = inputTokens[1].split("/and");
                            for (String deleteToken : deleteTokens) {
                                inputs.add(deleteToken.trim());
                            }
                            return new DeleteCommand(inputs);
                        }
                    }
                    case "EDITDESC" -> {
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind" + cmdType + ".");
                        } else {
                            String[] editTokens = inputTokens[1].split("/to");
                            for (String editToken : editTokens) {
                                inputs.add(editToken.trim());
                            }
                            return new EditDescriptionCommand(inputs);
                        }
                    }
                    case "EXTDLINE" -> {
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind" + cmdType + ".");
                        } else {
                            String[] editTokens = inputTokens[1].split("/by",2);
                            inputs.add(editTokens[0].trim());
                            inputs.add(2,"0");
                            inputs.add(3,"0");
                            inputs.add(4,"0");
                            for (String editToken : editTokens[1].trim().split(" ")) {
                                switch (editToken.trim().substring(editToken.length()-1)) {
                                    case "d" -> inputs.set(2, editToken.substring(0, editToken.length() - 1));
                                    case "h" -> inputs.set(3, editToken.substring(0, editToken.length() - 1));
                                    case "m" -> inputs.set(4, editToken.substring(0, editToken.length() - 1));
                                    default -> throw new NumberFormatException();
                                }
                            }
                            return new ExtendDeadlineCommand(inputs);
                        }
                    }
                    case "EXITDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new ExitCommand(inputs);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind" + cmdType + ".");
                        }
                    }
                    case "MARKDONE" -> {
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind" + cmdType + ".");
                        } else {
                            String[] doneTokens = inputTokens[1].split("/and");
                            for (String doneToken : doneTokens) {
                                inputs.add(doneToken.trim());
                            }
                            return new MarkDoneCommand(inputs);
                        }
                    }
                    case "SAVENOTES" -> {
                        if (inputTokens.length == 1) {
                            return new SaveCommand(inputs);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind" + cmdType + ".");
                        }

                    }
                    case "TRANSFER" -> {
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind" + cmdType + ".");
                        } else {
                            delimiters = new String[]{"/from", "/to", "/for \\$"};
                            for (String delimiter : delimiters) {
                                input = inputTokens[1];
                                inputTokens = input.split(delimiter, 2);
                                inputs.add(inputTokens[0].trim());
                            }
                            inputs.add(inputTokens[1].trim());
                            inputs.remove(1);
                            return new TransferCommand(inputs);
                        }
                    }
                    case "UNDOLAST" -> {
                        if (inputTokens.length == 1) {
                            return new UndoLastCommand(inputs);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind" + cmdType + ".");
                        }
                    }
                    case "WIPEDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new WipeCommand(inputs);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind" + cmdType + ".");
                        }
                    }
                    default -> {
                        throw new CommandException("It seems to be an invalid Generic Command.");
                    }
                }

            } else if (input.startsWith("@")) {

                String[] delimiters = new String[]{};
                String[] inputTokens = input.split(" ", 2);
                String noteType = inputTokens[0];
                inputs.add(noteType);
                switch (NoteType.getKey(noteType).toString()) {
                    case "BILL" -> {
                        delimiters = new String[]{"/by", "/for \\$"};
                    }
                    case "BIRTHDAY", "WEDDING" -> {
                        delimiters = new String[]{"/from", "/to", "/for \\$"};
                    }
                    case "DEADLINE" -> {
                        delimiters = new String[]{"/by"};
                    }
                    case "EVENT" -> {
                        delimiters = new String[]{"/from", "/to"};
                    }
                    case "SHOPLIST" -> {
                        delimiters = new String[]{"/for \\$"};
                    }
                    case "TODO" -> {
                        delimiters = new String[]{};
                    }
                    default -> {
                        throw new CommandException("It seems to be an invalid New Note Command.");
                    }
                }
                for (String delimiter : delimiters) {
                    input = inputTokens[1];
                    inputTokens = input.split(delimiter, 2);
                    inputs.add(inputTokens[0].trim());
                }
                inputs.add(inputTokens[1].trim());
                return new NewNoteCommand(inputs);

            } else {
                throw new PrefixException();
            }

        } catch (PrefixException e) {
            e.printExplanation(input);

        }
        return null;
    }
}