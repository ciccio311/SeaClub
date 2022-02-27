package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
import com.seaclub.Model.MembershipRegister;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

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
    protected void onPaymentButtonClick(){}

    @FXML
    protected void onStorageQuoteButtonClick(){}

    @FXML
    protected void backOnClick(){}


    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        setView();
    }

    private void setView(){
       MembershipRegister membershipRegister = clubMember.getLastPaymentQuote(Client.getInstance().getAllMembershipQuoteRegister());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate now = LocalDate.now();
        LocalDate dateMinusYear = now.minusYears(1);
        Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
       if(membershipRegister.getDatePayment().before(dateNow)){
            //expired
           expirationLabel.setText("Your membership is expired!");

       }else{
           priceLabel.setVisible(false);
           membershipPriceLabel.setVisible(false);
           paymentLabel.setVisible(false);
           CardRadioButton.setVisible(false);
           BanckTransferRadioButton.setVisible(false);
           paymentButton.setVisible(false);
           Date date = membershipRegister.getDatePayment();
           date.setYear(date.getYear()+1);
           expirationLabel.setText("Your membership will expire on: "+date.toString());
       }
    }
}