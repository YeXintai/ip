import java.util.*;

public class Sozius {
    private static final String sep = "_________________________________________________________________\n";

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
                sep +
                "Sozius: Goodbye.\n" +
                sep;
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(greeting);
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                System.out.print(sep);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " +  tasks[i]);
                }
                System.out.print(sep);
            } else {
                tasks[taskCount++] = line;
                System.out.print(sep);
                System.out.println("added: " + line);
                System.out.print(sep);
            }
        }
        System.out.println(goodbye);
    }
}