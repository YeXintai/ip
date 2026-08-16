public class TodoTask extends Task {
    public TodoTask(boolean done, String description) {
        super(done, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
