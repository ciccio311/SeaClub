package com.seaclub.client;

import javafx.fxml.FXML;

import java.io.IOException;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController {
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
        protected void onCompetitionButtonClick()throws IOException {
            labelUser.setText("COMPETITION");
        }

        @FXML
        protected void buttonQuotOnClick()throws IOException {
            labelUser.setText("Quote");
        }

        @FXML
        protected void buttonBoatOnClick()throws IOException {
            labelUser.setText("BOAT");
        }

        @FXML
        protected void buttonUserOnClick()throws IOException {
            labelUser.setText("USER");
        }

        public void showUser(ClubMember clubMember){
            labelUser.setText("Ciao "+clubMember.getName());
        }
}
