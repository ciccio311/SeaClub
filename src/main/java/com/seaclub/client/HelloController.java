package com.seaclub.client;

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
        ClubMember c1 = new ClubMember("fffanokkk",0,"gino","rino","via 2","2");
        ClubMember clogged = Client.getInstance().login(c1);
        if(clogged!=null)
            System.out.println(clogged.getBoats().size());
        else
            System.out.println("NULL");

    }

}