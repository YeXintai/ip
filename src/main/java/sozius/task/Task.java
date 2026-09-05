package sozius.task;

/**
 * Task abstract class provides common methods for all Task classes
 */
public abstract class Task {
    private boolean done;
    private String description;

    protected Task(String description) {
        this(false, description);
    }
    protected Task(boolean done, String description) {
        this.done = done;
        this.description = description;
    }

    public boolean getDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public String getDescription() {
        return description;
    }

    public abstract String toFileString();
    /**
     * Returns string representation of the task.
     * @return The task string in user format
     */
    public String toUserString() {
        return String.format("[%c] %s",
                done ? 'X' : ' ',
                description);
    }
}
