package main.java.use_case.Enrolled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
/**
 * The Use Case Interactor for the Enrolled contest.
 *
 * Retrieves the enrolled contest necessary and sends it onto the view.
 *
 *
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledInteractor implements EnrolledInputBoundary {
    final FirebaseDataAccess.FirebaseDataAccess userDataAccessObject;
    final EnrolledOutputBoundary enrolledPresenter;

    public EnrolledInteractor(FirebaseDataAccess.FirebaseDataAccess userDataAccessInterface,
                              EnrolledOutputBoundary enrolledOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.enrolledPresenter = enrolledOutputBoundary;
    }

    /**
     * Retrieves the StockContest by UUID from the enrolledInputData and passes it onto the Presenter through
     * enrolledOutputData.
     *
     * Assumes that the contest exists with the given UUID as it must've been displayed as an enrolled contest in
     * the user's page view.
     *
     * @author Mikhail Skazhenyuk
     * @version 0.0
     */
    @Override
    public void execute(EnrolledInputData enrolledInputData) {
        // Get Contest by ID
        String uuid = enrolledInputData.getUuid();
        Contest enrolledContest = userDataAccessObject.getEntity(EnrolledContest, "contests", uuid);

        // TODO figure out how to convert the enrolled contest object to the raw data needed
        // TODO Add the following getters in enrolled contest
        List<String> opponents = enrolledContest.getOpponents();
        Map<String, Float> userStocks = enrolledContest.getUserStocks(); // LinkedHashMap
        Map<String, Map<String, Float>> opponentStocks = enrolledContest.getOpponentStocks(); // LinkedHashMap
        LocalDateTime startDate = enrolledContest.getStartDate();
        LocalDateTime endDate = enrolledContest.getEndDate();

        EnrolledOutputData enrolledOutputData = new EnrolledOutputData(  ); // TODO Put it in here properly
        enrolledPresenter.prepareSuccessView(enrolledOutputData);
    }

}
