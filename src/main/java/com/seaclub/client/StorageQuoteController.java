package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StorageQuoteController {
    @FXML
    private Button btnBack;

    @FXML
    private Button payButton;

    @FXML
    private ListView listViewStorage;

    @FXML
    private ComboBox boatComboBox;

    @FXML
    private Label storageQuotePriceLabel;

    @FXML
    private RadioButton cardRadioButton;

    @FXML
    private RadioButton banckTransferRadioButton;

    private ClubMember clubMember;


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

    }
}