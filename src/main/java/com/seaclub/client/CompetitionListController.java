package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
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

public class CompetitionListController {
    private ClubMember clubMember;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewCompetitions;

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
        tableViewCompetitions.getItems().clear();

        TableColumn<Competition, String> C1 = new TableColumn("ID GARA");
        C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        C1.setStyle("-fx-alignment: CENTER;");
        TableColumn<Competition, String> C2 = new TableColumn("PREZZO");
        C2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice()+"€")));
        C2.setStyle("-fx-alignment: CENTER;");
        TableColumn<Competition, String> C3 = new TableColumn("DATA");
        C3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getDate())));
        C3.setStyle("-fx-alignment: CENTER;");

        C1.prefWidthProperty().bind(tableViewCompetitions.widthProperty().divide(3));
        C2.prefWidthProperty().bind(tableViewCompetitions.widthProperty().divide(3));
        C3.prefWidthProperty().bind(tableViewCompetitions.widthProperty().divide(3));
        tableViewCompetitions.getColumns().addAll(C1, C2, C3);

        tableViewCompetitions.setItems((FXCollections.observableArrayList(Client.getInstance().getAllCompetitions())));
    }
}
