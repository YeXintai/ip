import java.util.*;
//TODO:
//Error if task number is invalid(negative, too large)
//Error if mark/unmark command does not follow proper format
//Error if tasks array is full and add command

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void markTask(String args) {
        int index = Integer.parseInt(args);
        tasks[index - 1].setDone(true);
        System.out.println("Marked as done:");
        System.out.println(tasks[index - 1]);
    }
    private static void unmarkTask(String args) {
        int index = Integer.parseInt(args);
        tasks[index - 1].setDone(false);
        System.out.println("Marked as not done:");
        System.out.println(tasks[index - 1]);
    }
    private static void createTodoTask(String args) {
        if (args.equals("")) {
            System.out.println("Invalid command: incorrect number of arguments for todo");
            return;
        }
        tasks[taskCount++] = new TodoTask(args);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }
    private static void createDeadlineTask(String args) {
        String[] splitArgs = args.split(" /by ");
        String desc = splitArgs[0];
        String deadline = splitArgs[1];
        tasks[taskCount++] = new DeadlineTask(desc, deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }
    private static void createEventTask(String args) {
        String[] splitArgs1 = args.split(" /from ");
        String desc = splitArgs1[0];
        String[] splitArgs2 = splitArgs1[1].split(" /to ");
        String from = splitArgs2[0];
        String to = splitArgs2[1];
        tasks[taskCount++] = new EventTask(desc, from, to);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    private static void parse(String line) {
        if (line.equals("list")) {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " +  tasks[i]);
            }
        }
        int firstSpace = line.indexOf(' ');
        if (firstSpace == -1) {
            System.out.println("Invalid command: Commands other than list require at least 1 argument");
            return;
        }
        String command  = firstSpace == -1 ? line : line.substring(0, firstSpace);
        String args = firstSpace == -1 ? line : line.substring(firstSpace + 1);
        switch (command) {
            case "mark":
                markTask(args);
                break;
            case "unmark":
                unmarkTask(args);
                break;
            case "todo":
                createTodoTask(args);
                break;
            case "deadline":
                createDeadlineTask(args);
                break;
            case "event":
                createEventTask(args);
                break;
            default:
                System.out.println("Error: unknown command");
                break;
        }
    }

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

        System.out.println(greeting);
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            System.out.print(sep);
            if (line.equals("bye")) {
                break;
            } else {
                parse(line);
            }
            System.out.print(sep);
        }
        System.out.println(goodbye);
    }
}