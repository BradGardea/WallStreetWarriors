package useCase.EnrolledContest;

import api.Credentials;
import firebaseDataAccess.FirebaseDataAccess;
import api.ApiCall;
import firebaseDataAccess.IDataAccess;
import com.google.cloud.Timestamp;
import entity.Contest;
import entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * The use case interactor for enrolled contests in the application.
 * This class is responsible for handling the business logic associated with retrieving and processing
 * information about contests in which a user is enrolled.
 *
 * The interactor retrieves contest data based on input, processes it, and forwards it to the enrolled presenter
 * for display in the view. It also provides functionality to mark contests as completed for a user.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
 */
public class EnrolledInteractor implements EnrolledInputBoundary {
    final IDataAccess userDataAccessObject;
    final EnrolledOutputBoundary enrolledPresenter;

    /**
     * Constructs an EnrolledInteractor with specified data access object and presenter.
     *
     * @param userDataAccessInterface Data access object for retrieving and updating contest data.
     * @param enrolledOutputBoundary Output boundary (presenter) to send the processed data to.
     */
    public EnrolledInteractor(IDataAccess userDataAccessInterface,
                              EnrolledOutputBoundary enrolledOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.enrolledPresenter = enrolledOutputBoundary;
    }

    /**
     * Executes the use case logic for retrieving enrolled contest data.
     * Retrieves data based on input, processes it, and forwards it to the presenter.
     *
     * @param enrolledInputData Input data containing details necessary for retrieving contest data.
     */
    @Override
    public void execute(EnrolledInputData enrolledInputData) {
        enrolledPresenter.prepareSuccessView(retrieveData(enrolledInputData));
    }

    /**
     * Retrieves data from the specified EnrolledInputData object.
     * This method processes the contest data and prepares it for presentation.
     * Makes an ApiCall from finazon.
     *
     * @param  enrolledInputData   The EnrolledInputData object containing the necessary data.
     * @return                     The EnrolledOutputData object containing the retrieved and processed data.
     */
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

        int timeLeft = (int) (endDate.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()
                - Instant.now().getEpochSecond());

        for (User u : members) {
            String user = u.getUsername();
            System.out.println(user);
        for (String s : portfolios.get(user).keySet()) {

            String quantity = portfolios.get(user).get(s).get("Quantity");

            String closePrice = ApiCall.getClosePrice(s, Credentials.apiKey);

            if (closePrice == null) {
                closePrice = "0";
            }

            if (s.equals("Cash")) {closePrice = "1";}

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
                enrolledInputData.getUsername(),
                timeLeft);
    }

    /**
     * Marks a contest as completed for a given enrolled user.
     * This method updates the contest and user status in the storage to reflect completion.
     *
     * @param  enrolledInputData  The input data for the enrolled contest.
     * @return                    True if the contest was marked as completed successfully, false otherwise.
     */
    public boolean markContestCompleted(EnrolledInputData enrolledInputData) {
        var enrolledContest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", enrolledInputData.getContestId());
        var user = FirebaseDataAccess.getInstance().getEntity(User.class, "Users", enrolledInputData.getUsername());

        ArrayList<User> members = enrolledContest.getMembers();
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = enrolledContest.getPortfolios();

        if (user != null && enrolledContest != null){
            for (User u : members) {
                String username = u.getUsername();
                System.out.println(username);
                for (String s : portfolios.get(username).keySet()) {

                    String quantity = portfolios.get(username).get(s).get("Quantity");

                    String closePrice = ApiCall.getClosePrice(s, Credentials.apiKey);
                    if (closePrice == null) {
                        closePrice = "0";
                    }

                    if (s.equals("Cash")) {closePrice = "1";}

                    String value = String.valueOf(Float.parseFloat(quantity) * Float.parseFloat(closePrice));

                    portfolios.get(username).get(s).put("Close Price", closePrice);
                    portfolios.get(username).get(s).put("Value", value);

                } }

            userDataAccessObject.setOrUpdateEntity(enrolledContest, "Contests", enrolledInputData.getContestId());

            user.removeEnrolledContest(enrolledInputData.getContestId());
            user.addCompletedContest(enrolledInputData.getContestId());

            enrolledContest.updateInStorage();
            user.updateInStorage();
            return true;
        }
        return false;
    }

}
