package sozius.ui;

import java.util.Scanner;

/**
 * Ui class handles input and output.
 */
public class Ui {
    private static final String SEP = "_________________________________________________________________\n";
    private static final String banner =
            "     ________  ________  ________  ___  ___  ___  ________      \n"
            + "    |\\   ____\\|\\   __  \\|\\_____  \\|\\  \\|\\  \\|\\  \\|\\   ____\\     \n"
            + "    \\ \\  \\___|\\ \\  \\|\\  \\\\|___/  /\\ \\  \\ \\  \\\\\\  \\ \\  \\___|_    \n"
            + "     \\ \\_____  \\ \\  \\\\\\  \\   /  / /\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \n"
            + "      \\|____|\\  \\ \\  \\\\\\  \\ /  /_/__\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \n"
            + "        ____\\_\\  \\ \\_______\\\\________\\ \\__\\ \\_______\\____\\_\\  \\ \n"
            + "       |\\_________\\|_______|\\|_______|\\|__|\\|_______|\\_________\\\n"
            + "       \\|_________|                                 \\|_________|\n"
            + "                                                                \n";
    private static final String welcome =
            SEP
            + banner
            + "Sozius: Hello! I'm Sozius.\n"
            + "        What do you need?\n"
            + SEP;
    private static final String goodbye =
            "Sozius: Goodbye.\n"
            + SEP;

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(SEP);
    }
    public void showWelcome() {
        System.out.println(welcome);
    }
    public void showGoodbye() {
        System.out.println(goodbye);
    }
    public void showOutput(String output) {
        System.out.println(output);
    }
    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
