package main;

public class DukeApp {

//start the interaction
        UI ui;
        Storage storage = new Storage();
        public DukeApp()
        {
            ui = new UI();
        }
        public void run() {
            ui.printWelcomeMessage();
            Parser parser = new Parser();
            String input = "";
            TaskList line = new TaskList();
            storage.load("listData.txt", line);

            while (!input.equals("bye")) //if string doesn't equal to bye
            {
                input = ui.getUserInput();
                parser.parseCommand(input, ui, line, storage);

            }

            ui.printBye();
        }
}
