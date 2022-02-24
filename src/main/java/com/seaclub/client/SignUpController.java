package com.seaclub.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Label label;

    public void show(String c) throws IOException {

        label.setText(c);
    }
}
