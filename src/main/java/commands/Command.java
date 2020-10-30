package commands;

import ui.Ui;
import storage.Storage;

import java.util.ArrayList;

public class Command {


    public Command(){

    }

    public void execute(TaskList list, Ui ui, Storage storage){

    }


    public static void makeDone(String input, ArrayList<Task> memo){
        int option = 0;
        option = Integer.parseInt(input.replace("done", "").trim());

        if(option <= memo.size() && option >= 1){
            memo.get(option - 1).changeCompletedTo(true);
            System.out.println(System.lineSeparator() + "Nice! I've marked this task as done:" + System.lineSeparator() + memo.get(option - 1).toString());

        }else{
            System.out.println("Input Invalid");
        }
        return;
    }

}
