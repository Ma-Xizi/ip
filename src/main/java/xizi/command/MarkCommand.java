package xizi.command;

import xizi.*;

import java.io.IOException;
import java.util.regex.Matcher;

public class MarkCommand implements Command {
    private final int taskNumber;

    public MarkCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.MARK.matcher(userInput);
        if (matcher.matches()) {
            taskNumber = Integer.parseInt(matcher.group(1)) - 1;
        } else {
            throw new XiziException("Invalid mark command format. Use: mark <task number>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Parser.validateTaskNumber(taskNumber, actions);
        ui.showLine();
        ui.printMessage("Nice! I've marked this task as done: ");
        ui.printMessage(actions.markTask(taskNumber));
        ui.showLine();
        storage.saveTasks(actions.getItems());
    }
}
