
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
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            //tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            //tasks = new TaskList();
        }
    }


    public void run() {
    }


    public static void main(String[] args) throws IOException{
        ArrayList<Task> memo = new ArrayList<Task> ();

        String input;



        try{
            loadToMemo("data/tasks_list.txt", memo);
        }catch(IOException e){
            System.out.println("Load to file error");
        }

        printMemo(memo);


        int start = 1;

        while(start == 1){
            try{

                input = scan.nextLine();
                if(input.equals("bye")){
                    start = 0;
                }
                command(input,memo);
            }
            catch (Exception ex){
                System.out.println("Please input again.");
            }

        }


        String toFile = "data/tasks_list.txt";

        try{
            writeToFile(toFile,memo);
        } catch (IOException e){
            System.out.println("Write to file error");
        }


    }
}
