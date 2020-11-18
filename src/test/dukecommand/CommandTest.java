package dukecommand;

import dukeexception.DukeException;
import dukefile.Storage;
import dukelist.TaskList;
import dukeui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean exit;

    CommandTest() throws DukeException {
        ui = new Ui();
        storage = new Storage("data/test.txt");
        tasks = new TaskList();
    }

    @Test
    void isExit() {
        String[] command = {"todo", "testTodo"};
        Command test = new Command(command);
        assertEquals(false,test.isExit());
    }

    @Test
    void execute() {
        String[] command = {"todo", "testTodo"};
        Command test = new Command(command);
        test.execute(tasks, ui, storage);
        assertEquals("[T][\u2718] testTodo",tasks.getTask(0).toString());
    }

    @Test
    void checkCommand() {
        String[] command = {"tobe", "testTodo"};
        Command test = new Command(command);
        try {
            test.check(tasks);
        }catch (DukeException e){
            assertEquals("   Invalid command, issue 'help' for valid commands.", e.getMessage());
        }
    }
}