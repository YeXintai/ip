import java.util.*;
//TODO:
//Error if mark/unmark command does not follow proper format
//Error if tasks array is full and add command

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " +  tasks.get(i));
        }
    }
    private static void markTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
            }
            tasks.get(index - 1).setDone(true);
            System.out.println("Marked as done:");
            System.out.println(tasks.get(index - 1));
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private static void unmarkTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
            }
            tasks.get(index - 1).setDone(false);
            System.out.println("Marked as not done:");
            System.out.println(tasks.get(index - 1));
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private static void deleteTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
            }
            System.out.println("Task deleted:");
            System.out.println(tasks.remove(index - 1));
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private static void createTodoTask(String args) {
        if (args.isEmpty()) {
            System.out.println("Invalid command: incorrect number of arguments for todo");
            return;
        }
        tasks.add(new TodoTask(args));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
    private static void createDeadlineTask(String args) {
        String[] splitArgs = args.split(" /by ");
        String desc = splitArgs[0];
        String deadline = splitArgs[1];
        tasks.add(new DeadlineTask(desc, deadline));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
    private static void createEventTask(String args) {
        String[] splitArgs1 = args.split(" /from ");
        String desc = splitArgs1[0];
        String[] splitArgs2 = splitArgs1[1].split(" /to ");
        String from = splitArgs2[0];
        String to = splitArgs2[1];
        tasks.add(new EventTask(desc, from, to));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void parse(String line) {
        int firstSpace = line.indexOf(' ');
        Command command  = firstSpace == -1
                ? Command.getCommand(line)
                : Command.getCommand(line.substring(0, firstSpace));
        String args = line.substring(firstSpace + 1);
        if (command == null) {
            System.out.println("Error: unknown command");
            return;
        }
        switch (command) {
            case Command.LIST:
                listTasks();
                break;
            case Command.MARK:
                markTask(args);
                break;
            case Command.UNMARK:
                unmarkTask(args);
                break;
            case Command.TODO:
                createTodoTask(args);
                break;
            case Command.DEADLINE:
                createDeadlineTask(args);
                break;
            case Command.EVENT:
                createEventTask(args);
                break;
            case Command.DELETE:
                deleteTask(args);
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