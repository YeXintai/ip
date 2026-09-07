package sozius.parser;

import sozius.task.DeadlineTask;
import sozius.task.DueDate;
import sozius.task.EventTask;
import sozius.task.Task;
import sozius.task.TodoTask;
import sozius.tasklist.TaskList;

/**
 * The Parser class parses and executes commands
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Creates a parser
     * @param tasks used by the parser to store tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    private String listTasks() {
        if (tasks.size() == 0) {
            return "No tasks found";
        }
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toUserString()).append("\n");
        }
        return response.toString();
    }

    private int parseIndex(String s) {
        try {
            int index = Integer.parseInt(s);

            if (index < 1 || index > tasks.size()) {
                return -1;
            }

            return index;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String markTask(String args) {
        int index = parseIndex(args);
        if (index == -1) {
            return "Invalid command: Invalid index";
        }

        tasks.markTask(index - 1);
        return "Marked as done:\n" + tasks.get(index - 1).toUserString();
    }

    private String unmarkTask(String args) {
        int index = parseIndex(args);
        if (index == -1) {
            return "Invalid command: Invalid index";
        }

        tasks.unmarkTask(index - 1);
        return "Marked as not done:\n" + tasks.get(index - 1).toUserString();
    }

    private String deleteTask(String args) {
        int index = parseIndex(args);
        if (index == -1) {
            return "Invalid command: Invalid index";
        }

        return "Task deleted:\n" + tasks.remove(index - 1).toUserString();
    }

    private String createTask(Task task) {
        tasks.add(task);
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append(tasks.getLast().toUserString()).append("\n");
        response.append("Now you have " + tasks.size() + " tasks in the list");
        return response.toString();
    }

    private String createTodoTask(String args) {
        if (args.isEmpty()) {
            return "Invalid command: incorrect number of arguments for todo";
        }
        TodoTask task = new TodoTask(args);
        return createTask(task);
    }

    private String createDeadlineTask(String args) {
        String[] splitArgs = args.split(" /by ");
        String desc = splitArgs[0];
        DueDate by = DueDate.parse(splitArgs[1]);
        DeadlineTask task = new DeadlineTask(desc, by);
        return createTask(task);
    }

    private String createEventTask(String args) {
        String[] splitArgs1 = args.split(" /from ");
        String desc = splitArgs1[0];
        String[] splitArgs2 = splitArgs1[1].split(" /to ");
        DueDate from = DueDate.parse(splitArgs2[0]);
        DueDate to = DueDate.parse(splitArgs2[1]);
        EventTask task = new EventTask(desc, from, to);
        return createTask(task);
    }

    private String findTasks(String args) {
        int cnt = 0;
        StringBuilder response = new StringBuilder();
        response.append("Searching for tasks:\n");
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(args.toLowerCase())) {
                ++cnt;
                response.append(task.toUserString()).append("\n");
            }
        }
        response.append("Found ").append(cnt).append(" tasks\n");
        return response.toString();
    }

    /**
     * Parses a line of text entered by the user
     * Executes the command
     * Prints error message and returns if command is invalid
     * @param line the line of text
     * @see Command
     */
    public String parse(String line) {
        int firstSpace = line.indexOf(' ');
        Command command = firstSpace == -1
                ? Command.getCommand(line)
                : Command.getCommand(line.substring(0, firstSpace));
        String args = line.substring(firstSpace + 1);
        if (command == null) {
            return "Error: unknown command";
        }
        switch (command) {
            case Command.LIST:
                return listTasks();
            case Command.MARK:
                return markTask(args);
            case Command.UNMARK:
                return unmarkTask(args);
            case Command.TODO:
                return createTodoTask(args);
            case Command.DEADLINE:
                return createDeadlineTask(args);
            case Command.EVENT:
                return createEventTask(args);
            case Command.DELETE:
                return deleteTask(args);
            case Command.FIND:
                return findTasks(args);
            default:
                return "Error: unknown command";
        }
    }
}
