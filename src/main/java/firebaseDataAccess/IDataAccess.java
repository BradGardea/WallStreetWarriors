package firebaseDataAccess;

import com.google.firebase.internal.NonNull;
import com.google.firebase.internal.Nullable;

import java.util.ArrayList;

public interface IDataAccess {
    @Nullable
    <T> T getEntity(@NonNull Class<T> valueType, String collection, String id);

    @Nullable <T> ArrayList<T> getEntities(@NonNull Class<T> valueType, String collection);

    @Nullable <T> void setOrUpdateEntity(@NonNull T entity, String collection, String id);

    boolean deleteEntity(String collection, String id);
}
