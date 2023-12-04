package useCase.SignUp;

import com.google.firebase.internal.NonNull;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    <T> T getEntity(@NonNull Class<T> valueType, String collection, String id);
}
