package sozius.task;

public class TodoTask extends Task {
    /**
     * Creates a TodoTask.
     * @param description task description
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns string representation of the task.
     * For storing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %c | %s",
                super.done ? '1' : '0',
                super.description);
    }

    /**
     * Returns string representation of the task.
     * For displaying to user.
     */
    @Override
    public String toUserString() {
        return "[T]" + super.toUserString();
    }
}
