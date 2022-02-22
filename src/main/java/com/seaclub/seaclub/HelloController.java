package com.seaclub.seaclub;

import com.seaclub.Model.ClubMember;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button mimmo;

    @FXML
    protected void onHelloButtonClick() {
        ClubMember m1 = new ClubMember();
        m1.setName("BEPPE");
        mimmo.setText("CIAO "+m1.getName());
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}