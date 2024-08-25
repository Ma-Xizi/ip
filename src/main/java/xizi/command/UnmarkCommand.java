package xizi.command;

import xizi.*;

import java.io.IOException;
import java.util.regex.Matcher;

public class UnmarkCommand implements Command {
    private final int taskNumber;

    public UnmarkCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.UNMARK.matcher(userInput);
        if (matcher.matches()) {
            taskNumber = Integer.parseInt(matcher.group(1)) - 1;
        } else {
            throw new XiziException("Invalid unmark command format. Use: unmark <task number>");
        }
    }

    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Parser.validateTaskNumber(taskNumber, actions);
        ui.showLine();
        ui.printMessage("OK, I've marked this task as not done yet:");
        ui.printMessage(actions.unmarkTask(taskNumber));
        ui.showLine();
        storage.saveTasks(actions.getItems());
    }
}
