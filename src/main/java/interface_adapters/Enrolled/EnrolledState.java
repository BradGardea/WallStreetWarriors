package interface_adapters.Enrolled;

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
    private List<String> opponents;
    private HashMap<String, HashMap<String, String>> userPortfolio;
    private HashMap<String, HashMap<String, HashMap<String, String>>> opponentPortfolios;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String description;
    private String contestId;
    private String industry;

    public EnrolledState(EnrolledState copy) {
        opponents = copy.opponents;
        userPortfolio = copy.userPortfolio;
        opponentPortfolios = copy.opponentPortfolios;
        startDate = copy.startDate;
        endDate = copy.endDate;
        title = copy.title;
        description = copy.description;
        contestId = copy.contestId;
        industry = copy.industry;
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

    public void setUserPortfolio(HashMap<String, HashMap<String, String>> userPortfolio) {
        this.userPortfolio = userPortfolio;
    }

    public void setOpponentPortfolios(HashMap<String, HashMap<String, HashMap<String, String>>> opponentPortfolios) {
        this.opponentPortfolios = opponentPortfolios;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
