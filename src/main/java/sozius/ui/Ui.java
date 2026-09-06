package sozius.ui;

import java.util.Scanner;

/**
 * Ui class handles input and output.
 */
public class Ui {
    private static final String SEP = "_________________________________________________________________\n";
    private static final String BANNER =
            "     ________  ________  ________  ___  ___  ___  ________      \n"
            + "    |\\   ____\\|\\   __  \\|\\_____  \\|\\  \\|\\  \\|\\  \\|\\   ____\\     \n"
            + "    \\ \\  \\___|\\ \\  \\|\\  \\\\|___/  /\\ \\  \\ \\  \\\\\\  \\ \\  \\___|_    \n"
            + "     \\ \\_____  \\ \\  \\\\\\  \\   /  / /\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \n"
            + "      \\|____|\\  \\ \\  \\\\\\  \\ /  /_/__\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \n"
            + "        ____\\_\\  \\ \\_______\\\\________\\ \\__\\ \\_______\\____\\_\\  \\ \n"
            + "       |\\_________\\|_______|\\|_______|\\|__|\\|_______|\\_________\\\n"
            + "       \\|_________|                                 \\|_________|\n"
            + "                                                                \n";
    private static final String WELCOME =
            SEP
            + BANNER
            + "Sozius: Hello! I'm Sozius.\n"
            + "        What do you need?\n"
            + SEP;
    private static final String GOODBYE =
            "Sozius: Goodbye.\n"
            + SEP;

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Prints the separator
     */
    public void showLine() {
        System.out.println(SEP);
    }
    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        System.out.println(WELCOME);
    }
    /**
     * Prints the goodbye message
     */
    public void showGoodbye() {
        System.out.println(GOODBYE);
    }
    /**
     * Prints output
     * @param output the output
     */
    public void showOutput(String output) {
        System.out.println(output);
    }
    /**
     * Prints an error message
     * @param message the message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from user input
     * @return the line of input
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
