package use_case.enrolled;

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

    private final String opponent;
    // TODO figure out how stock prices are gonna work - Stock Entity?
    private final Map<K, V> userStocks; // LinkedHashMap
    private final Map<K, V> opponentStocks; // LinkedHashMap
    private final String startDate; // TODO figure out time objects and how to do the clock thing
    private final String endDate; // TODO


    public EnrolledOutputData(String opponent, Map<K, V> userStocks, Map<K, V> opponentStocks, String startDate, String endDate) {
        this.opponent = opponent;
        this.userStocks = userStocks;
        this.opponentStocks = opponentStocks;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // TODO Update getters if necessary

    public String getOpponent() {
        return opponent;
    }

    public Map<K, V> getUserStocks() {
        return userStocks;
    }

    public Map<K, V> getOpponentStocks() {
        return opponentStocks;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
