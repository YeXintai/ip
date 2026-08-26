package Sozius.task;

public class DeadlineTask extends Task {
    private DueDate deadline;

    public DeadlineTask(String description, DueDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return String.format("D | %c | %s | %s\n",
                super.done ? '1' : '0',
                super.description,
                deadline.toFileString());
    }

    @Override
    public String toUserString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.toUserString());
    }
}
