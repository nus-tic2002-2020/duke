package parser;//deals with making sense of the user command

import command.*;


public class Parser {
    String parsedInput;

    public Parser(){
        this.parsedInput = "";
    }

    public Command parse(String input){
        Command result = new Command();


        if(input.equals("blah")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
        }

        switch(input){
            case "list":
                return new Command()
            case "bye":

                return new ExitCommand();
                System.out.println(System.lineSeparator() + "Bye. Hope to see you again soon!");
                break;
            default:
                if(input.contains("done")){
                    return Command(input,memo);
                    break;
                }
                if(input.contains("todo")){
                    return AddCommand();
                    //addMemo(input, memo,2);
                    break;
                }
                if(input.contains("event")){
                    return AddCommand();
                    //addMemo(input, memo,3);
                    break;
                }
                if(input.contains("deadline")){
                    return AddCommand();
                    //addMemo(input, memo,4);
                    break;
                }

                if(input.contains("delete")){
                    return DeleteCommand ();
                    //deleteMemo(input,memo);
                    break;
                }

                if(input.contains("find")){
                    return FindCommand();
                    //findMemo(input,memo);
                    //break;
                }

                //default add task.
                return AddCommand();
                //addMemo(input, memo,1);

        }
    }


}
