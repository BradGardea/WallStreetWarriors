package FirebaseDataAccess;

import java.util.EventListener;

public interface IFirebaseEntity{
    /**
     * Gets the document with the specified ID from Firestore
     * @param collection The string corresponding to the Parent collection of the document
     * @param iD The string UUID corresponding to the instance of the entity in Firestore
     */
    public void GetDocument(String collection, String iD);

    /**
     * Sets the document with the specified ID from Firestore or updates existing document
     * @param collection The string corresponding to the Parent collection of the document
     * @param iD The string UUID corresponding to the instance of the entity in Firestore
     */
    public void SetOrUpdateDocument(String collection, String iD, IFirebaseEntity currObject);

    /**
     * Deletes the document with the specified ID from Firestore
     * @param collection The string corresponding to the Parent collection of the document
     * @param iD The string UUID corresponding to the instance of the entity in Firestore
     */
    public void DeleteDocument(String collection, String iD);
}
