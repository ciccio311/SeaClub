package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuEmployeeController {
    private ClubMember clubMember;

    @FXML
    private Button btnLogout;

    @FXML
    private Button buttonUsers;

    @FXML
    private Button buttonBoats;

    @FXML
    private Button buttonCompetitions;

    @FXML
    private Button buttonNotification;

    @FXML
    private Button buttonMembershipList;

    @FXML
    private Button buttonStorageList;

    @FXML
    private Button buttonCompetitionRegisterList;

    @FXML
    protected void logoutOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    @FXML
    protected void buttonUsersOnClick(){

    }

    @FXML
    protected void buttonBoatsOnClick(){

    }

    @FXML
    protected void buttonCompetitionsOnClick(){

    }

    @FXML
    protected void buttonNotificationOnClick(){

    }

    @FXML
    protected void buttonMembershipListOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MembershipRegister.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        MembershipRegisterController mrc = fxmlLoader.getController();
        mrc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    @FXML
    protected void buttonStorageListOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StorageRegister.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        StorageRegisterController src = fxmlLoader.getController();
        src.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    @FXML
    protected void buttonCompetitionRegisterListOnClick(){

    }

    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
    }
}