package firebaseDataAccess;

public interface IUpdateableEntity {

    /*
    Method that requires implementation dependent on class that needs to be updated in Firebase
     */
    public void updateInStorage();
//    /**
//     * Gets the document with the specified ID from Firestore
//     * @param collection The string corresponding to the Parent collection of the document
//     * @param iD The string UUID corresponding to the instance of the entity in Firestore
//     */
//    public @Nullable <T> T getEntity(@NonNull Class<T> valueType, String collection, String iD);
//
//    /**
//     * Sets the document with the specified ID from Firestore or updates existing document
//     * @param collection The string corresponding to the Parent collection of the document
//     * @param iD The string UUID corresponding to the instance of the entity in Firestore
//     */
//    public @Nullable <T> void setOrUpdateEntity(@NonNull T entity, String collection, String iD, IFirebaseEntity currObject);
//
//    /**
//     * Deletes the document with the specified ID from Firestore
//     * @param collection The string corresponding to the Parent collection of the document
//     * @param iD The string UUID corresponding to the instance of the entity in Firestore
//     */
//    public boolean deleteEntity(String collection, String iD);
}