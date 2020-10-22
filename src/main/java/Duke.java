
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;



//TODO: Refactor exceptions. Refactor addMemo[need to check for copies] and deleteMemo[need to reduce total task size when delete]
//TODO: Probably ensure that delete task function, will also print? Not sure TBC
//TODO: More OOP and Packages from Level 7
//TODO: JavaDoc and JUnit Testing from level 8


public class Duke {

    public static void command(String input, ArrayList<Task> memo) throws DukeException{
        if(input.equals("blah")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
        }

        switch(input){
            case "list":
                printMemo(memo);
                break;
            case "bye":
                System.out.println(System.lineSeparator() + "Bye. Hope to see you again soon!");
                break;
            default:
                if(input.contains("done")){
                    makeDone(input,memo);
                    break;
                }
                if(input.contains("todo")){
                    addMemo(input, memo,2);
                    break;
                }
                if(input.contains("event")){
                    addMemo(input, memo,3);
                    break;
                }
                if(input.contains("deadline")){
                    addMemo(input, memo,4);
                    break;
                }

                if(input.contains("delete")){
                    deleteMemo(input,memo);
                    break;
                }

                //default add task.
                addMemo(input, memo,1);

        }

        //return memo;
    }

    public static void deleteMemo(String input, ArrayList<Task> memo) throws DukeException{
        int option = 0;
        input = input.replaceFirst("delete", "").strip();
        if(input.isEmpty() == true){
            throw new DukeException();
        }

        try{
            option = Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println("Please enter an integer for your delete command.");
        }


        memo.remove(option - 1);

        return;
    }

    //TODO: Need to check for copies
    //option 1 for Task, 2 for Todos, 3 for Events, 4 for Deadlines
    public static void addMemo(String input, ArrayList<Task> memo, int option) throws DukeException{

        String secondPart = "";
        int index = 0;
        int size = 0;

        //if(memo.contains(Task(input)) == true){
            //System.out.println("Your task is already in the memory.");
            //return;
        //}


        switch(option){
            case 1:
                memo.add( new Task (input) );
                break;

            case 2:
                input = input.replaceFirst("todo", "").stripLeading();
                if(input.isEmpty() == true){
                    System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                    throw new DukeException();
                    //return memo;
                }
                memo.add(new ToDo(input) );
                break;

            case 3:
                index = input.indexOf("/at");
                secondPart = input.substring(index);
                secondPart = secondPart.replaceFirst( "/at", "").stripLeading();

                input = input.substring(0,index - 1);
                input = input.replaceFirst("event", "").stripLeading();
                memo.add( new Event(input, secondPart) );
                break;

            case 4:
                index = input.indexOf("/by");
                secondPart = input.substring(index);
                secondPart = secondPart.replaceFirst( "/by", "").stripLeading();
                input = input.substring(0,index - 1);
                input = input.replaceFirst("deadline", "").stripLeading();
                memo.add(new Deadline(input, secondPart) );
                break;
        }

        size = memo.size();

        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + memo.get(size - 1).toString());

        memo.get(size - 1).printTotalTasks();
        return;
    }

    public static void printMemo(ArrayList<Task> memo){
        int size = memo.size();
        if(size == 0){
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "Task List:");
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + "." + memo.get(i).toString());
        }

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

    private static void writeToFile(String filePath, ArrayList<Task> memo) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        int size = memo.size();
        if(size == 0){
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        String temp = "";
        String text = "";
        String firstPart;
        String secPart = "";
        int done = 0;

        System.out.println(System.lineSeparator() + "Writing Tasks List from Memo to File");
        for(int i = 0; i < size; i ++){
            temp = memo.get(i).getClass().toString();
            switch(temp){
                case "class Task":
                    firstPart = "O | ";
                    break;
                case "class ToDo":
                    firstPart = "T | ";
                    break;
                case "class Deadline":
                    firstPart = "D | ";
                    secPart = " | " + ((Deadline)memo.get(i)).getByDeadline();
                    break;
                case "class Event":
                    firstPart = "E | ";
                    secPart = " | " + ((Event)memo.get(i)).getAt();
                    break;
                default:
                    System.out.println("Can't get Class from Task Array");
                    return;
            }

            done = (memo.get(i).getIsDone()) ? 1:0;
            text = firstPart + String.valueOf(done) + " | " + memo.get(i).getDescription() + secPart;
            fw.write(text + System.lineSeparator());
            text = "";
            secPart = "";

        }
        fw.close();
    }

    public static void loadToMemo(String filePath, ArrayList<Task> memo) throws IOException{
        File f = new File(filePath);
        FileReader fr =new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        String[] splitInput;
        while((line=br.readLine())!=null){
            splitInput = line.split(" \\| ");
            switch(splitInput[0]){
                case "O":
                    memo.add(new Task (splitInput[2]));
                    break;
                case "T":
                    memo.add(new ToDo(splitInput[2]));
                    break;
                case "D":
                    memo.add(new Deadline(splitInput[2], LocalDateTime.parse(splitInput[3], DateTimeFormatter.ofPattern("d MMM yyyy H:m:s")).toString() ) );
                    break;
                case "E":
                    memo.add(new Event(splitInput[2],splitInput[3]) );
                    break;
                default:
                    System.out.println("Error in loading file to memo");
                    return;
            }

            if(splitInput[1].equals("1")){
                memo.get(memo.size()-1).changeCompletedTo(true);
            }

        }

        fr.close();
    }

    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ArrayList<Task> memo = new ArrayList<Task> ();

        String input;

        File directoryData = new File("data");
        if(directoryData.isDirectory() == false || directoryData.exists() == false){
            System.out.println("Directory \"data\" is not found, creating a new one");
            directoryData.mkdir();
        }

        File f = new File("data/tasks_list.txt");

        if(f.isFile() == false || f.exists() == false){
            System.out.println("File \"duke.text\" is not found, creating a new one");
            f.createNewFile();
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("Loading from File to Memo");

        try{
            loadToMemo("data/tasks_list.txt", memo);
        }catch(IOException e){
            System.out.println("Load to file error");
        }

        printMemo(memo);


        int start = 1;

        while(start == 1){
            try{
                System.out.println(System.lineSeparator() + "What can I do for you?");
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
