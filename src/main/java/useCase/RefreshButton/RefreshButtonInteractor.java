package useCase.RefreshButton;

import firebaseDataAccess.FirebaseDataAccess;

public class RefreshButtonInteractor implements RefreshButtonInputBoundary {

    public final RefreshButtonOutputBoundary refreshButtonPresenter;
    public final  FirebaseDataAccess refreshButtonDataAccess;

    public RefreshButtonInteractor(RefreshButtonOutputBoundary refreshButtonPresenter, FirebaseDataAccess refreshButtonDataAccess){
        this.refreshButtonPresenter = refreshButtonPresenter;
        this.refreshButtonDataAccess = refreshButtonDataAccess;
    }

    public void execute(){

    }
}
