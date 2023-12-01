package UseCase.HomePage;

import entity.User;

public interface HomePageDataAccessInterface {
    <T> T getEntity(Class<T> valueType, String collection, String id);
}
