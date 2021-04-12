package edu.epam.bookie.command;

import edu.epam.bookie.command.impl.InvalidCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Gets command by name
     *
     * @param commandName command name
     * @return Command
     */
    public static Command defineCommand(String commandName) {
       Command command;
        try {
            command = CommandType.valueOf(commandName.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            command = new InvalidCommand();
        }
        return command;
    }

    /**
     * Gets command type by name
     *
     * @param commandName commandName
     * @return Command
     */
    public static CommandType getCommandType(String commandName) {
        CommandType command;
        try {
            command = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = CommandType.INVALID_COMMAND;
        }
        return command;
    }
}
