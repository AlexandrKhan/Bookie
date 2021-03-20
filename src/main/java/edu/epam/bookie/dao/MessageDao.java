package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDao extends BaseDao<Message> {
    Optional<List<Message>> findAllMessagesOfUser(int id) throws DaoException;
}
