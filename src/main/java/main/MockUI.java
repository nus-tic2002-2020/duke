package main;

/***
 * mock UI class created to override main UI methods during testing
 */
public class MockUI extends UI {
    /***
     * mock printDone method to ove ride main printDone method during testing
     * @param line
     * @param index
     */
    public void printDone(TaskList line, int index) {

    }

    /***
     * mock printTodo method to over ride main printTodo method during testing
     * @param line
     * @param count
     */
    public void printTodo(TaskList line, int count) {

    }

    /***
     * mock print Deadline method to over ride main printDeadline method during testing
     * @param line
     * @param count
     */
    public void printDeadline(TaskList line, int count) {

    }

    /***
     * mock printEvent method to over ride main printEvent method during testing
     * @param line
     * @param count
     */
    public void printEvent(TaskList line, int count) {

    }

    /***
     * mock printDelete method to over ride main print Delete method during testing
     * @param t
     * @param count
     * @param index
     */
    public void printDelete(Task t, int count, int index) {

    }
}