package use_case.Enrolled;

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
    private final HashMap<String, HashMap<String, HashMap<String, String>>> portfolios;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String title;
    private final String description;
    private final String contestId;
    private final String industry;
    private final String username;

    public EnrolledOutputData(List<String> opponents,
                              HashMap<String, HashMap<String, HashMap<String, String>>> portfolios,
                              LocalDateTime startDate,
                              LocalDateTime endDate,
                              String title,
                              String description,
                              String contestId,
                              String industry,
                              String username) {
        this.opponents = opponents;
        this.portfolios = portfolios;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.contestId = contestId;
        this.industry = industry;
        this.username = username;
    }

    // Getters

    public List<String> getOpponents() {
        return opponents;
    }

    public String getUsername() {
        return username;
    }

    public HashMap<String, HashMap<String, HashMap<String, String>>> getPortfolios() {
        return portfolios;
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
