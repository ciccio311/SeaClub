package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class to manage the information and action about the view SIGNUP
 */
public class SignUpController {
    @FXML
    private Button btnSignUp;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField address;

    @FXML
    private TextField CF;

    @FXML
    private TextField password;

    /**
     * Method used to manage the pressing of SIGNUP button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void SignUpOnClick() throws IOException {
        if(name.getText().length()==0 ||
                surname.getText().length()==0 ||
                address.getText().length()==0 ||
                CF.getText().length()==0 || password.getText().length()==0){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert info in all fields!");
            alert.showAndWait();
        }else{
            ClubMember clubMember = new ClubMember();
            clubMember.setName(name.getText());
            clubMember.setSurname(surname.getText());
            clubMember.setCF(CF.getText());
            clubMember.setAddress(address.getText());
            clubMember.setPassword(password.getText());
            clubMember.setDipendente(0);
            if(Client.getInstance().addMember(clubMember)){

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                LoginController hc = fxmlLoader.getController();
                clubMember = Client.getInstance().getMemberByCF(clubMember);
                if(clubMember!=null) {
                    hc.isFirstLogin(clubMember);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage stage2 = (Stage) btnSignUp.getScene().getWindow();
                    stage2.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error for uploading your information!");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Already exist an user with the same CF!");
                alert.showAndWait();
            }
        }
    }

}