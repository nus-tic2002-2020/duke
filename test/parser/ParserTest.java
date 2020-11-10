package parser;

import commands.AddCommand;
import commands.ListCommand;
import tasks.*;
import ui.Ui;
import storage.Storage;


import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser = new Parser();
    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();


    @Test
    void parseList_success() throws DukeException {
        assertEquals(new ListCommand().getClass(),parser.parse("list 123").getClass());
        assertEquals( new AddCommand("task","list123","").getClass(), parser.parse("list123").getClass());
        assertEquals( new AddCommand("task","List","").getClass(),parser.parse("List").getClass());
    }

    @Test
    void parseAdd_success(){
        assertEquals();
    }

    @Test
    void parse_success(){
        assertEquals();
    }

}