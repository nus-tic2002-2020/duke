package duke.parser;

import duke.commands.*;
import duke.notes.*;
import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface DukeParser extends DukeUI {

    //METHODS-------------------------------------------
    static DukeCommand readCommand(String input) throws CommandException, ParseException {

        ArrayList<String> inputs = new ArrayList<String>();
        try {

            if (input.startsWith("#")) {

                String[] inputTokens = input.split(" ", 2);
                String cmdType = inputTokens[0];
                String[] delimiters;
                switch (CmdType.getKey(cmdType).toString()) {
                    case "COMMANDS" -> {
                        if (inputTokens.length == 1) {
                            return new InfoCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind" + cmdType + ".");
                        }
                    }
                    case "LISTBILLS", "LISTBIRTHDAYS", "LISTBUDGETS", "LISTDEADLINES", "LISTEVENTS",
                            "LISTSHOPLISTS", "LISTTODOS", "LISTWEDDINGS", "LISTNOTES" -> {
                        String noteFilter;
                        Date dateFilter;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            String[] listTokens = inputTokens[1].split("/on");
                            noteFilter = listTokens[0].trim();
                            if(noteFilter.equals("O") || noteFilter.equals("C") || noteFilter.equals("A")){
                                if(listTokens.length > 1) {
                                    dateFilter = INPUT_TIME.parse(listTokens[1].trim() + " 00:00");
                                    return new ListCommand(cmdType, noteFilter, dateFilter, CmdType.getTimelineDays(cmdType));
                                } else {
                                    return new ListCommand(cmdType, noteFilter);
                                }
                            } else {
                                throw new CommandException("There seems to be an error with the Note Filter specified");
                            }
                        }
                    }
                    case "LISTNXT24", "LISTNXT48", "LISTNXT72" -> {
                        String noteFilter;
                        Date dateFilter = new Date();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            noteFilter = inputTokens[1].trim();
                            if(noteFilter.equals("O") || noteFilter.equals("C") || noteFilter.equals("A")) {
                                return new ListCommand(cmdType, noteFilter, dateFilter, CmdType.getTimelineDays(cmdType));
                            }else {
                                throw new CommandException("There seems to be an error with the Note Filter specified");
                            }
                        }
                    }
                    case "DELETE" -> {
                        ArrayList<Integer> toDelete = new ArrayList<Integer>();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            String[] deleteTokens = inputTokens[1].split("/and");
                            for (String deleteToken : deleteTokens) {
                                toDelete.add(Integer.parseInt(deleteToken.trim()));
                            }
                            return new DeleteCommand(cmdType, toDelete);
                        }
                    }
                    case "EDITDATE" -> {
                        int targetNote;
                        String dateToChange;
                        Date newDate;
                        ArrayList<String> editDateInputs = new ArrayList<String>();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            delimiters = new String[]{" ", "/to"};
                            for (String delimiter : delimiters) {
                                input = inputTokens[1];
                                inputTokens = input.split(delimiter, 2);
                                editDateInputs.add(inputTokens[0].trim());
                            }
                            editDateInputs.add(inputTokens[1].trim());

                            targetNote = Integer.parseInt(editDateInputs.get(0));
                            dateToChange = editDateInputs.get(1);
                            newDate = INPUT_TIME.parse(editDateInputs.get(2));
                            return new EditDateCommand(cmdType, targetNote, dateToChange, newDate);
                        }
                    }
                    case "EDITDESC" -> {
                        int targetNote;
                        String newDescription;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            String[] editTokens = inputTokens[1].split("/to", 2);
                            targetNote = Integer.parseInt(editTokens[0].trim());
                            newDescription = editTokens[1].trim();
                            return new EditDescriptionCommand(cmdType, targetNote, newDescription);
                        }
                    }
                    case "EXTDLINE" -> {
                        int targetNote;
                        long millisecondsToExtend = 0;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            String[] editTokens = inputTokens[1].split("/by",2);
                            targetNote = Integer.parseInt(editTokens[0].trim());
                            for (String editToken : editTokens[1].trim().split(" ")) {
                                int x = Integer.parseInt(editToken.substring(0, editToken.length() - 1));
                                switch (editToken.trim().substring(editToken.length()-1)) {
                                    case "d" -> { millisecondsToExtend = millisecondsToExtend + (x * 86400000); }
                                    case "h" -> { millisecondsToExtend = millisecondsToExtend + (x * 3600000); }
                                    case "m" -> { millisecondsToExtend = millisecondsToExtend + (x * 60000); }
                                    default -> {
                                        throw new CommandException("There seems to be invalid characters behind " + cmdType + ".");
                                    }
                                }
                            }
                            return new ExtendDeadlineCommand(cmdType, targetNote, millisecondsToExtend);
                        }
                    }
                    case "EXITDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new ExitCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " + cmdType + ".");
                        }
                    }
                    case "MARKDONE" -> {
                        ArrayList<Integer> toMarkDone = new ArrayList<Integer>();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            String[] doneTokens = inputTokens[1].split("/and");
                            for (String doneToken : doneTokens) {
                                toMarkDone.add(Integer.parseInt(doneToken.trim()));
                            }
                            return new MarkDoneCommand(cmdType, toMarkDone);
                        }
                    }
                    case "SAVENOTES" -> {
                        if (inputTokens.length == 1) {
                            return new SaveCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " + cmdType + ".");
                        }

                    }
                    case "TRANSFER" -> {
                        int from;
                        int to;
                        double amount;
                        ArrayList<String> transferInputs = new ArrayList<>();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " + cmdType + ".");
                        } else {
                            delimiters = new String[]{"/from", "/to", "/for \\$"};
                            for (String delimiter : delimiters) {
                                input = inputTokens[1];
                                inputTokens = input.split(delimiter, 2);
                                transferInputs.add(inputTokens[0].trim());
                            }
                            transferInputs.add(inputTokens[1].trim());

                            from = Integer.parseInt(transferInputs.get(1));
                            to = Integer.parseInt(transferInputs.get(2));
                            amount = Double.parseDouble(transferInputs.get(3));
                            if (from == to){
                                throw new CommandException("Transferring from and to the same account achieves nothing, at all.");
                            }
                            return new TransferCommand(cmdType, from, to, amount);
                        }
                    }
                    case "UNDOLAST" -> {
                        if (inputTokens.length == 1) {
                            return new UndoLastCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " + cmdType + ".");
                        }
                    }
                    case "WIPEDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new WipeCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " + cmdType + ".");
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
                return new NewNoteCommand(noteType, inputs);

            } else {
                throw new PrefixException();
            }

        } catch (PrefixException e) {
            e.printExplanation(input);

        } catch (ArrayIndexOutOfBoundsException e) {

            throw new ParseException("Insufficient Attributes", 0);
        }
        return null;
    }
}