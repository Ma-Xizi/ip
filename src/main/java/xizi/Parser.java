package xizi;

import xizi.command.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr, INPUT_DATE_FORMAT);
    }

    public static void validateTaskNumber(int taskNumber, TaskList actions) throws XiziException {
        if (taskNumber < 0 || taskNumber >= actions.getSize()) {
            throw new XiziException("The task number does not exist. You have " + actions.getSize() + " tasks in total.");
        }
    }


    public Matcher matchCommand(String userInput, CommandType commandType) {
        return commandType.matcher(userInput);
    }

    public Command parse(String userInput) throws IOException, XiziException {
        CommandType commandType = CommandType.fromInput(userInput);

        switch (commandType) {
        case DELETE:
            return new DeleteCommand(userInput);
        case MARK:
            return new MarkCommand(userInput);
        case UNMARK:
            return new UnmarkCommand(userInput);
        case TODO:
            return new TodoCommand(userInput);
        case DEADLINE:
            return new DeadlineCommand(userInput);
        case EVENT:
            return new EventCommand(userInput);
        case LIST:
            return new ListCommand();
        case BYE:
            return new ByeCommand();
        case LIST_ON:
            return new ListOnCommand(userInput);
        case HELP:
            return new HelpCommand();
        default:
            throw new XiziException("Sorry, I didn't understand that command. Type help for all available commands and format.");
        }
    }


}
