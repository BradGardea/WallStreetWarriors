package FirebaseDataAccess;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.internal.NonNull;
import com.google.firebase.internal.Nullable;
import entity.Contest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseDataAccess implements IDataAccess {
    private static FirebaseDataAccess instance = null;
    private static Firestore db = null;
    public static synchronized FirebaseDataAccess getInstance(){
        if (instance == null){
            instance = new FirebaseDataAccess();
        }
        return instance;
    }
    public static void setFirestore(Firestore dB){
        db = dB;
    }

    /**
     * Sets a class of type T in specified path based on collection and id
     * @param valueType Class type of class to be pushed
     * @param collection collection id of target path
     * @param id target document id
     * @param <T> Generic of document type
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public @Nullable <T> T getEntity(@NonNull Class<T> valueType, String collection, String id) {
        try{
            DocumentReference docRef = db.collection(collection).document(id);
            // asynchronously retrieve the document
            ApiFuture<DocumentSnapshot> future = docRef.get();
            // block on response
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // convert document to POJO
                var result = document.toObject(valueType);
                System.out.println(result.getClass().toString());
                return result;
            } else {
                System.out.println("No such document!");
            }
        }
        catch (Exception ex){
            System.out.println("Exception gettingEntity: " + ex);
        }
        return null;
    }
    public @Nullable <T> ArrayList<T> getEntities(@NonNull Class<T> valueType, String collection) {
        try{
            ApiFuture<QuerySnapshot> future = db.collection(collection).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            var returnDocs = new ArrayList<T>();
            if (documents.size() > 0) {
                for (QueryDocumentSnapshot document : documents) {
                    returnDocs.add(document.toObject(valueType));
                    System.out.println(document.getId() + " => " + document.toObject(valueType));
                }
                return returnDocs;
            } else {
                System.out.println("No such documents!");
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Sets a class of type T in specified path based on collection and id
     * @param entity Class of type T to be pushed
     * @param collection collection id of target path
     * @param id target document id
     * @param <T> Generic of document type
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public @Nullable <T> void setOrUpdateEntity(@NonNull T entity, String collection, String id) {
        try{
            ApiFuture<WriteResult> future = db.collection(collection).document(id).set(entity);
            // block on response if required
            System.out.println("Update time: " + future.get().getUpdateTime());
        }
        catch (Exception ex){
            System.out.println("Exception settingOrUpdating: " + ex);
        }
    }

    /**
     * Deletes document with specified collection and id
     * @param collection collection id of target document
     * @param id target document id
     * @return true if document delete successful, false if not
     */
    public boolean deleteEntity(String collection, String id){
        try{
            ApiFuture<WriteResult> writeResult = db.collection(collection).document(id).delete();
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
            return true;
        }
        catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    
    
}
