package com.seaclub.client;

import com.seaclub.Manager.BoatManager;
import com.seaclub.Manager.CompetitionManager;
import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        btnLogin.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); -fx-background-color: red; -fx-background-radius: 50;");

        if(username.getText().length()==0 || password.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inserisci tutti i campi!");
            alert.showAndWait();
            btnLogin.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); -fx-background-color: #D3D3D3; -fx-background-radius: 50;");
        }
        else
        {
            ClubMember clubMember = new ClubMember();
            clubMember.setId(Integer.valueOf(username.getText()));
            clubMember.setPassword(password.getText());
            clubMember = Client.getInstance().login(clubMember);

            /*Competition cp = new Competition();
            Date date = new Date(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            cp.setDate(sqlDate);
            cp.setPrice((float) 6.35);
            Client.getInstance().addNewCompetition(cp);*/

            if(clubMember!=null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                MenuController hc = fxmlLoader.getController();
                //CompetitionManager.getInstance().updateList();
                hc.showUser(clubMember);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage stage2 = (Stage) btnLogin.getScene().getWindow();
                stage2.close();
            }
        }
    }

    @FXML
    protected void signUpOnClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sign up.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        SignUpController hc = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnSignup.getScene().getWindow();
        stage2.close();
    }
}