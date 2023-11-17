package FirebaseDataAccess;

import java.util.EventListener;

public interface IFirebaseEntity{
    /**
     * Gets the document with the specified ID from Firestore
     * @param collection The string corresponding to the Parent collection of the document
     * @param iD The string UUID corresponding to the instance of the entity in Firestore
     */
    public void getDocument(String collection, String iD);

    /**
     * Sets the document with the specified ID from Firestore or updates existing document
     * @param collection The string corresponding to the Parent collection of the document
     * @param iD The string UUID corresponding to the instance of the entity in Firestore
     */
    public void setOrUpdateDocument(String collection, String iD, IFirebaseEntity currObject);

    /**
     * Deletes the document with the specified ID from Firestore
     * @param collection The string corresponding to the Parent collection of the document
     * @param iD The string UUID corresponding to the instance of the entity in Firestore
     */
    public void deleteDocument(String collection, String iD);
}
