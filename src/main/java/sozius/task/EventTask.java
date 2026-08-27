package sozius.task;

public class EventTask extends Task {
    private DueDate from;
    private DueDate to;

    /**
     * Creates an EventTask
     * @param desc task description
     * @param from start DueDate
     * @param to end DueDate
     */
    public EventTask(String desc, DueDate from, DueDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of the task.
     * For storing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %c | %s | %s/%s",
                super.done ? '1' : '0',
                super.description,
                from.toFileString(),
                to.toFileString());
    }

    /**
     * Returns string representation of the task.
     * For displaying to user.
     */
    @Override
    public String toUserString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.toUserString(),
                to.toUserString());
    }
}