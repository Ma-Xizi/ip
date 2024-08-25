package xizi.command;
import xizi.Deadline;
import xizi.XiziException;
import xizi.Parser;
import xizi.TaskList;
import xizi.Storage;
import xizi.Ui;
import xizi.Task;

import java.io.IOException;
import java.util.regex.Matcher;
import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    private final String taskDescription;
    private final LocalDateTime deadline;

    public DeadlineCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.DEADLINE.matcher(userInput);
        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            String deadlineStr = matcher.group(2).trim();
            if (taskDescription.isEmpty() || deadlineStr.isEmpty()) {
                throw new XiziException("The description or time of a deadline cannot be empty.");
            }
            deadline = Parser.parseDateTime(deadlineStr);
        } else {
            throw new XiziException("Invalid deadline command format. Use: deadline <description> /by <date time>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Task task = new Deadline(taskDescription, deadline);
        actions.addTask(task);
        storage.appendTask(task);
        ui.showLine();
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + task);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}
