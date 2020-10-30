
import storage.Storage;
import ui.Ui;
import parser.Parser;
import commands.Command;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;


//TODO: Refactor exceptions. Refactor addMemo[need to check for copies] and deleteMemo[need to reduce total task size when delete]
//TODO: Probably ensure that delete task function, will also print? Not sure TBC
//TODO: More OOP and Packages from Level 7
//TODO: JavaDoc and JUnit Testing from level 8
//TODO: Level 9 Individual Feature, Coding Standard and Coding Quality


public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            list = new TaskList();
        }
    }


    public void run() {
        ui.printIntro();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.scanForInput();
                ui.showLine(); // show the divider line ("_______")

                Command c = Parser.parse(input);
                c.execute(list, ui, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                //ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.printBye();

    }


    public static void main(String[] args) throws IOException{
        new Duke("data/tasks_list.txt").run();


        //try{
          //  loadToMemo("data/tasks_list.txt", memo);
        //}catch(IOException e){
        //    System.out.println("Load to file error");
       // }



        //try{
         //   writeToFile(toFile,memo);
       // } catch (IOException e){
        //    System.out.println("Write to file error");
       // }


    }
}
