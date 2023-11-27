package main.java.interface_adapters.Enrolled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledState {
    private List<String> opponents = new ArrayList<String>();
    private Map<String, Float> userStocks = new LinkedHashMap<String, Float>(); // LinkedHashMap
    private Map<String, Map<String, Float>> opponentStocks = null; // LinkedHashMap
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();

    private String title = "";
    private String description = "";
    private String contestId = "";
    private String industry = "";

    public EnrolledState(EnrolledState copy) {
        opponents = copy.opponents;
        userStocks = copy.userStocks;
        opponentStocks = copy.opponentStocks;
        startDate = copy.startDate;
        endDate = copy.endDate;
        title = copy.title;
        description = copy.description;
        contestId = copy.contestId;
        industry = copy.industry;
    }

    public EnrolledState() {}

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

    public void setOpponents(List<String> opponents) {
        this.opponents = opponents;
    }

    public void setUserStocks(Map<String, Float> userStocks) {
        this.userStocks = userStocks;
    }

    public void setOpponentStocks(Map<String, Map<String, Float>> opponentStocks) {
        this.opponentStocks = opponentStocks;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
