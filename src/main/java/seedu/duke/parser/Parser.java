package seedu.duke.parser;

import seedu.duke.commands.AddTodoCommand;
import seedu.duke.commands.AddEventCommand;
import seedu.duke.commands.AddDeadlineCommand;
import seedu.duke.commands.DoneCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ScheduleCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.exception.DukeException;

public class Parser {
    /**
     * Parses the user input and returns the command accordingly.
     * @param    userInput       The user input.
     * @return   Command         The command based on the user input.
     * @throws   DukeException   To handle error and exception, if the user input is invalid.
     */
    public static Command parserCommand(String userInput) throws DukeException {
        switch (userInput.split(" ")[0]) {
        case AddTodoCommand.COMMAND_WORD:
            return new AddTodoCommand(false, userInput);
        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommand(false, userInput);
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommand(false, userInput);
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(false, userInput);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(false, ""); //just print
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(false, userInput);
        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommand(false, userInput);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(false, userInput);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand(true, userInput);
        default:
            throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means.");
        }
    }
}