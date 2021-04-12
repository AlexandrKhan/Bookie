package edu.epam.bookie.command;

import edu.epam.bookie.model.StatusType;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

/**
 * Map of command as key and status of user as value, allowing execution of this command only to users with declared status
 */

public class CommandStatusMap {
    private static CommandStatusMap INSTANCE = new CommandStatusMap();
    private static final EnumMap<CommandType, List<StatusType>> STATUS_MAP;

    static {
        STATUS_MAP = new EnumMap<>(CommandType.class);
        STATUS_MAP.put(CommandType.ADMIN_PANEL, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.UPDATE_MATCH, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.CREATE_MATCH, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.ADD_MATCH, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.BLOCK_USER, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.VERIFY_ACCOUNT, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.UNBLOCK_USER, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.PLACE_BET, Collections.singletonList(StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.CASH_IN, Collections.singletonList(StatusType.VERIFIED));

        STATUS_MAP.put(CommandType.ACTIVATE_ACCOUNT, Collections.singletonList(StatusType.NOT_ACTIVATED));
        STATUS_MAP.put(CommandType.FILE_UPLOAD, Collections.singletonList(StatusType.ACTIVATED));

        STATUS_MAP.put(CommandType.PERSONAL_CABINET, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.MESSAGES, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.LOGIN, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.REGISTRATION, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.INVALID_COMMAND, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.SEARCH_MATCHES, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.LANGUAGE, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.AUTHORISATION, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.LOGOUT, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
        STATUS_MAP.put(CommandType.MATCH_LIST, Arrays.asList(StatusType.ACTIVATED, StatusType.BLOCKED, StatusType.NOT_ACTIVATED, StatusType.VERIFIED));
    }

    private CommandStatusMap() {
    }

    public static CommandStatusMap getInstance() {
        return INSTANCE;
    }

    public boolean hasStatus(CommandType commandType, StatusType status) {
        return STATUS_MAP.get(commandType).contains(status);
    }
}
