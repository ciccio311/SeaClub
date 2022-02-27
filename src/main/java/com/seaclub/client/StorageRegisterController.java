package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.StorageRegister;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageRegisterController {
    private ClubMember clubMember;
    private ObservableList<String> items = FXCollections.observableArrayList();
    private List<String> register;

    @FXML
    private Button btnBack;

    @FXML
    private ListView listViewRegister;


    @FXML
    protected void backOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        MenuController mc = fxmlLoader.getController();
        mc.setClubMember(this.clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnBack.getScene().getWindow();
        stage2.close();

    }

    public void setClubMember(ClubMember cm){
        this.clubMember = cm;
        setListView();
    }

    private void setListView(){
        listViewRegister.getItems().clear();
        register = new ArrayList<String>();
        List<StorageRegister> reg = new ArrayList<StorageRegister>();

        if(this.clubMember.getDipendente() == 1){
            //SE DIPENDENTE GET ALL STORAGE REGISTER

            reg = Client.getInstance().getAllStorageRegisterQuote();
            listViewRegister.setItems(items);
            items.add("ID BARCA,  ID SOCIO, PAGAMENTO,  DATA PAGAMENTO,  PREZZO");

            for (var x : reg) {
                String item = x.getIdBoat() + ", " + x.getIdClubMember() + ", " + x.getPaymentMethod() + ", " + x.getDatePayment().toString() + ", " + String.valueOf(x.getPrice());
                items.add(item);
            }
        }
        else {
            //SE club member GET STORAGE REGISTER BY CLUB MEMBER ID

            listViewRegister.setItems(items);
            items.add("ID BARCA,  PAGAMENTO,  DATA PAGAMENTO,  PREZZO");
            for (var x : register) {
                items.add(x);
            }
        }
    }
}
