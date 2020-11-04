/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) throws IOException, DukeException {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(fullCommand); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException | IOException | DukeIOException e) {
                ui.showError(e.getMessage());
            } finally {
                //ui.showLine(ui.readCommand());
            }
        }
    }
    public static void main(String[] args) throws IOException, DukeException {
        System.out.println("Please provide a valid file path in this format: C:\\Folder\\sample.txt");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        //String filePath = "E:\\_BackupNUS\\TIC2002\\duke\\src\\main\\java\\data\\duke.txt";
        try{
            new Duke(filePath).run();
        }catch(DukeException e){
            new DukeException("Please provide a valid file path in this format: C:\\Folder\\sample.txt");
        }

    }
}