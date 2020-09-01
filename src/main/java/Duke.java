public class Duke {
    public static void duke_says(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }//end duke_says

    public static void main(String[] args) {
        List new_list = new List();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        duke_says("Hello! I'm Duke\n\tWhat can I do for you?");
        while(true){
            new_list.reader();
        }//end while loop
    }//end main
}
