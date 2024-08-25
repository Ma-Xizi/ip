package xizi;//https://nus-cs2103-ay2425s1.github.io/website/admin/standardsAndConventions.html

import xizi.command.Command;

import java.io.IOException;
import java.util.List;


public class Xizi {
    private static final String FILE_PATH = "./data/xizi.txt";
    private final Storage storage;
    private TaskList actions;
    private final Ui ui;
    private final Parser parser;

    public Xizi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        actions = new TaskList();
        parser = new Parser();

        try {
            List<Task> loadedTasks = storage.load();
            actions = new TaskList(loadedTasks);
        } catch (IOException | XiziException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Xizi(FILE_PATH).run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readUserInput();
                Command command = parser.parse(userInput);
                command.execute(actions, storage, ui);
                isRunning = !userInput.equals("bye");
            } catch (XiziException | IOException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }

    }



}
