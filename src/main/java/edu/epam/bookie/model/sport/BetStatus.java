package edu.epam.bookie.model.sport;

/**
 * Status of bet
 * Before match - NOT_STARTED
 * After match - WON or LOST, depends on match result
 */
public enum BetStatus {
    NOT_STARTED,
    WON,
    LOST
}
