package main.java.use_case.Enrolled;

/**
 * Houses the UUID of a certain selected StockContest object from the user's page view.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledInputData {
    final private String uuid;

    public EnrolledInputData(String uuid) {
        this.uuid = uuid;
    }

    String getUuid() {return uuid;}
}
