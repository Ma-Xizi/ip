package xizi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom(){
        return this.from;
    }

    public LocalDateTime getTo(){
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_DATE_FORMAT) + " to: " + to.format(OUTPUT_DATE_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s", this.isDone ? 1 : 0, this.name, this.from.format(OUTPUT_DATE_FORMAT), this.to.format(OUTPUT_DATE_FORMAT));
    }


}
