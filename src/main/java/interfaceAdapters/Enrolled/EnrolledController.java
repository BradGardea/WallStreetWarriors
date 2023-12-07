package interfaceAdapters.Enrolled;

import useCase.EnrolledContest.EnrolledInputBoundary;
import useCase.EnrolledContest.EnrolledInputData;

/**
 * Called from LoggedIn View to then display a popup of a specific enrolled contest.
 * Call to Enrolled Use Case interactor.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
 */
public class EnrolledController {

    final EnrolledInputBoundary enrolledUseCaseInteractor;

    /**
     * Contstructor that initializes a new EnrolledController object.
     *
     * EnrolledInteractor passed under EnrolledInputBoundary interface, adhering to LSP and CA.
     *
     * @param enrolledUseCaseInteractor The interactor for the Enrolled Use Case, passed under boundary.
     */
    public EnrolledController(EnrolledInputBoundary enrolledUseCaseInteractor) {
        this.enrolledUseCaseInteractor = enrolledUseCaseInteractor;
    }

    /**
     * Constructs input data given parameters and executes the EnrolledUseCaseInteractor.
     *
     * @param username The username of the user.
     * @param contestId The Contest Id of the selected contest.
     */
    public void execute(String username, String contestId) {
        EnrolledInputData enrolledInputData = new EnrolledInputData(username, contestId);
        enrolledUseCaseInteractor.execute(enrolledInputData);
    }

    /**
     * Marks the contest as completed.
     *
     * @param  username   the username of the user
     * @param  contestId  the ID of the contest
     * @return True if the contest was successfully marked as completed, false otherwise
     */
    public boolean markContestCompleted(String username, String contestId){
        EnrolledInputData enrolledInputData = new EnrolledInputData(username, contestId);
        return enrolledUseCaseInteractor.markContestCompleted(enrolledInputData);
    }
}
