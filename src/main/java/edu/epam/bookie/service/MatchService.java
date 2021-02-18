package edu.epam.bookie.service;

import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MatchService {
    List<Match> findAll() throws MatchServiceException;
    Match create(Team first, Team second, LocalDate date, LocalTime time) throws MatchServiceException;
}
