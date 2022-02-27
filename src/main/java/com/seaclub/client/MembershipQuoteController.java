package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
import com.seaclub.Model.MembershipRegister;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MembershipQuoteController {

    private ClubMember clubMember;

    @FXML
    private Button btnBack;

    @FXML
    private Label priceLabel;

    @FXML
    private Label expirationLabel;

    @FXML
    private Label membershipPriceLabel;

    @FXML
    private Label paymentLabel;

    @FXML
    private RadioButton CardRadioButton;

    @FXML
    private RadioButton BanckTransferRadioButton;

    @FXML
    private Button buttonStorageQuote;

    @FXML
    private Button paymentButton;


    @FXML
    protected void onPaymentButtonClick() throws IOException {
        MembershipRegister mr = new MembershipRegister();
        if((!CardRadioButton.isSelected()) && !BanckTransferRadioButton.isSelected()
                ||
                (CardRadioButton.isSelected()) && BanckTransferRadioButton.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }
        else {
            if(CardRadioButton.isSelected())
                mr.setPaymentMethod("Card");
            if(BanckTransferRadioButton.isSelected())
                mr.setPaymentMethod("Bank transfer");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            //default time zone
            ZoneId defaultZoneId = ZoneId.systemDefault();

            /*LocalDate now = LocalDate.now();
            Date dateNow = Date.from(now.atStartOfDay(defaultZoneId).toInstant());
            mr.setDatePayment(dateNow);*/

            Date date = new Date(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            mr.setDatePayment(sqlDate);

            mr.setIdClubMember(clubMember.getId());
            mr.setIDQuote(1);

            if(Client.getInstance().addMembershipRegisterQuote(mr))
                System.out.println("FUNZIONA");
            else
                System.out.println("NON FUNX");


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Payment success!");
            alert.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            MenuController mc = new MenuController();
            mc.setClubMember(clubMember);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 = (Stage) btnBack.getScene().getWindow();
            stage2.close();
        }
    }

    @FXML
    protected void onStorageQuoteButtonClick(){}

    @FXML
    protected void backOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        MenuController mc = new MenuController();
        mc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnBack.getScene().getWindow();
        stage2.close();
    }


    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        setView();
    }

    private void setView(){
        membershipPriceLabel.setText("900 €");
        MembershipRegister membershipRegister = clubMember.getLastPaymentQuote(Client.getInstance().getAllMembershipQuoteRegister());
        if(membershipRegister != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            //default time zone
            ZoneId defaultZoneId = ZoneId.systemDefault();

            LocalDate now = LocalDate.now();
            LocalDate dateMinusYear = now.minusYears(1);
            Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
            if (membershipRegister.getDatePayment().before(dateNow)) {
                //expired
                expirationLabel.setText("Your membership is expired!");

            } else {
                priceLabel.setVisible(false);
                membershipPriceLabel.setVisible(false);
                paymentLabel.setVisible(false);
                CardRadioButton.setVisible(false);
                BanckTransferRadioButton.setVisible(false);
                paymentButton.setVisible(false);
                Date date = membershipRegister.getDatePayment();
                date.setYear(date.getYear() + 1);
                expirationLabel.setText("Your membership will expire on: " + date.toString());
            }
        }else{
            expirationLabel.setText("You have to pay a Membership Fee!");
        }
    }
}