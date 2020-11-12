package main;

public class DukeApp {

    //start the interaction
    UI ui;
    testDuke testDuke;
    Storage storage = new Storage();

    public DukeApp() {
        ui = new UI();
    }

    /***
     * run the application
     * run the application by calling the parser, UI and storage classes
     */
    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        String input = "";
        TaskList task = new TaskList();
        storage.load("listData.txt", task);
        //System.out.println("debug");
        //Junit testing
        //testDuke.addOneTaskTest();
        //if string doesn't equal to bye
        while (!input.equals("bye")) {
            input = ui.getUserInput();
            System.out.println(input);
            parser.parseCommand(input, ui, task, storage);

        }

        ui.printBye();
    }
}
