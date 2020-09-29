import java.util.Scanner;
import java.util.ArrayList;

public class List{
    //variable
    private ArrayList<String> list;
    private ArrayList<Boolean> done;
    private ArrayList<Character> task_cat;
    private static int item_count = 0;

    //constructor
    public List(){
        this.list = new ArrayList<String>();
        this.done = new ArrayList<Boolean>();
        this.task_cat = new ArrayList<Character>();
    }

    //getters
    private void duke_echo(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
    private void printList(){
        if(this.list.size() == 0){
            duke_echo("List is empty");
            return;
        }//if end
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 1 ; i <= item_count ; i++){
            String tick = (this.done.get(i - 1) == true) ? "✓" : "✗";
            String to_print = "[" + this.task_cat.get(i - 1) + "]" + "[" + tick + "] " + this.list.get(i - 1);
            System.out.println("\t" + i + ". " + to_print);
        }
        System.out.println("\t____________________________________________________________");
    }
    private void print_task(int index){
        String tick = (this.done.get(index) == true) ? "✓" : "✗";
        String to_print = "\t[" + this.task_cat.get(index) + "]" + "[" + tick + "] " + this.list.get(index);
        System.out.println(to_print);
    }

    //setters
    private void insert_item(String input){
        String[] task_split = input.split(" ", 2);
        switch(task_split[0]){
            case "todo":
                this.task_cat.add('T');
                this.list.add(task_split[1]);
                break;
            case "deadline":
                this.task_cat.add('D');
                task_split = task_split[1].split("/", 2);
                this.list.add(task_split[0] + "(" + task_split[1] + ")");
                break;
            case "event":
                this.task_cat.add('E');
                task_split = task_split[1].split("/", 2);
                this.list.add(task_split[0] + "(" + task_split[1] + ")");
                break;
        }//end switch
        //this.done.set(item_count, false);
        this.done.add(false);
        item_count++;
        System.out.println("\t____________________________________________________________");
        System.out.print("\tGot it. I've added this task:\n\t");
        print_task(item_count - 1);
        System.out.println("\tNow you have " + item_count + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }
    private void mark_completion(String done_message){
        /*Remove any unwanted characters that are not int*/
        String number = done_message.substring(done_message.indexOf(" ") + 1);
        for(int i = 0 ; i < number.length() ; i++){
            if(number.charAt(i) < 48 || number.charAt(i) > 57){
                number = number.substring(0, i);
            }//end if
        }//end for loop

        int index = Integer.parseInt(number); //convert to int

        /*Check if number is within bound*/
        if(index > item_count || index <= 0){
            duke_echo("There are only " + item_count + " items or the value is out of bound!");
        }
        else if(this.done.get(index - 1) == true){
            duke_echo("\"" + this.list.get(index - 1) + "\" was already marked as done");
        }
        else{
            this.done.set(index - 1, true);
            String tick = (this.done.get(index-1) == true) ? "✓" : "✗";
            duke_echo("Nice! I've marked this task as done:\n\t[" + this.task_cat.get(index - 1) + "]" + "[" + tick + "] " + this.list.get(index - 1));
        }
    }
    private void mark_all_complete(){
        if(item_count == 0){
            duke_echo("The list is empty!");
            return;
        }
        for(int i = 0 ; i < item_count ; i++){
            this.done.set(i, true);
        }
        duke_echo("All tasks(" + item_count + ") are marked as done");
    }
    private void delete(String input) throws IndexOutOfBoundsException{
        String[] input_split = input.split(" ", 2);
        int index = Integer.parseInt(input_split[1]);
        if(index > item_count || index <= 0){
            throw new IndexOutOfBoundsException();
        }
        item_count--;
        System.out.println("\t____________________________________________________________");
        System.out.print("\tNoted. I've removed this task:\n\t");
        print_task(index - 1);
        System.out.println("\tNow you have " + item_count + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        this.list.remove(index - 1);
        this.done.remove(index - 1);
        this.task_cat.remove(index - 1);
    }
    public void reader(){
        String user_input;
        Scanner input = new Scanner(System.in);
        user_input = input.nextLine();
        try{
            responses(user_input.trim());
        }
        catch(IllegalInputException e){ //unknown input
            duke_echo(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        catch(ArrayIndexOutOfBoundsException e){ //todo, event & deadline
            duke_echo("☹ OOPS!!! The description of a " + user_input.trim() + " cannot be empty.");
        }
        catch(NumberFormatException e){ //done
            duke_echo("☹ OOPS!!! The description of a " + user_input.trim() + " cannot be empty.");
        }
        catch(IndexOutOfBoundsException e){
            duke_echo("☹ OOPS!!! Delete is out of bound. ");
        }
    }
    private void responses(String user_input) throws IllegalInputException{
        if(user_input.equals("bye")){ //bye: end duke
            duke_echo("Bye. Hope to see you again soon!");
            System.exit(0);
        }//end if
        else if(user_input.equals("list")){ //list: list items
            this.printList();
        }//end else if
        else if(user_input.contains("done") /*&& (user_input.equals("done") == false)*/){ //done: change done to true from false
            if(user_input.equals("done all")){
                this.mark_all_complete();
                return;
            }
            this.mark_completion(user_input);
        }
        else if((user_input.contains("todo") || user_input.contains("deadline") || user_input.contains("event"))){ //insertion of item into list
            this.insert_item(user_input);
        }//end else if
        else if(user_input.contains("delete")){
            this.delete(user_input);
        }
        else{
            throw new IllegalInputException();
        }//end else
    }
}