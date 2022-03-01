package com.seaclub.client;

import com.seaclub.Manager.ClubMemberManager;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.StorageRegister;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserListController {
    private ClubMember clubMember;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewUsers;

    @FXML
    protected void backOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEmployee.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        MenuEmployeeController mc = fxmlLoader.getController();
        mc.setClubMember(this.clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnBack.getScene().getWindow();
        stage2.close();
    }

    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        setTableView();
    }

    private void setTableView(){
        tableViewUsers.getItems().clear();

        TableColumn<ClubMember, String> C1 = new TableColumn("ID SOCIO");
        C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        C1.setStyle("-fx-alignment: CENTER;");
        TableColumn<ClubMember, String> C2 = new TableColumn("CF");
        C2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getCF())));
        C2.setStyle("-fx-alignment: CENTER;");
        TableColumn<ClubMember, String> C3 = new TableColumn("DIPENDENTE");
        C3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getDipendente())));
        C3.setStyle("-fx-alignment: CENTER;");
        TableColumn<ClubMember, String> C4 = new TableColumn("NOME");
        C4.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
        C4.setStyle("-fx-alignment: CENTER;");
        TableColumn<ClubMember, String> C5 = new TableColumn("COGNOME");
        C5.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getSurname())));
        C5.setStyle("-fx-alignment: CENTER;");
        TableColumn<ClubMember, String> C6 = new TableColumn("INDIRIZZO");
        C6.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getAddress())));
        C6.setStyle("-fx-alignment: CENTER;");

        C1.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        C2.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        C3.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        C4.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        C5.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        C5.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        C6.prefWidthProperty().bind(tableViewUsers.widthProperty().divide(6));
        tableViewUsers.getColumns().addAll(C1, C2, C3, C4, C5, C6);

        tableViewUsers.setItems((FXCollections.observableArrayList(Client.getInstance().getAllClubMember())));
    }
}