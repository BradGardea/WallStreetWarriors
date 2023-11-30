package use_case.Enrolled;

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
        String username = enrolledInputData.getUsername();
        String contestId = enrolledInputData.getContestId();

        // Get Contest object by ID
        Contest enrolledContest = userDataAccessObject.getEntity(Contest.class, "Contests", contestId);

        // Get base data from object
        ArrayList<User> members = enrolledContest.getMembers();
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = enrolledContest.getPortfolios(); // LinkedHashMap
        Timestamp startTime = enrolledContest.getStartTime();
        Timestamp endTime = enrolledContest.getEndTime();
        String industry = enrolledContest.getIndustry();
        String title = enrolledContest.getTitle();
        String description = enrolledContest.getDescription();
        HashMap<String, HashMap<String, String>> userPortfolio = portfolios.get(username);

        // Convert to OutputData format
        List<String> opponents = new LinkedList<>();
        HashMap<String, HashMap<String, HashMap<String, String>>> opponentPortfolios = new HashMap<>();
        for (User user : members) {
            String name = user.getUsername();
            if (!Objects.equals(name, username)) {
                opponents.add(name);
                HashMap<String, HashMap<String, String>> namePortofolio = portfolios.get(name);
                opponentPortfolios.put(name, namePortofolio);
            }
        }

        // Convert time to OutputData format
        LocalDateTime startDate = startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = endTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        EnrolledOutputData enrolledOutputData = new EnrolledOutputData(
                opponents,
                userPortfolio,
                opponentPortfolios,
                startDate,
                endDate,
                title,
                description,
                contestId,
                industry);
        enrolledPresenter.prepareSuccessView(enrolledOutputData);
    }

}
