package sozius;

import sozius.parser.Parser;
import sozius.storage.Storage;
import sozius.tasklist.TaskList;
import sozius.ui.Ui;

public class Sozius {
    private TaskList tasks = new TaskList();
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the chatbot with the tasklist from a file
     * @param filePath name of file
     */
    public Sozius(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser(ui, tasks);
    }

    /**
     * Starts the chatbot
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showLine();
                String line = ui.readCommand();
                if (line.equals("bye")) {
                    break;
                } else {
                    parser.parse(line);
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        storage.save(tasks);
        ui.showGoodbye();
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        new Sozius("./tasks.txt").run();
    }
}