package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.MembershipRegister;
import com.seaclub.Model.StorageRegister;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageRegisterController {
    private ClubMember clubMember;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewRegister;


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
        setTableView();
    }

    private void setTableView(){
        tableViewRegister.getItems().clear();

        TableColumn<StorageRegister, String> C1 = new TableColumn("ID BARCA");
        C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdBoat())));
        C1.setStyle("-fx-alignment: CENTER;");
        TableColumn<StorageRegister, String> C2 = new TableColumn("ID SOCIO");
        C2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdClubMember())));
        C2.setStyle("-fx-alignment: CENTER;");
        TableColumn<StorageRegister, String> C3 = new TableColumn("PAGAMENTO");
        C3.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPaymentMethod()));
        C3.setStyle("-fx-alignment: CENTER;");
        TableColumn<StorageRegister, String> C4 = new TableColumn(" DATA PAGAMENTO");
        C4.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getDatePayment())));
        C4.setStyle("-fx-alignment: CENTER;");
        TableColumn<StorageRegister, String> C5 = new TableColumn("PREZZO");
        C5.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice()+"€")));
        C5.setStyle("-fx-alignment: CENTER;");

        if(this.clubMember.getDipendente() == 1){
            //SE DIPENDENTE GET ALL STORAGE REGISTER
            C1.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(5));
            C2.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(5));
            C3.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(5));
            C4.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(5));
            C5.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(5));
            tableViewRegister.getColumns().addAll(C1, C2, C3, C4, C5);

            tableViewRegister.setItems((FXCollections.observableArrayList(Client.getInstance().getAllStorageRegisterQuote())));

        }
        else {
            //SE club member GET STORAGE REGISTER BY CLUB MEMBER ID
            C1.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(4));
            C3.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(4));
            C4.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(4));
            C5.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(4));
            tableViewRegister.getColumns().addAll(C1, C3, C4, C5);

            List<StorageRegister> reg = new ArrayList<StorageRegister>();
            for(var x : Client.getInstance().getAllStorageRegisterQuote()){
                if(x.getIdClubMember() == this.clubMember.getId()){
                    reg.add(x);
                }
            }

            tableViewRegister.setItems((FXCollections.observableArrayList(reg)));
        }
    }
}