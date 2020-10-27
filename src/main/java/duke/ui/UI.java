package duke.ui;

import duke.task.*;
import duke.storage.*;
import duke.command.*;
import java.util.Scanner;

public class UI {
    //accessor
    /*for handling user input*/
    public void reader(TaskList tasks, Storage storage) {
        //process user input
        String user_input;
        Scanner input = new Scanner(System.in);
        user_input = input.nextLine().trim();

        //deal with inputs
        try{
            responses(tasks, storage, user_input);
        }
        catch(IllegalInputException e){ //unknown input
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        catch(ArrayIndexOutOfBoundsException e){ //todo, event & deadline
            System.out.println("☹ OOPS!!! The description of a " + user_input.trim() + " cannot be empty.");
        }
        catch(NumberFormatException e){ //done
            System.out.println("☹ OOPS!!! The description of a " + user_input.trim() + " cannot be empty.");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! Delete is out of bound. ");
        }
    }
    public void responses(TaskList tasks, Storage storage, String user_input) throws IllegalInputException{
        if(user_input.equals("bye")){ //bye: end duke
            //save list
            storage.save(tasks);
            //end duke
            tasks.duke_echo("Bye. Hope to see you again soon!");
            System.exit(0);
        }//end if
        else if(user_input.equals("list")){ //list: list items
            tasks.printList();
        }//end else if
        else if(user_input.contains("done")){ //done: change done to true from false
            if(user_input.equals("done all")){
                tasks.mark_all_complete();
                return;
            }
            tasks.mark_completion(user_input);
        }
        else if((user_input.contains("todo") || user_input.contains("deadline") || user_input.contains("event"))){ //insertion of item into list
            tasks.insert_item(user_input);
        }//end else if
        else if(user_input.contains("delete")){
            tasks.delete(user_input);
        }
        else{
            throw new IllegalInputException();
        }//end else
    }
    /*for loading previously saved list*/
    public void responses(TaskList tasks, String user_input){
        if(user_input.contains("done")){ //done: change done to true from false
            if(user_input.equals("done all")){
                tasks.mark_all_complete();
                return;
            }
            tasks.mark_completion(user_input);
        }
        else if((user_input.contains("todo") || user_input.contains("deadline") || user_input.contains("event"))){ //insertion of item into list
            tasks.insert_item(user_input);
        }//end else if
    }
}
