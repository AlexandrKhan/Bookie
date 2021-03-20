package edu.epam.bookie.command;

import edu.epam.bookie.command.impl.*;
import edu.epam.bookie.command.impl.ToLoginPageCommand;
import edu.epam.bookie.command.impl.ToRegistrationPageCommand;

public enum CommandType {
    HOME(new HomeCommand()),
    LOGOUT(new LogoutCommand()),
    LOGIN(new LoginCommand()),
    LANGUAGE(new LanguageCommand()),
    TO_LOGIN_PAGE_COMMAND(new ToLoginPageCommand()),
    TO_REGISTER_PAGE_COMMAND(new ToRegistrationPageCommand()),
    TO_CREATE_MATCH_COMMAND(new ToAddMatchCommand()),
    TO_CASH_IN_COMMAND(new ToCashInCommand()),
    TO_MESSAGES_COMMAND(new ToMessagesCommand()),
    REGISTRATION(new RegistrationCommand()),
    FILE_UPLOAD(new FileUploadCommand()),
    ADMIN_PANEL(new AdminPanelCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    MATCH_LIST(new MatchListCommand()),
    ADD_MATCH(new AddMatchCommand()),
    PLACE_BET(new PlaceBetCommand()),
    TO_PLACE_BET_COMMAND(new ToPlaceBetCommand()),
    CASH_IN(new CashInCommand()),
    TO_UPDATE_MATCH_COMMAND(new ToUpdateMatchCommand()),
    UPDATE_MATCH(new UpdateMatchDateCommand()),
    PERSONAL_CABINET(new PersonalCabinetCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
