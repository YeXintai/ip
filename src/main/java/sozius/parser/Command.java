package sozius.parser;

/**
 * Command Enum class for all commands
 */
public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find");

    private final String word;

    Command(String word) {
        this.word = word;
    }

    /**
     * Returns the command of the string
     * @param s the input string
     * @return a Command, or null if the string does not match any
     */
    public static Command getCommand(String s) {
        for (Command c : values()) {
            if (c.word.equals(s)) {
                return c;
            }
        }
        return null;
    }
}
