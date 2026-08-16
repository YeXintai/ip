public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}