package xizi;// Do not use wildcard imports
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // Load tasks from the file
    public List<Task> load() throws IOException, XiziException {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks; // Return an empty task list if file doesn't exist
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        try  {
            String line = reader.readLine();
            while (line  != null) {
                Task task = getTask(line);
                line = reader.readLine();
                tasks.add(task);
            }
        } finally {

        }

        return tasks;
    }

    private static Task getTask(String line) throws XiziException {
        String[] parts = line.split(" \\| ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"); // same as output format in xizi class
        Task task;
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            LocalDateTime ddl = LocalDateTime.parse(parts[3], formatter);
            task = new Deadline(parts[2], ddl);
            break;
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
            LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
            task = new Event(parts[2], from, to);
            break;
        default:
            throw new XiziException("File data corrupted: Unknown task type.");
        }
        if (parts[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    // Save tasks to the file
    public void appendTask(Task task) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(task.toFileFormat());
            writer.newLine();

        }
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }


}




