package duke.parser;

import duke.commands.*;
import duke.notes.NoteType;
import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * An interface that extends the (@code DukeUI} interface.
 * (@code DukeParser} makes sense of user inputs and creates the appropriate {@code DukeCommand} objects.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public interface DukeParser extends DukeUI, DateParser {

    //METHODS-------------------------------------------
    /**
     * This method is used to create the appropriate {@code DukeCommand} object created based on the user input.
     *
     * @param input The textual input provided by the user in verbatim.
     * @return DukeCommand The appropriate {@code DukeCommand} object created based on the user input.
     * @exception CommandException If there are errors in the command input.
     * @exception ParseException If there are errors reading from the user input.
     */
    static DukeCommand readCommand(String input) throws CommandException, ParseException, PrefixException {

        assert !input.isEmpty() && !input.isBlank() : "User Input cannot be empty or blank.";
        ArrayList<String> inputs = new ArrayList<>();
        try {

            if (input.startsWith("#")) {

                String[] inputTokens = input.split(" ", 2);
                String cmdType = inputTokens[0];
                switch (CmdType.getKey(cmdType).toString()) {
                    case "AUTOSAVE" -> {
                        if (inputTokens.length == 1) {
                            return new AutoSaveToggleCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " +
                                    cmdType + ".");
                        }
                    }
                    case "COMMANDS" -> {
                        if (inputTokens.length == 1) {
                            return new InfoCommand(cmdType, "all");
                        } else {
                            String infoType = inputTokens[1];
                            if(infoType.equals("gen") || infoType.equals("new") || infoType.equals("info") ||
                                    infoType.equals("mgmt")) {
                                return new InfoCommand(cmdType, infoType);
                            }
                            throw new CommandException("There seems to be invalid characters behind " +
                                    cmdType + ".");
                        }
                    }
                    case "LISTBILLS", "LISTBIRTHDAYS", "LISTBUDGETS", "LISTDEADLINES", "LISTEVENTS",
                            "LISTSHOPLISTS", "LISTTASKS", "LISTWEDDINGS", "LISTNOTES" -> {
                        String noteFilter = null;
                        String textFilter = null;
                        Date dateFilter = null;
                        Date addedFilter = null;
                        if (inputTokens.length == 1) {
                            return new ListCommand(cmdType);
                        } else {
                            if(input.contains("/nf")){
                                String[] listTokens = input.split("/nf", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                noteFilter = listTokens[0].trim().toUpperCase();
                                if(!noteFilter.equals("O") && !noteFilter.equals("C")){
                                    throw new CommandException("There seems to be an error with the " +
                                            "Note Filter specified.");
                                }
                            }

                            if(input.contains("/with")) {
                                String[] listTokens = input.split("/with", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                textFilter = (listTokens[0].trim());
                            }

                            if(input.contains("/on")) {
                                if(CmdType.getCommand(cmdType).equals("#shoplist")) {
                                    throw new CommandException("Date filters are incompatible with " +
                                            cmdType + ".");
                                }
                                String[] listTokens = input.split("/on", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                dateFilter = DateParser.understandDateInput(listTokens[0].trim() + " 00:00");
                            }

                            if(input.contains("/added")) {
                                if(CmdType.getCommand(cmdType).equals("#shoplist")) {
                                    throw new CommandException("Date filters are incompatible with " +
                                            cmdType + ".");
                                }
                                String[] listTokens = input.split("/added", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                addedFilter = DateParser.understandDateInput(listTokens[0].trim() + " 00:00");
                            }

                            if(noteFilter == null && textFilter == null && dateFilter == null && addedFilter == null) {
                                throw new CommandException("There seems to be invalid characters behind " +
                                        cmdType + ".");
                            }

                            return new ListCommand(cmdType, noteFilter, textFilter, dateFilter, addedFilter,
                                    CmdType.getTimelineDays(cmdType));
                        }
                    }
                    case "LISTNXT24", "LISTNXT48", "LISTNXT72" -> {
                        String noteFilter = null;
                        String textFilter = null;
                        Date dateFilter = new Date();
                        Date addedFilter = null;
                        if (inputTokens.length == 1) {
                            return new ListCommand(cmdType, dateFilter, CmdType.getTimelineDays(cmdType));
                        } else {
                            if(input.contains("/nf")){
                                String[] listTokens = input.split("/nf", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                noteFilter = listTokens[0].trim().toUpperCase();
                                if(!noteFilter.equals("O") && !noteFilter.equals("C")){
                                    throw new CommandException("There seems to be an error with the " +
                                            "Note Filter specified.");
                                }
                            }

                            if(input.contains("/with")) {
                                String[] listTokens = input.split("/with", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                textFilter = (listTokens[0].trim());
                            }

                            if(input.contains("/on")) {
                                throw new CommandException("Date filters are incompatible with " +
                                        cmdType + ".");
                            }

                            if(input.contains("/added")) {
                                if(CmdType.getCommand(cmdType).equals("#shoplist")) {
                                    throw new CommandException("Date filters are incompatible with " +
                                            cmdType + ".");
                                }
                                String[] listTokens = input.split("/added", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                addedFilter = DateParser.understandDateInput(listTokens[0].trim() + " 00:00");
                            }

                            if(noteFilter == null && textFilter == null && addedFilter == null) {
                                throw new CommandException("There seems to be invalid characters behind " +
                                        cmdType + ".");
                            }

                            return new ListCommand(cmdType, noteFilter, textFilter, dateFilter, addedFilter,
                                    CmdType.getTimelineDays(cmdType));
                        }
                    }
                    case "DELETE" -> {
                        ArrayList<Integer> toDelete = new ArrayList<>();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] deleteTokens = input.split("/n", 2);
                                deleteTokens = deleteTokens[1].trim().split("/", 2);
                                deleteTokens = deleteTokens[0].trim().split("&");
                                for (String deleteToken : deleteTokens) {
                                    toDelete.add(Integer.parseInt(deleteToken.trim()));
                                }
                                return new DeleteCommand(cmdType, toDelete);
                            } else {
                                throw new CommandException("The Note to delete was not specified.");
                            }


                        }
                    }
                    case "EDITEND" -> {
                        int targetNote;
                        String dateToChange = "end";
                        Date newDate;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] editTokens = input.split("/n", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                targetNote = Integer.parseInt(editTokens[0].trim());
                            } else {
                                throw new CommandException("The Note to edit was not specified.");
                            }

                            if (input.contains("/to")) {
                                String[] listTokens = input.split("/to", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                newDate = DateParser.understandDateInput(listTokens[0].trim());
                            } else {
                                throw new CommandException("The new Event end date-time was not specified.");
                            }

                            return new EditDateCommand(cmdType, targetNote, dateToChange, newDate);
                        }
                    }
                    case "EDITSTART" -> {
                        int targetNote;
                        String dateToChange = "start";
                        Date newDate;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] editTokens = input.split("/n", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                targetNote = Integer.parseInt(editTokens[0].trim());
                            } else {
                                throw new CommandException("The Note to edit was not specified.");
                            }

                            if (input.contains("/to")) {
                                String[] listTokens = input.split("/to", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                newDate = DateParser.understandDateInput(listTokens[0].trim());
                            } else {
                                throw new CommandException("The new Event start date-time was not specified.");
                            }

                            return new EditDateCommand(cmdType, targetNote, dateToChange, newDate);
                        }
                    }
                    case "EDITTARGET" -> {
                        int targetNote;
                        String dateToChange = "target";
                        Date newDate;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] editTokens = input.split("/n", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                targetNote = Integer.parseInt(editTokens[0].trim());
                            } else {
                                throw new CommandException("The Note to edit was not specified.");
                            }

                            if (input.contains("/to")) {
                                String[] listTokens = input.split("/to", 2);
                                listTokens = listTokens[1].trim().split("/", 2);
                                newDate = DateParser.understandDateInput(listTokens[0].trim());
                            } else {
                                throw new CommandException("The new Deadline target date-time was not specified.");
                            }

                            return new EditDateCommand(cmdType, targetNote, dateToChange, newDate);
                        }
                    }
                    case "EDITDESC" -> {
                        int targetNote;
                        String newDescription;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] editTokens = input.split("/n", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                targetNote = Integer.parseInt(editTokens[0].trim());
                            } else {
                                throw new CommandException("The Note to edit was not specified.");
                            }

                            if (input.contains("/to")) {
                                String[] editTokens = input.split("/to", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                newDescription = editTokens[0].trim();
                            } else {
                                throw new CommandException("The new description was not specified.");
                            }

                            return new EditDescriptionCommand(cmdType, targetNote, newDescription);
                        }
                    }
                    case "EXTDLINE" -> {
                        int targetNote;
                        long milliSecToExtend = 0;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] editTokens = input.split("/n", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                targetNote = Integer.parseInt(editTokens[0].trim());
                            } else {
                                throw new CommandException("The Deadline to extend was not specified.");
                            }

                            if (input.contains("/d")) {
                                String[] editTokens = input.split("/d", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                milliSecToExtend = milliSecToExtend + (Integer.parseInt(editTokens[0].trim())*86400000);
                            }

                            if (input.contains("/h")) {
                                String[] editTokens = input.split("/h", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                milliSecToExtend = milliSecToExtend + (Integer.parseInt(editTokens[0].trim())*3600000);
                            }

                            if (input.contains("/m")) {
                                String[] editTokens = input.split("/m", 2);
                                editTokens = editTokens[1].trim().split("/", 2);
                                milliSecToExtend = milliSecToExtend + (Integer.parseInt(editTokens[0].trim())*60000);
                            }

                            if(milliSecToExtend == 0) {
                                throw new CommandException("The duration to extend the Deadline was not specified.");
                            }

                            return new ExtendDeadlineCommand(cmdType, targetNote, milliSecToExtend);
                        }
                    }
                    case "EXITDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new ExitCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " +
                                    cmdType + ".");
                        }
                    }
                    case "MARKDONE" -> {
                        ArrayList<Integer> toMarkDone = new ArrayList<>();
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/n")) {
                                String[] doneTokens = input.split("/n", 2);
                                doneTokens = doneTokens[1].trim().split("/", 2);
                                doneTokens = doneTokens[1].trim().split("&");
                                for (String doneToken : doneTokens) {
                                    toMarkDone.add(Integer.parseInt(doneToken.trim()));
                                }
                                return new MarkDoneCommand(cmdType, toMarkDone);
                            } else {
                                throw new CommandException("There seems to be invalid characters behind " +
                                        cmdType + ".");
                            }
                        }
                    }
                    case "SAVEDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new SaveCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " +
                                    cmdType + ".");
                        }

                    }
                    case "TRANSFER" -> {
                        int from;
                        int to;
                        double amount;
                        if (inputTokens.length == 1) {
                            throw new CommandException("There seems to be insufficient attributes behind " +
                                    cmdType + ".");
                        } else {
                            if (input.contains("/from")) {
                                String[] transferTokens = input.split("/from", 2);
                                transferTokens = transferTokens[1].trim().split("/", 2);
                                from = Integer.parseInt(transferTokens[0].trim());
                            } else {
                                throw new CommandException("The Note whose Budget to transfer from was not specified.");
                            }

                            if (input.contains("/to")) {
                                String[] transferTokens = input.split("/to", 2);
                                transferTokens = transferTokens[1].trim().split("/", 2);
                                to = Integer.parseInt(transferTokens[0].trim());
                            } else {
                                throw new CommandException("The Note whose Budget to transfer to was not specified.");
                            }

                            if (input.contains("/for $")) {
                                String[] transferTokens = input.split("/for \\$", 2);
                                transferTokens = transferTokens[1].trim().split("/", 2);
                                amount = Double.parseDouble(transferTokens[0].trim());
                                if(amount <= 0) {
                                    throw new CommandException("The dollar amount specified must be more than zero.");
                                }
                            } else {
                                throw new CommandException("The Budget amount to transfer was not specified.");
                            }

                            return new TransferCommand(cmdType, from, to, amount);
                        }
                    }
                    case "UNDO" -> {
                        if (inputTokens.length == 1) {
                            return new UndoCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " +
                                    cmdType + ".");
                        }
                    }
                    case "WIPEDUKE" -> {
                        if (inputTokens.length == 1) {
                            return new WipeCommand(cmdType);
                        } else {
                            throw new CommandException("There seems to be invalid characters behind " +
                                    cmdType + ".");
                        }
                    }
                    default -> throw new CommandException("It seems to be an invalid Generic Command.");
                }

            } else if (input.startsWith("@")) {

                String[] delimiters;
                String[] inputTokens = input.split(" ", 2);
                String noteType = inputTokens[0];
                inputs.add(noteType);
                switch (NoteType.getKey(noteType).toString()) {
                    case "BILL" -> delimiters = new String[]{"/by", "/for \\$"};
                    case "BIRTHDAY", "WEDDING" -> delimiters = new String[]{"/from", "/to", "/for \\$"};
                    case "DEADLINE" -> delimiters = new String[]{"/by"};
                    case "EVENT" -> delimiters = new String[]{"/from", "/to"};
                    case "SHOPLIST" -> delimiters = new String[]{"/for \\$"};
                    case "TASK" -> delimiters = new String[]{};
                    default -> throw new CommandException("It seems to be an invalid New Note Command.");
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

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandException("There seems to be insufficient Attributes");
        }
    }
}