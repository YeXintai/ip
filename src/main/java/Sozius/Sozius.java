package Sozius;

import Sozius.parser.Parser;
import Sozius.storage.Storage;
import Sozius.tasklist.TaskList;
import Sozius.ui.Ui;

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";
    private TaskList tasks = new TaskList();
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Sozius(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser(ui, tasks);
    }

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

    public static void main(String[] args) {
        new Sozius("./tasks.txt").run();
    }
}