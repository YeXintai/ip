package sozius.task;

public abstract class Task {
    boolean done;
    String description;

    public Task(String description) {
        this(false, description);
    }
    public Task(boolean done, String description) {
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
    public String toUserString() {
        return String.format("[%c] %s",
                done ? 'X' : ' ',
                description);
    }
}
