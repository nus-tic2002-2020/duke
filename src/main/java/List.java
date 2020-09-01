import java.util.Scanner;

public class List{
    //variable
    private String[] list;
    private boolean[] done;
    private char[] task_cat;
    private static int item_count = 0;

    //constructor
    public List(){
        this.list = new String[100];
        this.done = new boolean[100];
        this.task_cat = new char[100];
    }

    //getters
    public void duke_help(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + "You have enter an invalid command!");
        System.out.println("\t" + "Following are valid commands: ");
        System.out.println(
                "\t\tlist - Display tasks in list\n" +
                        "\t\tdone <task number> - Mark task as completed\n" +
                        "\t\ttodo <task description> - Add to do task without any date/time \n" +
                        "\t\tdeadline <task description>/by <date/time> - Add task that need to be done before a specific date/time\n" +
                        "\t\tevent <task description>/at <date/time> - Add tasks that start at a specific time and ends at a specific time\n"+
                        "\t\tbye - End duke"
        );
        System.out.println("\t____________________________________________________________");
    }
    private void duke_echo(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
    private void printList(){
        if(list[0] == null){
            duke_echo("List is empty");
            return;
        }//if end
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 1 ; i <= item_count ; i++){
            String tick = (this.done[i - 1] == true) ? "✓" : "✗";
            String to_print = "[" + this.task_cat[i - 1] + "]" + "[" + tick + "] " + this.list[i - 1];
            System.out.println("\t" + i + ". " + to_print);
        }
        System.out.println("\t____________________________________________________________");
    }
    private void print_task(int index){
        String tick = (this.done[index] == true) ? "✓" : "✗";
        String to_print = "\t[" + this.task_cat[index] + "]" + "[" + tick + "] " + this.list[index];
        System.out.println(to_print);
    }

    //setters
    private void insert_item(String input){
        String[] task_split = input.split(" ", 2);
        switch(task_split[0]){
            case "todo":
                this.task_cat[item_count] = 'T';
                this.list[item_count] = task_split[1];
                break;
            case "deadline":
                this.task_cat[item_count] = 'D';
                task_split = task_split[1].split("/", 2);
                this.list[item_count] = task_split[0] + "(" + task_split[1] + ")";
                break;
            case "event":
                this.task_cat[item_count] = 'E';
                task_split = task_split[1].split("/", 2);
                this.list[item_count] = task_split[0] + "(" + task_split[1] + ")";
                break;
        }//end switch
        this.done[item_count] = false;
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
        else if(this.done[index - 1] == true){
            duke_echo("\"" + this.list[index - 1] + "\" was already marked as done");
        }
        else{
            this.done[index - 1] = true;
            String tick = (this.done[index-1] == true) ? "✓" : "✗";
            duke_echo("Nice! I've marked this task as done:\n\t[" + this.task_cat[index - 1] + "]" + "[" + tick + "] " + this.list[index - 1]);
        }
    }
    private void mark_all_complete(){
        for(int i = 0 ; i < item_count ; i++){
            this.done[i] = true;
        }
        duke_echo("All tasks(" + item_count + ") are marked as done");
    }
    public void reader(){
        String user_input;
        Scanner input = new Scanner(System.in);
        user_input = input.nextLine();
        responses(user_input.trim());
    }
    private void responses(String user_input){
        if(user_input.equals("bye")){ //bye: end duke
            duke_echo("Bye. Hope to see you again soon!");
            System.exit(0);
        }//end if
        else if(user_input.equals("list")){ //list: list items
            this.printList();
        }//end else if
        else if(user_input.contains("done") && (user_input.equals("done") == false)){ //done: change done to true from false
            if(item_count == 0){
                duke_echo("List is empty!");
                return;
            }
            if(user_input.equals("done all")){
                this.mark_all_complete();
                return;
            }
            this.mark_completion(user_input);
        }
        else if((user_input.contains("todo") || user_input.contains("deadline") || user_input.contains("event")) &&
                ((user_input.equals("todo") || user_input.equals("deadline") || user_input.equals("event")) == false)
        ){ //insertion of item into list
            this.insert_item(user_input);
        }//end else if
        else{
            duke_help();
        }//end else
    }
}