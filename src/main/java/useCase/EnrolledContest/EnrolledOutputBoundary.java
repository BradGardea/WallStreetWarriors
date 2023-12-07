package useCase.EnrolledContest;

/**
 *  Interface for a boundary between interactor and presenter.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
 */
public interface EnrolledOutputBoundary {
    void prepareSuccessView(EnrolledOutputData enrolledOutputData);
}
