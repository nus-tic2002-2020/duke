package ui;

import task.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 /**
 * This class tests the Ui class.
 *
 * @author Aloysius Wong
 * @Version 1.0
 * @since 08-11-2020
 */
public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Ui ui = new Ui();

    /**
     * This method tests printTodo in Ui class.
     */
    @Test
    public void printTodoTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Task test = new ToDo("TEST CASE");
        ui.printTodo(test);
        assertEquals("[T][✗] TEST CASE (Priority: NA)\r\n", outputStreamCaptor.toString());
    }

    /**
     * This method tests printDeadline in Ui class.
     */
    @Test
    public void printTDeadlineTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Task test = new Deadline("TEST CASE", "01/02/2020 03:04");
        ui.printDeadline(test);
        assertEquals("[D][✗] TEST CASE (by: 1 FEBRUARY 2020, 03:04) (Priority: NA)\r\n",
                outputStreamCaptor.toString());
    }

    /**
     * This method tests printEvent in Ui class.
     */
    @Test
    public void printTEventTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Task test = new Event("TEST CASE", "01/02/2020 03:04");
        ui.printEvent(test);
        assertEquals("[E][✗] TEST CASE (at: 1 FEBRUARY 2020, 03:04) (Priority: NA)\r\n",
                outputStreamCaptor.toString());
    }

    /**
     * This method tests printWithin in Ui class.
     */
    @Test
    public void printWithinTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Task test = new Within("TEST CASE", "01/02/2020 03:04", "05/06/2020 07:08");
        ui.printWithin(test);
        assertEquals("[W][✗] TEST CASE (within: 1 FEBRUARY 2020, 03:04 to 5 JUNE 2020, 07:08) (Priority: NA)"
                        + "\r\n",
                outputStreamCaptor.toString());
    }
}
