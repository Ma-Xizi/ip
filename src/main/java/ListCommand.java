public class ListCommand implements Command {
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showLine();
        ui.printMessage("Here are the tasks in your list:");
        actions.printActions();
        ui.showLine();
    }
}

