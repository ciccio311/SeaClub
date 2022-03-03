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

/**
 * Class to manage the information and action about the view StorageQuote
 */
public class StorageQuoteController {

    private Boat boatSelected = new Boat();
    private float price;

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

    /**
     * Method used to manage the pressing of PAY button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void payOnClick() throws IOException {
        if(BoatComboBox.getSelectionModel().isEmpty() ||
                (!cardRadioButton.isSelected()) && !banckTransferRadioButton.isSelected()
                ||
                (cardRadioButton.isSelected()) && banckTransferRadioButton.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }else{

            StorageRegister storageRegister = new StorageRegister();
            storageRegister.setIdBoat(boatSelected.getId());
            storageRegister.setIdQuote(1);
            storageRegister.setIdClubMember(clubMember.getId());
            storageRegister.setPrice(price);
            if(cardRadioButton.isSelected())
                storageRegister.setPaymentMethod("Card");
            if(banckTransferRadioButton.isSelected())
                storageRegister.setPaymentMethod("Bank transfer");
            Date date = new Date(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            storageRegister.setDatePayment(sqlDate);

            if(Client.getInstance().addStorageRegister(storageRegister)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Payment successfull!");
                alert.showAndWait();
                if(!(Client.getInstance().updateNotificationStorage(clubMember))){
                    Alert alerts = new Alert(Alert.AlertType.ERROR, "Notification not updated!");
                    alerts.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                alert.showAndWait();
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MembershipQuote.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            MembershipQuoteController mc = fxmlLoader.getController();
            mc.setClubMember(this.clubMember);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 = (Stage) btnBack.getScene().getWindow();
            stage2.close();


        }
    }

    /**
     * Method used to manage the pressing of BOAT button
     */
    @FXML
    protected void OnClickBoat(){
        String selectedItem = (String) BoatComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            String words[] = selectedItem.split(" ");
            //boatSelected.setId(Integer.valueOf(words[0]));

            boatSelected = clubMember.getBoatById(Integer.valueOf(words[0]));
            price = boatSelected.getWidth()*10;
            storageQuotePriceLabel.setText(price+"€");
        }
    }

    /**
     * Method used to manage the pressing of HISTORY button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void onHistoryButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StorageRegister.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        StorageRegisterController uc = fxmlLoader.getController();
        uc.setClubMember(this.clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnBack.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of BACK button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void backOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MembershipQuote.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        MembershipQuoteController mc = fxmlLoader.getController();
        mc.setClubMember(this.clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnBack.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to set the club member for the view
     * @param clubMember is the club member
     */
    public void setClubMember(ClubMember clubMember) {
        try {
            this.clubMember = clubMember;
            setView();
        }
        catch(Exception e){

            System.out.println(e);
        }
    }

    /**
     * Method used to set parameters of the view
     */
    protected void setView(){
        List<Boat> expiredBoat = new ArrayList<Boat>();

        for(var i:clubMember.getBoatExpired()){
            String infoItem = i.getId()+" "+i.getName();
            BoatComboBox.getItems().add(infoItem);
        }

    }

    /**
     * Method used to know if the storage payment of a specific boat is expired
     * @param boat is the specific boat
     * @return TRUE->expired; FALSE->not expired;
     */
    private boolean isDateExpired(Boat boat){
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate now = LocalDate.now();
        LocalDate dateMinusYear = now.minusYears(1);
        Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
        StorageRegister storageRegister = new StorageRegister();
        storageRegister = Client.getInstance().getLastStorageRegister(boat);

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