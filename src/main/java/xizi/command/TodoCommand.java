package xizi.command;

import xizi.*;

import java.io.IOException;
import java.util.regex.Matcher;

public class TodoCommand implements Command {
    private final String taskDescription;

    public TodoCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.TODO.matcher(userInput);
        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            if (taskDescription.isEmpty()) {
                throw new XiziException("The description of a todo cannot be empty.");
            }
        } else {
            throw new XiziException("Invalid todo command format. Use: todo <task description>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Task task = new Todo(taskDescription);
        actions.addTask(task);
        storage.appendTask(task);
        ui.showLine();
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + task);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}

