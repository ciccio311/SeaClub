package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
import com.seaclub.Model.CompetitionRegister;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionController {
    private ClubMember clubMember;
    private Boat boatSelected = new Boat();
    private Competition competitionSelected = new Competition();

    @FXML
    private Label RacePriceLabel;

    @FXML
    private ComboBox DateComboBox;

    @FXML
    private ComboBox BoatComboBox;

    @FXML
    private RadioButton BanckTransferRadioButton;

    @FXML
    private RadioButton CardRadioButton;

    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Competition>competitions = new ArrayList<Competition>();
        competitions = Client.getInstance().getAllCompetition();

        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate now = LocalDate.now();

        Date dateNow = Date.from(now.atStartOfDay(defaultZoneId).toInstant());

        for(var comp:competitions){
            if(comp.getDate().after(dateNow)){

                String info =comp.getId()+" "+dateFormat.format(comp.getDate())+" "+comp.getPrice()+"€";
                DateComboBox.getItems().add(info);
            }else{
                competitions.remove(comp);
            }
        }

        for(var boat:clubMember.getBoats()){
            String boatInfo = boat.getId()+" "+boat.getName();
            BoatComboBox.getItems().add(boatInfo);
        }

    }


    @FXML
    protected void OnClickDate(){
        String selectedItem = (String) DateComboBox.getSelectionModel().getSelectedItem();
        String words[] = selectedItem.split(" ");
        RacePriceLabel.setText(words[2]);
        competitionSelected.setId(Integer.valueOf(words[0]));
    }

    @FXML
    protected void OnClickBoat(){
        String selectedItem = (String) BoatComboBox.getSelectionModel().getSelectedItem();
        String words[] = selectedItem.split(" ");
        boatSelected.setId(Integer.valueOf(words[0]));
    }

    @FXML
    protected void SubmitOnClick(){
        if(DateComboBox.getSelectionModel().isEmpty() || BoatComboBox.getSelectionModel().isEmpty() ||
                (!CardRadioButton.isSelected()) && !BanckTransferRadioButton.isSelected()
                ||
                (CardRadioButton.isSelected()) && BanckTransferRadioButton.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }else{
            CompetitionRegister competitionRegister = new CompetitionRegister();
            competitionRegister.setIdBoat(boatSelected.getId());
            competitionRegister.setIdCompetition(competitionSelected.getId());
            competitionRegister.setIdClubMember(clubMember.getId());
            if(CardRadioButton.isSelected())
                competitionRegister.setPaymentMethod("Card");
            if(BanckTransferRadioButton.isSelected())
                competitionRegister.setPaymentMethod("Bank transfer");
            Client.getInstance().addBoatToCompetition(competitionRegister);
        }
    }



}