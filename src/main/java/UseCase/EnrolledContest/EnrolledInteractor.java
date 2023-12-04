package UseCase.EnrolledContest;

import Api.ApiCall;
import Api.Credentials;
import FirebaseDataAccess.FirebaseDataAccess;
import FirebaseDataAccess.IDataAccess;
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
    final IDataAccess userDataAccessObject;
    final EnrolledOutputBoundary enrolledPresenter;
    private String username = null;
    private String contestId = null;

    public EnrolledInteractor(IDataAccess userDataAccessInterface,
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
        this.username = enrolledInputData.getUsername();
        this.contestId = enrolledInputData.getContestId();

        enrolledPresenter.prepareSuccessView(retrieveData(enrolledInputData));
    }

    public EnrolledOutputData retrieveData(EnrolledInputData enrolledInputData) {

        // Get Contest object by ID

        Contest enrolledContest = userDataAccessObject.getEntity(Contest.class, "Contests", enrolledInputData.getContestId());

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
            if (Objects.equals(name, enrolledInputData.getUsername())) {
                continue;
            }
            opponents.add(name);
        }

        // Convert time to OutputData format
        LocalDateTime startDate = startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = endTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        for (User u : members) {
            String user = u.getUsername();
        for (String s : portfolios.get(user).keySet()) {

            String quantity = portfolios.get(user).get(s).get("Quantity");

            String closePrice = "0";
            try {
                closePrice = ApiCall.getClosePrice(s, Credentials.apiKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (closePrice == null) {
                closePrice = "0";
            }

            String value = String.valueOf(Float.parseFloat(quantity) * Float.parseFloat(closePrice));

            portfolios.get(user).get(s).put("Close Price", closePrice);
            portfolios.get(user).get(s).put("Value", value);

        } }

        userDataAccessObject.setOrUpdateEntity(enrolledContest, "Contests", enrolledInputData.getContestId());

        return new EnrolledOutputData(
                opponents,
                portfolios,
                startDate,
                endDate,
                title,
                description,
                enrolledInputData.getContestId(),
                industry,
                enrolledInputData.getUsername());
    }

    public boolean markContestCompleted(){
        var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", this.contestId);
        var user = FirebaseDataAccess.getInstance().getEntity(User.class, "Users", this.username);

        if (user != null && contest != null){
            user.removeEnrolledContest(this.contestId);
            user.addCompletedContest(this.contestId);

            contest.updateInStorage();
            user.updateInStorage();
            return true;
        }
        return false;
    }

}
