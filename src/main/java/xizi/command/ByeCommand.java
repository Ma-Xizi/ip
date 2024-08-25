package xizi.command;

import xizi.Storage;
import xizi.TaskList;
import xizi.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showGoodbyeMessage();
    }
}

