public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return String.format("T | %c | %s\n",
                super.done ? '1' : '0',
                super.description);
    }

    @Override
    public String toUserString() {
        return "[T]" + super.toString();
    }
}
