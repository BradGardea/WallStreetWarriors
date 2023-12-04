package useCase.EnrolledContest;

/**
 * A boundary between Controller and Enrolled UCI.
 * execute method in UCI passing inputData.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public interface EnrolledInputBoundary {
    void execute(EnrolledInputData enrolledInputData);
    boolean markContestCompleted(EnrolledInputData enrolledInputData);
}
