package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDao extends BaseDao<Message> {
    /**
     * Select all messages of this user
     *
     * @param id user is
     * @return list of messages
     * @throws DaoException dao exception
     */
    Optional<List<Message>> findAllMessagesOfUser(int id) throws DaoException;
}
