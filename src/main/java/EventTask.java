public class EventTask extends Task {
    private DueDate from;
    private DueDate to;

    public EventTask(String desc, DueDate from, DueDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return String.format("E | %c | %s | %s/%s\n",
                super.done ? '1' : '0',
                super.description,
                from.toFileString(),
                to.toFileString());
    }

    @Override
    public String toUserString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.toUserString(),
                to.toUserString());
    }
}