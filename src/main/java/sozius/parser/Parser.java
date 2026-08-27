package sozius.parser;

import sozius.tasklist.TaskList;

import sozius.task.DueDate;
import sozius.task.TodoTask;
import sozius.task.DeadlineTask;
import sozius.task.EventTask;

import sozius.ui.Ui;

public class Parser {
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates a parser
     * @param ui the ui the parser uses for output
     * @param tasks used by the parser to store tasks
     */
    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

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
    private TodoTask createTodoTask(String args) {
        if (args.isEmpty()) {
            System.out.println("Invalid command: incorrect number of arguments for todo");
            return null;
        }
        TodoTask task = new TodoTask(args);
        tasks.add(new TodoTask(args));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }
    private DeadlineTask createDeadlineTask(String args) {
        String[] splitArgs = args.split(" /by ");
        String desc = splitArgs[0];
        DueDate by = DueDate.parse(splitArgs[1]);
        DeadlineTask task = new DeadlineTask(desc, by);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }
    private EventTask createEventTask(String args) {
        String[] splitArgs1 = args.split(" /from ");
        String desc = splitArgs1[0];
        String[] splitArgs2 = splitArgs1[1].split(" /to ");
        DueDate from = DueDate.parse(splitArgs2[0]);
        DueDate to = DueDate.parse(splitArgs2[1]);
        EventTask task = new EventTask(desc, from, to);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.getLast().toUserString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return task;
    }

    /**
     * Parses a line of text entered by the user
     * Executes the command
     * Prints error message and returns if command is invalid
     * @param line the line of text
     * @see Command
     */
    public void parse(String line) {
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
}
