package useCase.HomePage;

public interface HomePageDataAccessInterface {
    <T> T getEntity(Class<T> valueType, String collection, String id);
}
