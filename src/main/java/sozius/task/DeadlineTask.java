package sozius.task;

public class DeadlineTask extends Task {
    private DueDate deadline;

    /**
     * Creates a DeadlineTask.
     * @param description task description
     * @param deadline task deadline
     */
    public DeadlineTask(String description, DueDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns string representation of the task.
     * For storing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %c | %s | %s",
                super.done ? '1' : '0',
                super.description,
                deadline.toFileString());
    }

    /**
     * Returns string representation of the task.
     * For displaying to user.
     */
    @Override
    public String toUserString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.toUserString());
    }
}
