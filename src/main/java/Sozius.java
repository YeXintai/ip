import java.io.IOException;
import java.util.*;

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";
    private TaskList tasks = new TaskList();
    private Storage storage;

    private void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " +  tasks.get(i).toUserString());
        }
    }
    private void markTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
                return;
            }
            tasks.markTask(index - 1);
            System.out.println("Marked as done:");
            System.out.println(tasks.get(index - 1).toUserString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private void unmarkTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
                return;
            }
            tasks.unmarkTask(index - 1);
            System.out.println("Marked as not done:");
            System.out.println(tasks.get(index - 1).toUserString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private void deleteTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
                return;
            }
            System.out.println("Task deleted:");
            System.out.println(tasks.remove(index - 1).toUserString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private Task createTodoTask(String args) {
        if (args.isEmpty()) {
            System.out.println("Invalid command: incorrect number of arguments for todo");
            return null;
        }
        Task task = new TodoTask(args);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }
    private Task createDeadlineTask(String args) {
        String[] splitArgs = args.split(" /by ");
        String desc = splitArgs[0];
        DueDate by = DueDate.parse(splitArgs[1]);
        Task task = new DeadlineTask(desc, by);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }
    private Task createEventTask(String args) {
        String[] splitArgs1 = args.split(" /from ");
        String desc = splitArgs1[0];
        String[] splitArgs2 = splitArgs1[1].split(" /to ");
        DueDate from = DueDate.parse(splitArgs2[0]);
        DueDate to = DueDate.parse(splitArgs2[1]);
        Task task = new EventTask(desc, from, to);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }

    private void parseUserCommand(String line) {
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

    public Sozius(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() {
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Sozius(args[0]).run();
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
                parseUserCommand(line);
            }
            System.out.print(sep);
        }
        System.out.println(goodbye);
    }
}