package xizi;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        out.println(DIVIDER);
        out.println("Hello! I'm Xizi.");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    public void printErrorMessage(String message) {
        out.println(DIVIDER);
        out.println("OOPS!!! " + message);
        out.println(DIVIDER);
    }

    public void showGoodbyeMessage() {
        out.println(DIVIDER);
        out.println("Goodbye! Have a great day!");
        out.println(DIVIDER);
    }

    public String readUserInput() {
        try {
            return in.nextLine().trim();
        } catch (Exception e) {
            printErrorMessage("Error reading input. Please try again.");
            return "";
        }
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    public void printMessage(String message) {
        out.println(message);
    }

    public void printHelp() {
        out.println(DIVIDER);
        out.println("Here are the available commands and their formats:");
        out.println();

        // Display command formats and examples
        printCommand("1. list",
                "- Displays all tasks in your list.");

        printCommand("2. todo <task_description>",
                "- Adds a new 'todo' task.",
                "  Example: todo read a book");

        printCommand("3. deadline <task_description> /by <deadline>",
                "- Adds a new 'deadline' task.",
                "  Example: deadline submit report /by 20/08/2024 1800");

        printCommand("4. event <task_description> /from <start_time> /to <end_time>",
                "- Adds a new 'event' task.",
                "  Example: event project meeting /from 15/08/2024 1400 /to 15/08/2024 1600");

        printCommand("5. mark <task_number>",
                "- Marks the specified task as completed.",
                "  Example: mark 3");

        printCommand("6. unmark <task_number>",
                "- Unmarks the specified task as not completed.",
                "  Example: unmark 3");

        printCommand("7. delete <task_number>",
                "- Deletes the specified task.",
                "  Example: delete 3");

        printCommand("8. bye",
                "- Exits the program.");

        printCommand("9. help",
                "- Displays this help message.");

        printCommand("10. list on <date> <time>",
                "- Displays all tasks scheduled for a specific date and time.",
                "  Example: list on 15/08/2024 1400");

        out.println(DIVIDER);
    }

    private void printCommand(String command, String description) {
        out.println(command);
        out.println("  " + description);
        out.println();
    }

    private void printCommand(String command, String description, String example) {
        out.println(command);
        out.println("  " + description);
        out.println(example);
        out.println();
    }

    public void handleList(TaskList actions) {
        this.showLine();
        out.println("Here are the tasks in your list:");
        actions.printActions();
        this.showLine();
    }
}
