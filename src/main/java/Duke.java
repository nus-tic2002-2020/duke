import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String dukeDivider = "\t----------------------------------------------------------------------------";
        Date now = new Date();
        SimpleDateFormat dateToday = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat dayToday = new SimpleDateFormat("EEEE");

        String logo =
                "====================================================================================\n" +
                "====================================================================================\n" +
                "===  DDDDDDDDD     UUU      UUU  KKK      KKK  EEEEEEEEEEEE            A0177803Y ===\n" +
                "===  DDDDDDDDDD    UUU      UUU  KKK     KKK   EEEEEEEEEEE                       ===\n" +
                "===  DDD     DDD   UUU      UUU  KKK    KKK    EEE                               ===\n" +
                "===  DDD      DDD  UUU      UUU  KKK   KKK     EEEEEEEEEE                        ===\n" +
                "===  DDD      DDD  UUU      UUU  KKK  KKK      EEEEEEEEEE                        ===\n" +
                "===  DDD      DDD  UUU      UUU  KKKKKKK       EEE                               ===\n" +
                "===  DDD      DDD  UUU      UUU  KKKKKK        EEE           NOTEPAD,            ===\n" +
                "===  DDD     DDD   UUU      UUU  KKK KKKK      EEE           TASK MANAGER,       ===\n" +
                "===  DDDDDDDDDD     UUUUUUUUUU   KKK   KKKK    EEEEEEEEEEE   BUDGET ASSISTANT,   ===\n" +
                "===  DDDDDDDDD       UUUUUUUU    KKK     KKKK  EEEEEEEEEEEE  AND MORE...!        ===\n" +
                "====================================================================================\n" +
                "====================================================================================\n";
        System.out.print("\nWelcome to PROJECT >>>\n" + logo);
        System.out.print(String.format("%1$-42s%2$42s", dateToday.format(now), dayToday.format(now)) + "\n\n");

        System.out.println(dukeDivider);
        System.out.println("\tHello! I'm Duke, your all-rounded personal assistant!");
        System.out.println("\tWhat do you need done today?");
        System.out.println(dukeDivider);

        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            if(input.equals("bye")) {
                System.out.println(dukeDivider);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(dukeDivider);
                break;
            } else if(input.equals("list")) {
                for(int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ": " + tasks[i].toString());
                }
            } else {
                System.out.println(dukeDivider);
                tasks[taskCount] = input;
                System.out.println("\tadded: " + tasks[taskCount]);
                taskCount++;
                System.out.println(dukeDivider);
            }
        }
    }
}
