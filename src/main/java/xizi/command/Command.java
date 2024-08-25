package xizi.command;

import xizi.Storage;
import xizi.TaskList;
import xizi.Ui;
import xizi.XiziException;

import java.io.IOException;

public interface Command {
    void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException;
}

