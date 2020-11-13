package parser;

import commands.AddCommand;
import commands.ListCommand;
import commands.DeleteCommand;

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
    void testParseList() throws DukeException {


        assertEquals("class commands.ListCommand",parser.parse("list 123").getClass().toString());

        assertEquals("task" , ((AddCommand) parser.parse("list123")).getTaskType());
        assertEquals("list123" , ((AddCommand) parser.parse("list123")).getTaskDescription());


        assertEquals( "task",((AddCommand) parser.parse("List")).getTaskType());
        assertEquals( "List",((AddCommand) parser.parse("List")).getTaskDescription());
    }

    @Test
    void testParseBlah(){

        try{
            parser.parse("blah");
        }catch(DukeException e){
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getErrorMessage());
        }

        try {
            assertEquals( "task", ((AddCommand) parser.parse("blah 123")).getTaskType());
            assertEquals("blah 123",((AddCommand) parser.parse("blah 123")).getTaskDescription() );
        } catch (DukeException e) {
            e.getErrorMessage();
        }

    }

    @Test
    void testParseEmptyString(){
        try{
            parser.parse("");
        }catch(DukeException e){
            assertEquals("☹ OOPS!!! You did not enter any command", e.getErrorMessage());
        }

        try{
            parser.parse("    ");
        }catch(DukeException e){
            assertEquals("☹ OOPS!!! You did not enter any command", e.getErrorMessage());
        }
    }


    @Test
    void testParseDelete() throws DukeException {
        assertEquals("class commands.DeleteCommand", parser.parse("delete 2").getClass().toString());

        assertEquals("class commands.AddCommand", parser.parse("delete2").getClass().toString() );

        try{
            parser.parse("delete -1");
        }catch(DukeException e){
            assertEquals("Input option for Delete invalid.", e.getErrorMessage());
        }

    }

    @Test
    void testParseAddTask() throws DukeException {
        assertEquals("task",((AddCommand) parser.parse("jumping")).getTaskType());
        assertEquals("jumping",((AddCommand) parser.parse("jumping")).getTaskDescription());
    }


    @Test
    void testParseAddTodo() throws DukeException {
        AddCommand dummyAdd = (AddCommand) parser.parse("todo running");
        assertEquals("todo",dummyAdd.getTaskType());
        assertEquals("running",dummyAdd.getTaskDescription());
    }

    @Test
    void testParseAddDeadline() throws DukeException {
        AddCommand dummyAdd = (AddCommand) parser.parse("deadline jogging /by 01 Jan 2020 23:59:59");
        assertEquals("deadline",dummyAdd.getTaskType());
        assertEquals("jogging",dummyAdd.getTaskDescription());
        assertEquals("01 Jan 2020 23:59:59",dummyAdd.getTaskSecondPart());
    }

    @Test
    void testParseAddEvent() throws DukeException {
        AddCommand dummyAdd = (AddCommand) parser.parse("event dinner /at restaurant");
        assertEquals("event",dummyAdd.getTaskType());
        assertEquals("dinner",dummyAdd.getTaskDescription());
        assertEquals("restaurant",dummyAdd.getTaskSecondPart());
    }

}