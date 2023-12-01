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
    private final HashMap<String, HashMap<String, String>> userPortfolio;
    private final HashMap<String, HashMap<String, HashMap<String, String>>> opponentPortfolios;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String title;
    private final String description;
    private final String contestId;
    private final String industry;

    public EnrolledOutputData(List<String> opponents,
                              HashMap<String, HashMap<String, String>> userPortfolio,
                              HashMap<String, HashMap<String, HashMap<String, String>>> opponentPortfolios,
                              LocalDateTime startDate,
                              LocalDateTime endDate,
                              String title,
                              String description,
                              String contestId,
                              String industry) {
        this.opponents = opponents;
        this.userPortfolio = userPortfolio;
        this.opponentPortfolios = opponentPortfolios;
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

    public HashMap<String, HashMap<String, String>> getUserPortfolio() {
        return userPortfolio;
    }

    public HashMap<String, HashMap<String, HashMap<String, String>>> getOpponentPortfolios() {
        return opponentPortfolios;
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
