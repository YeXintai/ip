package sozius.storage;

import sozius.tasklist.TaskList;
import sozius.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }
    public Storage() {
        this("./tasks.txt");
    }

    private static Task parseFileCommand(String line) {
        String[] splitArgs = line.split(" \\| ");
        String type = splitArgs[0];
        boolean marked = splitArgs[1].equals("1");
        String desc = splitArgs[2];
        Task task = null;
        if (type.equals("T")) {
            task = new TodoTask(desc);
        } else if (type.equals("D")) {
            DueDate by = DueDate.parse(splitArgs[3]);
            task = new DeadlineTask(desc, by);
        } else {
            String[] times = splitArgs[3].split("/");
            task = new EventTask(desc, DueDate.parse(times[0]), DueDate.parse(times[1]));
        }
        task.setDone(marked);
        return task;
    }

    /**
     * Parses all lines from the file to find tasks.
     * @return the ArrayList<Task> of all the tasks found from parsing
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File inputFile = new File(fileName);
            if (!inputFile.exists()) {
                System.out.println("Error: tasks file does not exist");
                System.out.println("Creating tasks file...");
                inputFile.createNewFile();
            }

            try (Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    tasks.add(parseFileCommand(scanner.nextLine()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: tasks file could not be created");
        }
        return tasks;
    }

    /**
     * Saves all tasks to the file
     * @param tasks the TaskList which contains all the tasks
     */
    public void save(TaskList tasks) {
        try (FileWriter myWriter = new FileWriter(fileName)) {
            for (Task task : tasks) {
                myWriter.write(task.toFileString() + '\n');
            }
        } catch (IOException e) {
            System.out.println("Error: tasks file could not be created");
        }
    }
}
