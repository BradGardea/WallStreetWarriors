package UseCase.CompletedContest;

import entity.Contest;

public interface CompletedContestDataAccessInterface {

    <T> T getEntity(Class<T> valueType, String collection, String id);

}
