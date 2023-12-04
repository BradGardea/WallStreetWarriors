package interfaceAdapters.RefreshButton;

import useCase.RefreshButton.RefreshButtonInputBoundary;

public class RefreshButtonController {

    public final RefreshButtonInputBoundary refreshButtonInteractor;

    public RefreshButtonController(RefreshButtonInputBoundary refreshButtonInteractor){
        this.refreshButtonInteractor = refreshButtonInteractor;
    }

    public void execute(){
        refreshButtonInteractor.execute();
    }

}
