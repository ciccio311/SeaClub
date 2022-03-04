package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Class to manage the information and action about the view User
 */
public class UserController {

    @FXML
    private Label memberUsernameLabel;

    @FXML
    private Label memberNameLabel;

    @FXML
    private  Label memberSurnameLabel;

    @FXML
    private Label memberSCfLabel;

    @FXML
    private TextField AddressTextField;

    @FXML
    private TextField PasswordTextField;

    private ClubMember clubMember;

    /**
     * Method used to set the club member for the view
     * @param clubMember is the club member
     */
    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        memberNameLabel.setText(this.clubMember.getName());
        memberSurnameLabel.setText(this.clubMember.getSurname());
        memberUsernameLabel.setText(String.valueOf(this.clubMember.getId()));
        memberSCfLabel.setText(this.clubMember.getCF());
        AddressTextField.setText(this.clubMember.getAddress());
        PasswordTextField.setText(this.clubMember.getPassword());
    }

    /**
     * Method used to manage the pressing of BACK button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void backOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        MenuController mc = fxmlLoader.getController();
        mc.setClubMember(this.clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) AddressTextField.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of MODIFY button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void modifyOnClick() throws IOException {
        if(AddressTextField.getText().length() > 25 || PasswordTextField.getText().length() > 17) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fields too long!");
            alert.showAndWait();
        }
        else if(AddressTextField.getText().length() == 0 || PasswordTextField.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }
        else{
            if (!Objects.equals(this.clubMember.getAddress(), AddressTextField.getText()) || !Objects.equals(this.clubMember.getPassword(), PasswordTextField.getText())) {
                this.clubMember.setAddress(AddressTextField.getText());
                this.clubMember.setPassword(PasswordTextField.getText());
                if (Client.getInstance().updateMemberInfo(this.clubMember)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Parameters modified!");
                    alert.showAndWait();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                    Parent root = (Parent) fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage stage2 = (Stage) AddressTextField.getScene().getWindow();
                    stage2.close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Modify something!");
                alert.showAndWait();
            }
        }
    }
}