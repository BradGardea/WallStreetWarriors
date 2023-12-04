package useCase.CompletedContest;

public interface CompletedContestDataAccessInterface {

    <T> T getEntity(Class<T> valueType, String collection, String id);

}
