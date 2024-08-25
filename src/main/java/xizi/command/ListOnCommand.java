package xizi.command;

import xizi.Deadline;
import xizi.Event;
import xizi.XiziException;
import xizi.Parser;
import xizi.TaskList;
import xizi.Storage;
import xizi.Ui;
import xizi.Task;

import java.time.LocalDateTime;
import java.util.regex.Matcher;

public class ListOnCommand implements Command {
    private final LocalDateTime date;

    public ListOnCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.LIST_ON.matcher(userInput);
        if (matcher.matches()) {
            String dateTimeStr =  matcher.group(1);
            this.date = Parser.parseDateTime(dateTimeStr);
        } else {
            throw new XiziException("Invalid list on command format. Use: list on <date>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showLine();
        boolean tasksFound = false;
        ui.printMessage("Here are the tasks on the particular day in your list:");
        try {
            for (Task task : actions.getItems()) {
                if (task instanceof Event) {
                    Event event = (Event) task;
                    if ((event.getFrom().isBefore(date) || event.getFrom().equals(date))&&
                                (event.getTo().isAfter(date) || event.getTo().equals(date))) {
                            ui.printMessage(event.toString());
                            tasksFound = true;
                        }
                    } else if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.getDdl().equals(date)) {
                            ui.printMessage(deadline.toString());
                            tasksFound = true;
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        if (!tasksFound) {
            ui.printMessage("No tasks found on this date.");
        }
        ui.showLine();
    }
}


