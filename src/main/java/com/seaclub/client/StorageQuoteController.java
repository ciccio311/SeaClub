package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.StorageRegister;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageQuoteController {

    private Boat boatSelected = new Boat();

    @FXML
    private Button btnBack;

    @FXML
    private Button payButton;

    @FXML
    private ComboBox BoatComboBox;

    @FXML
    private Label storageQuotePriceLabel;

    @FXML
    private RadioButton cardRadioButton;

    @FXML
    private RadioButton banckTransferRadioButton;

    private ClubMember clubMember;


    @FXML
    protected void payOnClick(){
        if(BoatComboBox.getSelectionModel().isEmpty() ||
                (!cardRadioButton.isSelected()) && !banckTransferRadioButton.isSelected()
                ||
                (cardRadioButton.isSelected()) && banckTransferRadioButton.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }else{
            if(isDateExpired()){
                //add new StorageRegister

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Date doesn't expired!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    protected void OnClickBoat(){
        String selectedItem = (String) BoatComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            String words[] = selectedItem.split(" ");
            //boatSelected.setId(Integer.valueOf(words[0]));

            boatSelected = clubMember.getBoatById(Integer.valueOf(words[0]));
            float price = boatSelected.getWidth()*10;
            storageQuotePriceLabel.setText(price+"€");
        }
    }

    @FXML
    protected void onHistoryButtonClick(){}

    @FXML
    protected void backOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MembershipQuote.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnBack.getScene().getWindow();
        stage2.close();
    }


    public void setClubMember(ClubMember clubMember) {
        try {
            this.clubMember = clubMember;
            setView();
        }
        catch(Exception e){

            System.out.println(e);
        }
    }

    protected void setView(){
        //boatComboBox.getItems().clear();
        for(var i:clubMember.getBoats()){
            String infoItem = i.getId()+" "+i.getName();
            BoatComboBox.getItems().add(infoItem);
        }

    }

    private boolean isDateExpired(){
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate now = LocalDate.now();
        LocalDate dateMinusYear = now.minusYears(1);
        Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
        StorageRegister storageRegister = new StorageRegister();
        storageRegister = Client.getInstance().getLastStorageRegister(boatSelected);

        if(storageRegister!=null){
            if(storageRegister.getDatePayment().before(dateNow)){
                //expired
                return true;
            }else
                return false;
        }else
            return true;
    }
}