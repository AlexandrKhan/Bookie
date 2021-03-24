package edu.epam.bookie.command;

import edu.epam.bookie.model.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class CommandRoleMap {
    private static CommandRoleMap INSTANCE = new CommandRoleMap();
    private static final EnumMap<CommandType, List<Role>> ROLE_MAP;

    static {
        ROLE_MAP = new EnumMap<>(CommandType.class);
        ROLE_MAP.put(CommandType.ADMIN_PANEL, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.UPDATE_MATCH, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_CREATE_MATCH_COMMAND, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.ADD_MATCH, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.BLOCK_USER, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.UNBLOCK_USER, Collections.singletonList(Role.ADMIN));
        ROLE_MAP.put(CommandType.LOGIN, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.PLACE_BET, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.PERSONAL_CABINET, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.TO_PLACE_BET_COMMAND, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.TO_MESSAGES_COMMAND, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.CASH_IN, Arrays.asList(Role.ADMIN, Role.USER));
        ROLE_MAP.put(CommandType.REGISTRATION, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.ACTIVATE_ACCOUNT, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.FILE_UPLOAD, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.HOME, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.SEARCH_MATCHES, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.LANGUAGE, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.TO_LOGIN_PAGE_COMMAND, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.TO_REGISTER_PAGE_COMMAND, Arrays.asList(Role.ADMIN, Role.USER, Role.GUEST));
        ROLE_MAP.put(CommandType.TO_CASH_IN_COMMAND, Arrays.asList(Role.ADMIN, Role.USER));
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
