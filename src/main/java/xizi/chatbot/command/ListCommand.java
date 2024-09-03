package xizi.chatbot.command;

import xizi.chatbot.Storage;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.Ui;

import java.util.List;

/**
 * Represents a command to list all tasks currently in the task list.
 * This command displays the tasks in the order they were added.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     * The tasks are presented to the user in a formatted list.
     *
     * @param actions The task list containing all tasks.
     * @param storage The storage handler (not used in this command).
     * @param ui      The user interface for interacting with the user.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showLine();
        ui.printMessage("Here are the tasks in your list:");
        int listSize = actions.getSize();
        List<Task> innerTasks = actions.getItems();
        for (int i = 1; i <= listSize; i++) {
            Task task = innerTasks.get(i - 1);
            String taskString = String.format("%d. %s", i, task.toString());
            ui.printMessage(taskString);
        }
        if (listSize == 0) {
            ui.printMessage("There are no tasks in the list now");
        }
        ui.showLine();
    }
}

