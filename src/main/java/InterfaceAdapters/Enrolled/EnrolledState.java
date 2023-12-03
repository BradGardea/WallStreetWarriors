package InterfaceAdapters.Enrolled;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Object responsible to store some data for the View.
 * State of view is EnrolledState.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
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
    }

    public String getUsername() {
        return username;
    }

    public EnrolledState() {}

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
