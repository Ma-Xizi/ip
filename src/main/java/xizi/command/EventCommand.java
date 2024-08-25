package xizi.command;

import xizi.Event;
import xizi.XiziException;
import xizi.Parser;
import xizi.TaskList;
import xizi.Storage;
import xizi.Ui;
import xizi.Task;

import java.io.IOException;
import java.util.regex.Matcher;
import java.time.LocalDateTime;

public class EventCommand implements Command {
    private final String taskDescription;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.EVENT.matcher(userInput);
        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            String fromStr = matcher.group(2).trim();
            String toStr = matcher.group(3).trim();
            if (taskDescription.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                throw new XiziException("The description or time of an event cannot be empty.");
            }
            from = Parser.parseDateTime(fromStr);
            to = Parser.parseDateTime(toStr);
        } else {
            throw new XiziException("Invalid event command format. Use: event <description> /from <start> /to <end>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Task task = new Event(taskDescription, from, to);
        actions.addTask(task);
        storage.appendTask(task);
        ui.showLine();
        ui.printMessage("Got it. I've added this event:");
        ui.printMessage("  " + task);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}

