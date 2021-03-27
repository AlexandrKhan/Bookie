package edu.epam.bookie.command;

import edu.epam.bookie.command.impl.*;
import edu.epam.bookie.command.impl.AuthorisationCommand;

public enum CommandType {
    HOME(new HomeCommand()),
    LOGOUT(new LogoutCommand()),
    LOGIN(new LoginCommand()),
    LANGUAGE(new LanguageCommand()),
    AUTHORISATION(new AuthorisationCommand()),
    CREATE_MATCH(new ToAddMatchCommand()),
    MESSAGES(new ToMessagesCommand()),
    REGISTRATION(new RegistrationCommand()),
    FILE_UPLOAD(new FileUploadCommand()),
    ADMIN_PANEL(new AdminPanelCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    MATCH_LIST(new MatchListCommand()),
    ADD_MATCH(new AddMatchCommand()),
    PLACE_BET(new PlaceBetCommand()),
    CASH_IN(new CashInCommand()),
    UPDATE_MATCH(new UpdateMatchDateCommand()),
    PERSONAL_CABINET(new PersonalCabinetCommand()),
    SEARCH_MATCHES(new SearchMatchesByTeamCommand()),
    INVALID_COMMAND(new InvalidCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
