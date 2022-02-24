package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        if(username.getText().length()==0 || password.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inserisci tutti i campi!");
            alert.showAndWait();
        }
        else
        {
            ClubMember clubMember = new ClubMember();
            clubMember.setCF(username.getText());
            clubMember.setPassword(password.getText());
            clubMember = Client.getInstance().login(clubMember);
            if(clubMember!=null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign up.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                SignUpController hc = fxmlLoader.getController();
                hc.show(clubMember.getAddress());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }
}
