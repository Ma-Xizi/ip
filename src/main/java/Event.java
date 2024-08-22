public class Event extends Task{
    protected String from;
    protected String to;


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s", this.done ? 1 : 0, this.name, this.from, this.to);
    }


}
