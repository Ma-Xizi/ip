package xizi.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Enums for command types
public enum CommandType {
    MARK("^mark (\\d+)$"),
    UNMARK("^unmark (\\d+)$"),
    DELETE("^delete\\s+(\\d+)$"),
    TODO("^todo\\s*(.*)$"),
    DEADLINE("^deadline\\s*(.*?)\\s*/by\\s*(.*?)$"),
    EVENT("^event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$"),
    LIST("^list$"),
    BYE("^bye$"),
    HELP("^help$"),
    LIST_ON("^list on (\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})$"),
    UNKNOWN("");


    private final Pattern pattern;

    CommandType(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public Matcher matcher(String input) {
        return pattern.matcher(input);
    }

    public static CommandType fromInput(String input) {
        for (CommandType type : values()) {
            if (type.matcher(input).matches()) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
