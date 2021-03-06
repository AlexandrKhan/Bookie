package edu.epam.bookie.tag;

import edu.epam.bookie.model.StatusType;
import edu.epam.bookie.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User sorting tags
 */
public class UserSorting {
    private UserSorting() {
    }

    /**
     * Not verified users
     *
     * @param users list of users
     * @return filtered users
     */
    public static List<User> selectNotVerifiedUsers(List<User> users) {
        return users.stream()
                .filter(u -> u.getStatusType().equals(StatusType.valueOf("ACTIVATED")))
                .collect(Collectors.toList());
    }

    /**
     * Banned users
     *
     * @param users list of users
     * @return filtered users
     */
    public static List<User> selectBannedUsers(List<User> users) {
        return  users.stream()
                .filter(u -> u.getStatusType().equals(StatusType.valueOf("BLOCKED")))
                .collect(Collectors.toList());
    }
}
