package main.java.use_case.Enrolled;

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
    private final String startDate;
    private final String endDate;


    public EnrolledOutputData(List<String> opponents, Map<String, Float> userStocks, Map<String, Map<String, Float>> opponentStocks, String startDate, String endDate) {
        this.opponents = opponents;
        this.userStocks = userStocks;
        this.opponentStocks = opponentStocks;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
