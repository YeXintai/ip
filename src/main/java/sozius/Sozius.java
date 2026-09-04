package sozius;

import javafx.application.Application;
import sozius.parser.Parser;
import sozius.storage.Storage;
import sozius.tasklist.TaskList;
import sozius.ui.Gui;
import sozius.ui.Ui;

public class Sozius {
    private TaskList tasks = new TaskList();
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the chatbot with the tasklist from tasks.txt
     */
    public Sozius() {
        storage = new Storage("./tasks.txt");
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
        Application.launch(Gui.class, args);
    }

    public String getResponse(String command) {
        return parser.parse(command);
    }
}