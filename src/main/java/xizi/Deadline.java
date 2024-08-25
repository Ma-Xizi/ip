package xizi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime ddl;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.ddl = deadline;
    }

    public LocalDateTime getDdl(){
        return this.ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl.format(OUTPUT_DATE_FORMAT) + ")";
    }

    @Override
    public String toFileFormat(){
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.name, this.ddl.format(OUTPUT_DATE_FORMAT));
    }


}
