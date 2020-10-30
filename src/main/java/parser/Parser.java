package parser;//deals with making sense of the user command


import commands.*;


public class Parser {
    String parsedInput;

    public Parser(){
        this.parsedInput = "";
    }

    public static Command parse(String input){
        String description;
        String secPart;
        int option = 0;
        int index = 0;

        if(input.equals("blah")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
        }

        switch(input){
            case "list":
                //return Command()
            case "bye":
                return new ExitCommand();
                break;
            default:
                if(input.contains("done")){
                    option = Integer.parseInt(input.replace("done", "").trim());
                    return new DoneCommand(option);
                }


                if(input.contains("todo")){
                    description = input.replaceFirst("todo", "").stripLeading();
                    if(input.isEmpty() == true) {
                        System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                    }

                    return new AddCommand("todo", description, secPart);
                }

                if(input.contains("event")){
                    index = input.indexOf("/at");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst( "/at", "").stripLeading();

                    description = input.substring(0,index - 1);
                    description = input.replaceFirst("event", "").stripLeading();
                    return new AddCommand("event", description, secPart);
                }
                if(input.contains("deadline")){
                    index = input.indexOf("/by");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst( "/by", "").stripLeading();
                    description = input.substring(0,index - 1);
                    description = input.replaceFirst("deadline", "").stripLeading();
                    return new AddCommand("deadline", description, secPart);
                }

                if(input.contains("delete")){
                    return new DeleteCommand ();
                    //deleteMemo(input,memo);
                }

                if(input.contains("find")){
                    return new FindCommand();
                }

                //default add task.
                return AddCommand("task", description, secPart);
                //addMemo(input, memo,1);

        }
    }


}
