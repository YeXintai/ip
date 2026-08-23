public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return String.format("D | %c | %s | %s\n",
                super.done ? '1' : '0',
                super.description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
