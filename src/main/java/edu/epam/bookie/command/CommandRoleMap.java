package edu.epam.bookie.command;

import edu.epam.bookie.model.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

/**
 * Map of command as key and role of user as value,
 * allowing execution of this command only to users with declared roles
 */

public class CommandRoleMap {
    private static final CommandRoleMap INSTANCE = new CommandRoleMap();
    private static final EnumMap<CommandType, List<Role>> ROLE_MAP;

    static {
        ROLE_MAP = new EnumMap<>(CommandType.class);
        ROLE_MAP.put(CommandType.ADMIN_PANEL, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.UPDATE_MATCH, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.CREATE_MATCH, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.ADD_MATCH, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.BLOCK_USER, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.VERIFY_ACCOUNT, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.UNBLOCK_USER, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.LOGIN, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.PLACE_BET, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.PERSONAL_CABINET, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.MESSAGES, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.CASH_IN, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.REGISTRATION, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.ACTIVATE_ACCOUNT, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.INVALID_COMMAND, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.FILE_UPLOAD, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.SEARCH_MATCHES, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.LANGUAGE, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.AUTHORISATION, Collections.singletonList(Role.GUEST));
        ROLE_MAP.put(CommandType.LOGOUT, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.MATCH_LIST, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
    }

    private CommandRoleMap() {
    }

    public static CommandRoleMap getInstance() {
        return INSTANCE;
    }

    public boolean hasRole(CommandType commandType, Role role) {
        return ROLE_MAP.get(commandType).contains(role);
    }
}
