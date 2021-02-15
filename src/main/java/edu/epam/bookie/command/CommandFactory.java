package edu.epam.bookie.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance(){
        return instance;
    }

    public static Optional<Command> defineCommand(HttpServletRequest request) {
        String value = request.getParameter(RequestParameter.COMMAND);

        if (value != null && !value.isEmpty()) {
            return Stream.of(CommandType.values())
                    .filter(e -> e.toString().equals(value.toUpperCase()))
                    .map(CommandType::getCommand).findAny();
        } else {
            return Optional.empty();
        }
    }
}
