package main.java.use_case.Enrolled;

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
    final EnrolledUserDataAccessInterface userDataAccessObject;
    final EnrolledOutputBoundary enrolledPresenter;

    public EnrolledInteractor(EnrolledUserDataAccessInterface userDataAccessInterface,
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


    }

}
