package parser;//deals with making sense of the user command


import commands.*;

public class Parser {
    String parsedInput;

    public Parser(){
        this.parsedInput = "";
    }

    public static Command parse(String input){
        String description = "";
        String secPart = "";
        String keyword;
        int option = 0;
        int index = 0;

        if(input.equals("blah")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            //throw new DukeException();
        }

        switch(input){
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
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
                    description = description.replaceFirst("event", "").stripLeading();
                    return new AddCommand("event", description, secPart);
                }
                if(input.contains("deadline")){
                    index = input.indexOf("/by");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst( "/by", "").stripLeading();

                    description = input.substring(0,index - 1);
                    description = description.replaceFirst("deadline", "").stripLeading();
                    return new AddCommand("deadline", description, secPart);
                }

                if(input.contains("delete")){
                    option = Integer.parseInt(input.replace("delete", "").trim());
                    return new DeleteCommand(option);
                }

                if(input.contains("find")){
                    keyword = input.replaceFirst( "find", "").stripLeading();
                    return new FindCommand(keyword);
                }

                description = input;
                return new AddCommand("task", description, secPart);

        }
    }

}
