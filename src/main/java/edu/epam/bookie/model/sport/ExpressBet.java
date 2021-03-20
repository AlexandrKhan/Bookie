package edu.epam.bookie.model.sport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpressBet {
    private int id;
    private int userId;
    private Map<Long, Result> matches = new HashMap<>(); //?
    private LocalDate betDate;
    private LocalTime betTime;
    private BigDecimal betAmount;
    private BigDecimal betCoeff;
    private BetStatus betStatus;
}
