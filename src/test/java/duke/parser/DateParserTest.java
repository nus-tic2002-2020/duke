package duke.parser;

import duke.commands.CommandException;
import duke.ui.DukeUI;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

public class DateParserTest {


    @Test
    void dateTest() throws ParseException, CommandException {

        String input = "09/08/20 6pm";
        Date results = DateParser.understandDateInput(input);
        System.out.println(DukeUI.INPUT_TIME.format(results));
    }
}
