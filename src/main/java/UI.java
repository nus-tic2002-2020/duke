import java.util.*;
import java.io.*;

public class UI {
    private final Scanner commandline;

    public UI(){
        this(System.in);
    }

    public UI(InputStream in){
        this.commandline = new Scanner(in);
    }

    public String readCommand(){
        String command = commandline.nextLine();
        return command;
    }

    public void showWelcome(){
        System.out.println(showLine("Hello! I'm Duke\n\tWhat can I do for you?"));
    }

    public void showGoodbye(){
        System.out.println(showLine("Bye. Hope to see you again soon!"));
        System.exit(0);
    }

    public String showLine(String message){
        return "\t____________________________________________________________\n\t" + message + "\n\t____________________________________________________________ ";
    }

    public void showOutput(String output) {
        System.out.println(showLine(output));
    }

    public void showError(String errorMessage){
        System.out.println(showLine("☹ OOPS!!! " + errorMessage));
    }
}
