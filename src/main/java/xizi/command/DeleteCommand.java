package xizi.command;

import xizi.XiziException;
import xizi.Parser;
import xizi.TaskList;
import xizi.Storage;
import xizi.Ui;
import xizi.Task;

import java.io.IOException;
import java.util.regex.Matcher;

public class DeleteCommand implements Command {
    private final int taskNumber;

    public DeleteCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.DELETE.matcher(userInput);
        if (matcher.matches()) {
            taskNumber = Integer.parseInt(matcher.group(1)) - 1;
        } else {
            throw new XiziException("Invalid delete command format. Use: delete <task number>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Parser.validateTaskNumber(taskNumber, actions);
        Task deleted = actions.deleteTask(taskNumber);
        storage.saveTasks(actions.getItems());
        ui.showLine();
        ui.printMessage("Noted. I've removed this task:");
        ui.printMessage("  " + deleted);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}

