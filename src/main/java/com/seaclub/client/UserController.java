package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        memberNameLabel.setText(this.clubMember.getName());
        memberSurnameLabel.setText(this.clubMember.getSurname());
        memberUsernameLabel.setText(String.valueOf(this.clubMember.getId()));
        memberSCfLabel.setText(this.clubMember.getCF());
        AddressTextField.setText(this.clubMember.getAddress());
        PasswordTextField.setText(this.clubMember.getPassword());
    }

    @FXML
    protected void backOnClick(){}

    @FXML
    protected void modifyOnClick(){}
}