import java.util.Scanner;

public class List{
    //variable
    private String[] list;
    private boolean[] done;
    private static int item_count = 0;
    //private char[] task_cat; //for level 4

    //constructor
    public List(){
        this.list = new String[100];
        this.done = new boolean[100];
    }

    //getters
    private void printList(){
        if(list[0] == null){
            //System.out.println("List is empty");
            duke_echo("List is empty");
            return;
        }//if end
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 1 ; i <= list.length	; i++){
            String tick = (this.done[i-1] == true) ? "✓" : "✗";
            System.out.println("\t" + i + "." + "[" + tick + "] " +this.list[i-1]);
            if(this.list[i] == null){
                System.out.println("\t____________________________________________________________");
                return;
            }//end if
        }//end for loop
    }

    //setters
    private void duke_echo(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
    private void insert_item(String input){
        this.list[item_count] = input;
        this.done[item_count] = false;
        item_count++;
    }
    public void mark_completion(String done_message){
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
        else if(this.done[index - 1] == true){
            duke_echo("\"" + this.list[index - 1] + "\" was already marked as done");
        }
        else{
            this.done[index - 1] = true;
            String tick = (this.done[index-1] == true) ? "✓" : "✗";
            duke_echo("Nice! I've marked this task as done:\n\t[" + tick + "] " + this.list[index - 1]);
        }
    }
    public void reader(){
        String user_input;
        Scanner input = new Scanner(System.in);
        user_input = input.nextLine();
        responses(user_input);
    }
    private void responses(String user_input){
        if(user_input.equals("bye")){ //bye: end duke
            duke_echo("Bye. Hope to see you again soon!");
            System.exit(0);
        }//end if
        else if(user_input.equals("list")){ //list: list items
            this.printList();
        }//end else if
        else if(user_input.contains("done")){ //done: change done to true from false
            this.mark_completion(user_input);
        }
        else{ //insertion of item into list
            duke_echo("added: " + user_input);
            this.insert_item(user_input);
        }//end else
    }
}