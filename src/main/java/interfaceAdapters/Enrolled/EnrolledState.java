package interfaceAdapters.Enrolled;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents the state of an enrolled contest view in the application.
 * This class encapsulates all the relevant data needed for displaying the details
 * of an enrolled contest, including information about the contest, participants, and portfolios.
 *
 * The EnrolledState class holds data such as the contest title, description, start and end dates,
 * user details, and portfolio information. It provides getters and setters for accessing and modifying
 * these properties.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
 */
public class EnrolledState {
    private String username;
    private List<String> opponents;
    private HashMap<String, HashMap<String, HashMap<String, String>>> portfolios;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String description;
    private String contestId;
    private String industry;
    private int timeLeft;

    /**
     * Constructs a copy of the given EnrolledState.
     *
     * @param copy The EnrolledState instance to copy.
     */
    public EnrolledState(EnrolledState copy) {
        username = copy.username;
        opponents = copy.opponents;
        portfolios = copy.portfolios;
        startDate = copy.startDate;
        endDate = copy.endDate;
        title = copy.title;
        description = copy.description;
        contestId = copy.contestId;
        industry = copy.industry;
        timeLeft = copy.timeLeft;
    }

    /**
     * Default constructor for EnrolledState.
     */
    public EnrolledState() {}

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getUsername() {
        return username;
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

    public List<String> getOpponents() {
        return opponents;
    }

    public HashMap<String, HashMap<String, HashMap<String, String>>> getPortfolios() {
        return portfolios;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setOpponents(List<String> opponents) {
        this.opponents = opponents;
    }

    public void setPortfolios(HashMap<String, HashMap<String, HashMap<String, String>>> portfolios) {
        this.portfolios = portfolios;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
