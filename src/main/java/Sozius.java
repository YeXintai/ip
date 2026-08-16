import java.util.*;
//TODO:
//Error if task number is invalid(negative, too large)
//Error if mark/unmark command does not follow proper format
//Error if tasks array is full and add command

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";

    public static void main(String[] args) {
        String banner =
            "     ________  ________  ________  ___  ___  ___  ________      \n" +
            "    |\\   ____\\|\\   __  \\|\\_____  \\|\\  \\|\\  \\|\\  \\|\\   ____\\     \n" +
            "    \\ \\  \\___|\\ \\  \\|\\  \\\\|___/  /\\ \\  \\ \\  \\\\\\  \\ \\  \\___|_    \n" +
            "     \\ \\_____  \\ \\  \\\\\\  \\   /  / /\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \n" +
            "      \\|____|\\  \\ \\  \\\\\\  \\ /  /_/__\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \n" +
            "        ____\\_\\  \\ \\_______\\\\________\\ \\__\\ \\_______\\____\\_\\  \\ \n" +
            "       |\\_________\\|_______|\\|_______|\\|__|\\|_______|\\_________\\\n" +
            "       \\|_________|                                 \\|_________|\n" +
            "                                                                \n";
        String greeting =
                sep +
                banner +
                "Sozius: Hello! I'm Sozius.\n" +
                "        What do you need?\n" +
                sep;
        String goodbye =
                "Sozius: Goodbye.\n" +
                sep;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(greeting);
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            System.out.print(sep);
            int firstSpace = line.indexOf(' ');
            String command  = firstSpace == -1 ? line : line.substring(0, firstSpace);
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " +  tasks[i]);
                }
            } else if (firstSpace == -1) {
                System.out.println("Invalid command: " + command);
            } else if (command.equals("mark")) {
                String line_args = line.substring(firstSpace + 1);
                int index = Integer.parseInt(line_args);
                tasks[index - 1].setDone(true);
                System.out.println("Marked as done:");
                System.out.println(tasks[index - 1]);
            } else if (command.equals("unmark")) {
                String line_args = line.substring(firstSpace + 1);
                int index = Integer.parseInt(line_args);
                tasks[index - 1].setDone(false);
                System.out.println("Marked as not done:");
                System.out.println(tasks[index - 1]);
            } else if (command.equals("todo")) {
                String line_args = line.substring(firstSpace + 1);
                tasks[taskCount++] = new TodoTask(false, line_args);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list");
            } else {
                System.out.println("Invalid command: unknown command");
            }
            System.out.print(sep);
        }
        System.out.println(goodbye);
    }
}