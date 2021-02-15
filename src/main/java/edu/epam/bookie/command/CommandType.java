package edu.epam.bookie.command;

import edu.epam.bookie.command.impl.*;
import edu.epam.bookie.command.pagecommand.ToLoginPageCommand;
import edu.epam.bookie.command.pagecommand.ToRegistrationPageCommand;

public enum CommandType {
    HOME(new HomeCommand()),
    LOGOUT(new LogoutCommand()),
    LOGIN(new LoginCommand()),
    LANGUAGE(new LanguageCommand()),
    TO_LOGIN_PAGE_COMMAND(new ToLoginPageCommand()),
    TO_REGISTER_PAGE_COMMAND(new ToRegistrationPageCommand()),
    REGISTRATION(new RegistrationCommand()),
    FILE_UPLOAD(new FileUploadCommand()),
    ADMIN_PANEL(new AdminPanelCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
