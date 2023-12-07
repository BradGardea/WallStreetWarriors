package interfaceAdapters.Enrolled;

import useCase.EnrolledContest.EnrolledInputBoundary;
import useCase.EnrolledContest.EnrolledInputData;

/**
 * Called from LoggedIn View to then display a popup of a specific enrolled contest.
 * Call to Enrolled Use Case interactor.
 *
 *
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledController {

    final EnrolledInputBoundary enrolledUseCaseInteractor;

    public EnrolledController(EnrolledInputBoundary enrolledUseCaseInteractor) {
        this.enrolledUseCaseInteractor = enrolledUseCaseInteractor;
    }

    public void execute(String username, String contestId) {
        EnrolledInputData enrolledInputData = new EnrolledInputData(username, contestId);
        enrolledUseCaseInteractor.execute(enrolledInputData);
    }

    /**
     * Marks the contest as completed.
     *
     * @param  username   the username of the user
     * @param  contestId  the ID of the contest
     * @return            true if the contest was successfully marked as completed, false otherwise
     */
    public boolean markContestCompleted(String username, String contestId){
        EnrolledInputData enrolledInputData = new EnrolledInputData(username, contestId);
        return enrolledUseCaseInteractor.markContestCompleted(enrolledInputData);
    }
}
