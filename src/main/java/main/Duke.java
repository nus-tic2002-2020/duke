package main;

/***
 * Duke application allows users to create a task list and add tasks based on 3 categories:
 * todo, deadline and event
 * Users are also able make their event and deadline tasks recurring by adding more inputs to the back of the task input
 * @param args
 */

public class Duke {
    //private static final String OUTPUT_DELIMITER = "\\|";
    //private static final String INPUT_DELIMITER = " ";
    //ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        DukeApp application = new DukeApp();
        application.run();
    }
}