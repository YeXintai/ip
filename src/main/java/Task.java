public abstract class Task {
    boolean done;
    String description;

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

    @Override
    public String toString() {
        if (done) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
