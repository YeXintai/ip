public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String word;

    Command(String word) {
        this.word = word;
    }

    public static Command getCommand(String s) {
        for (Command c : values()) {
            if (c.word.equals(s)) {
                return c;
            }
        }
        return null;
    }
}
