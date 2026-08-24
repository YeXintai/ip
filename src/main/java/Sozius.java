import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " +  tasks.get(i).toUserString());
        }
    }
    private static void markTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
                return;
            }
            tasks.get(index - 1).setDone(true);
            System.out.println("Marked as done:");
            System.out.println(tasks.get(index - 1).toUserString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private static void unmarkTask(String args) {
        try {
            int index = Integer.parseInt(args);

            if (index < 1 || index > tasks.size()) {
                System.out.println("Invalid command: Invalid index");
                return;
            }
            tasks.get(index - 1).setDone(false);
            System.out.println("Marked as not done:");
            System.out.println(tasks.get(index - 1).toUserString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid command: Index must be integer");
        }
    }
    private static void deleteTask(String args) {
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
    private static Task createTodoTask(String args) {
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
    private static Task createDeadlineTask(String args) {
        String[] splitArgs = args.split(" /by ");
        String desc = splitArgs[0];
        DueDate by = parseDueDate(splitArgs[1]);
        Task task = new DeadlineTask(desc, by);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }
    private static Task createEventTask(String args) {
        String[] splitArgs1 = args.split(" /from ");
        String desc = splitArgs1[0];
        String[] splitArgs2 = splitArgs1[1].split(" /to ");
        DueDate from = parseDueDate(splitArgs2[0]);
        DueDate to = parseDueDate(splitArgs2[1]);
        Task task = new EventTask(desc, from, to);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }
    private static DueDate parseDueDate(String args) {
        String[] splitArgs = args.split(" ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalDate date = LocalDate.parse(splitArgs[0], dateFormatter);
        LocalTime time = splitArgs.length == 1
                ? null
                : LocalTime.parse(splitArgs[1], timeFormatter);
        return new DueDate(date, time);
    }

    private static void parseUserCommand(String line) {
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
    private static void parseFileCommand(String line) {
        System.out.println(line);
        String[] splitArgs = line.split(" \\| ");
        String type = splitArgs[0];
        boolean marked = splitArgs[1].equals("1");
        String desc = splitArgs[2];
        Task task;
        if (type.equals("T")) {
            task = new TodoTask(desc);
        } else if (type.equals("D")) {
            DueDate by = parseDueDate(splitArgs[3]);
            task = new DeadlineTask(desc, by);
        } else {
            String[] times = splitArgs[3].split("/");
            task = new EventTask(desc, parseDueDate(times[0]), parseDueDate(times[1]));
        }
        task.setDone(marked);
        tasks.add(task);
    }

    public static void initializeTasks() {
        try {
            File inputFile = new File("./tasks.txt");
            if (!inputFile.exists()) {
                System.out.println("Error: tasks file does not exist");
                System.out.println("Creating tasks file...");
                inputFile.createNewFile();
            }

            try (Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    parseFileCommand(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: tasks file could not be created");
        }
    }
    public static void saveTasks() {
        try (FileWriter myWriter = new FileWriter("./tasks.txt")) {
            for (Task task : tasks) {
                myWriter.write(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error: tasks file could not be created");
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

        initializeTasks();
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
        saveTasks();
    }
}