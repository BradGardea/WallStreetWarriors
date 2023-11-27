package main.java.use_case.Enrolled;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Responsible in handling any data outputted from the Enrolled Use Case Interactor.
 *
 * Specifically, this would be the contest details which will be displayed by the view to the user.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledOutputData {
    private final List<String> opponents;
    private final Map<String, Float> userStocks; // LinkedHashMap
    private final Map<String, Map<String, Float>> opponentStocks; // LinkedHashMap
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String title;
    private final String description;
    private final String contestId;
    private final String industry;

    public EnrolledOutputData(List<String> opponents, Map<String, Float> userStocks, Map<String, Map<String, Float>> opponentStocks, LocalDateTime startDate, LocalDateTime endDate, String title, String description, String contestId, String industry) {
        this.opponents = opponents;
        this.userStocks = userStocks;
        this.opponentStocks = opponentStocks;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.contestId = contestId;
        this.industry = industry;
    }

    // Getters

    public List<String> getOpponents() {
        return opponents;
    }

    public Map<String, Float> getUserStocks() {
        return userStocks;
    }

    public Map<String, Map<String, Float>> getOpponentStocks() {
        return opponentStocks;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContestId() {
        return contestId;
    }

    public String getIndustry() {
        return industry;
    }
}
