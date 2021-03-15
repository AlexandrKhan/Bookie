package edu.epam.bookie.service;

import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    List<Match> findAll() throws MatchServiceException;
    Match findById(Long id) throws MatchServiceException;
    List<Match> findAllNotStartedMatches() throws MatchServiceException;
    Optional<Match> create(Team first, Team second, LocalDate date, LocalTime time, BigDecimal homeCoeff, BigDecimal drawCoeff, BigDecimal awayCoeff) throws MatchServiceException;
    boolean deleteById(Long id) throws MatchServiceException;
    boolean setGoalsResultAndOverMatchById(Long id) throws MatchServiceException;
    boolean updateMatchDate(Long id, LocalDate date, LocalTime time) throws MatchServiceException;
}
