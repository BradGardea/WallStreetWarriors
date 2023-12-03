package use_case.Enrolled;

import FirebaseDataAccess.FirebaseDataAccess;
import com.google.cloud.Timestamp;
import entity.Contest;
import entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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
    final FirebaseDataAccess userDataAccessObject;
    final EnrolledOutputBoundary enrolledPresenter;

    public EnrolledInteractor(FirebaseDataAccess userDataAccessInterface,
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

        enrolledPresenter.prepareSuccessView(retrieveData(enrolledInputData));
    }

    public EnrolledOutputData retrieveData(EnrolledInputData enrolledInputData) {
        String username = enrolledInputData.getUsername();
        String contestId = enrolledInputData.getContestId();

        // Get Contest object by ID
        Contest enrolledContest = userDataAccessObject.getEntity(Contest.class, "Contests", contestId);

        // Get base data from object
        ArrayList<User> members = enrolledContest.getMembers();
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = enrolledContest.getPortfolios();
        Timestamp startTime = enrolledContest.getStartTime();
        Timestamp endTime = enrolledContest.getEndTime();
        String industry = enrolledContest.getIndustry();
        String title = enrolledContest.getTitle();
        String description = enrolledContest.getDescription();

        // Convert to OutputData format
        List<String> opponents = new LinkedList<>();
        for (User user : members) {
            String name = user.getUsername();
            if (Objects.equals(name, username)) {
                continue;
            }
            opponents.add(name);
        }

        // Convert time to OutputData format
        LocalDateTime startDate = startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = endTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        EnrolledOutputData enrolledOutputData = new EnrolledOutputData(
                opponents,
                portfolios,
                startDate,
                endDate,
                title,
                description,
                contestId,
                industry,
                username);

        return enrolledOutputData;
    }

}
