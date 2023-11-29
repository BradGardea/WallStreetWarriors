package view.AvailableContests;

import FirebaseDataAccess.FirebaseDataAccess;
import UseCase.AvailableContest.AvailableContestInteractor;
import app.ContestUseCaseFactory;
import app.Main;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.User;
import interface_adapters.AvailableContests.AvailableContestState;
import interface_adapters.AvailableContests.AvailableContestsController;
import interface_adapters.AvailableContests.AvailableContestsViewModel;
import interface_adapters.ViewModelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AvailableContestDetailView extends JDialog implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel contestIdLabel;
    private JLabel industryLabel;
    private JLabel numberOfPlayersLabel;
    private JLabel startTimeLabel;
    private JLabel endTimeLabel;
    private JLabel cashRemainingLabel;
    private JList stockChoicesList;
    private JSpinner stockQuantitySpinner;
    private JLabel stockNameLabel;
    private JLabel totalCostLabel;
    private JLabel purchasePriceLabel;
    private final AvailableContestsViewModel viewModel;
    private final AvailableContestsController controller;
    private final User user;
    public final String viewName = "availableContestDetailView";
    private HashMap<String, HashMap<String, String>> currentPortfollio = new HashMap<String, HashMap<String, String>>(); //StockTickerName: {StockTickerMetaDataName: StockTickerMetaDataName}

    public AvailableContestDetailView(AvailableContestsController controller, AvailableContestsViewModel viewModel, String username) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.user = FirebaseDataAccess.getInstance().getEntity(User.class, "Users", username);
        viewModel.addPropertyChangeListener(this); //trigger events based on state change

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setListValues();
        setUiValues();
        setDefaultStockSelectionUiValues();

        stockChoicesList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()){
                            var updatedCost = controller.stockListChanged(stockChoicesList.getSelectedValue().toString()); //API call to get update prices here
                            updateStockSelectionUI(stockChoicesList.getSelectedValue().toString()); // Add extra param with api call here
                        }
                    }
        });

        // Add current changes to currentPortfolio
        stockQuantitySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (stockChoicesList.getSelectedValue() != null){
                    var currentStock = stockChoicesList.getSelectedValue().toString();
                    var updatedCost = controller.getUpdatedStockPrice(currentStock);
                    if (currentPortfollio.get(currentStock) != null){
                        var currentStockMetaData = currentPortfollio.get(currentStock);
                        currentStockMetaData.put("Quantity", stockQuantitySpinner.getValue().toString());
                        currentStockMetaData.put("Purchase Price", String.valueOf(updatedCost));
                    }
                    else{
                        var newCurrentStockMetaData = new HashMap<String, String>();
                        newCurrentStockMetaData.put("Quantity", stockQuantitySpinner.getValue().toString());
                        newCurrentStockMetaData.put("Purchase Price", String.valueOf(updatedCost));
                        currentPortfollio.put(currentStock, newCurrentStockMetaData);
                    }
                    totalCostLabel.setText(String.valueOf(Float.parseFloat(stockQuantitySpinner.getValue().toString()) * updatedCost));
                }
            }
        });


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private float calculateCashRemaining(){
        var cashRemaining = Float.parseFloat(cashRemainingLabel.getText());

        return 0;
    }
    private void setListValues() {
        var keyset = this.viewModel.getState().getContestDetails().getStockOptions();
        var model = new String[keyset.size()];
        for(var i = 0; i < keyset.size(); i++){
            model[i] = keyset.get(i).toString();
        }
        stockChoicesList.setListData(model);
    }

    public void getUpdatedStockPrices(String stockName){
        controller.getUpdatedStockPrice(stockName);
    }
    public void setUiValues(){
        // Populate view with default params
        var contestConfig = this.viewModel.getState().getContestDetails();
        this.industryLabel.setText(contestConfig.getIndustry());
        this.numberOfPlayersLabel.setText(Integer.toString(contestConfig.getMembers().size()));
        this.contestIdLabel.setText(contestConfig.getContestId());
        this.startTimeLabel.setText(contestConfig.getStartTime().toString());
        this.endTimeLabel.setText(contestConfig.getEndTime().toString());
    }

    public void updateStockSelectionUI(String stockSelection){
        this.stockNameLabel.setText(stockSelection);
        if (this.currentPortfollio != null && this.currentPortfollio.getOrDefault(stockSelection, null) != null){
            this.stockQuantitySpinner.setValue(this.currentPortfollio.get(stockSelection).get("quantity"));
        }
        else{
            this.stockQuantitySpinner.setValue(0);
        }
        this.purchasePriceLabel.setText("100"); //TODO: get stock cost here
    }
    public void setDefaultStockSelectionUiValues(){
        this.stockNameLabel.setText("Select a stock");
        this.stockQuantitySpinner.setValue(0);
        this.purchasePriceLabel.setText("N/A");
        this.totalCostLabel.setText("0");
        this.cashRemainingLabel.setText("10000");
    }

    private void onOK() {
        // switch this contest and its configuration to enrolled
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AvailableContestState state = (AvailableContestState) evt.getNewValue();
        if (state.getContestDetails() != null && !state.getContestDetails().equals(this.viewModel.getState())){
            setUiValues();
        }
    }

    public static void launch(AvailableContestDetailView dialog) throws IOException { //TODO: temp
//        AvailableContestsViewModel viewModel, AvailableContestsController controller, String username
//        URL url =  Main.class.getResource("/wallstreetwarriors-firebase-adminsdk-8g503-275acc4c97.json");
//        File file = new File(url.getPath());
//
//        FileInputStream serviceAccount =
//                new FileInputStream(file);
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        FirebaseApp.initializeApp(options);
//
//        var db = FirestoreClient.getFirestore();
//
//        //"Initialize" singleton entity level data access factory
//        var firebaseDataAccess = FirebaseDataAccess.getInstance();
//        firebaseDataAccess.setFirestore(db);
//
//        var vmM = new ViewModelManager();
//        var vm = new AvailableContestsViewModel();
//        var dialog = ContestUseCaseFactory.createAvailableContestDetailView(vm, vmM, "1", "foo");
//        AvailableContestDetailView dialog = new AvailableContestDetailView(controller, viewModel, username);
//        dialog.pack();
        dialog.setSize(new Dimension(600,800));
        dialog.setVisible(true);
        System.exit(0);
    }

    public static void main(String[] args) {
        try{
//            launch();
        }
        catch(Exception ex){

        }

//        AvailableContestDetailView dialog = new AvailableContestDetailView();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
    }
}
