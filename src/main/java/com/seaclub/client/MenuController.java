package com.seaclub.client;

import javafx.fxml.FXML;

import java.io.IOException;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

        private ClubMember clubMember;

        @FXML
        private Label labelUser;

        @FXML
        private Button competitionButton;

        @FXML
        private Button buttonQuote;

        @FXML
        private Button buttonBoat;

        @FXML
        private Button buttonUser;

        @FXML
        private Button btnLogout;

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
        protected void onCompetitionButtonClick()throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Competition.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                CompetitionController hc = fxmlLoader.getController();
                hc.setClubMember(clubMember);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage stage2 = (Stage) competitionButton.getScene().getWindow();
                stage2.close();
        }

        @FXML
        protected void buttonQuotOnClick()throws IOException {
            labelUser.setText("Quote");
        }

        @FXML
        protected void buttonBoatOnClick()throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Boat.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                BoatController bc = fxmlLoader.getController();
                bc.setClubMember(this.clubMember);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage stage2 = (Stage) buttonBoat.getScene().getWindow();
                stage2.close();
        }

        @FXML
        protected void buttonUserOnClick()throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("User.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                UserController uc = fxmlLoader.getController();
                uc.setClubMember(this.clubMember);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage stage2 = (Stage) buttonUser.getScene().getWindow();
                stage2.close();
        }

        public void setClubMember(ClubMember clubMember) {
                this.clubMember = clubMember;
        }

        public void showUser(){
            labelUser.setText("Ciao "+clubMember.getName());
        }
}
