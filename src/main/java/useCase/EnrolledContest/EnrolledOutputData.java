package useCase.EnrolledContest;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Encapsulates the output data from the Enrolled Use Case Interactor.
 * This class is responsible for holding the data related to enrolled contests that will be displayed to the user.
 * It contains detailed information about each contest, including participant details, portfolio information,
 * and contest specifics such as title, description, start and end dates, industry category, and time left.
 *
 * The data contained in this class is typically used to update the view layer of the application, providing
 * users with up-to-date information about contests they are enrolled in.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
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
    private final int timeLeft;

    /**
     * Constructs an EnrolledOutputData object with specific details of an enrolled contest.
     *
     * @param opponents The list of opponents in the contest.
     * @param portfolios A complex HashMap representing the portfolios of each contestant.
     * @param startDate The start date of the contest.
     * @param endDate The end date of the contest.
     * @param title The title of the contest.
     * @param description The description of the contest.
     * @param contestId The unique identifier for the contest.
     * @param industry The industry category of the contest.
     * @param username The username of the current user.
     * @param timeLeft The time remaining for the contest.
     */
    public EnrolledOutputData(List<String> opponents,
                              HashMap<String, HashMap<String, HashMap<String, String>>> portfolios,
                              LocalDateTime startDate,
                              LocalDateTime endDate,
                              String title,
                              String description,
                              String contestId,
                              String industry,
                              String username,
                              int timeLeft) {
        this.opponents = opponents;
        this.portfolios = portfolios;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.contestId = contestId;
        this.industry = industry;
        this.username = username;
        this.timeLeft = timeLeft;
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

    public int getTimeLeft() {
        return timeLeft;
    }
}
