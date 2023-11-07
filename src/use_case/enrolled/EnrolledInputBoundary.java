package use_case.enrolled;

/**
 * A boundary between Controller and Enrolled UCI.
 * execute method in UCI passing inputData.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public interface EnrolledInputBoundary {
    void execute(EnrolledInputData enrolledInputData);
}
