package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class BoatListController {
    private ClubMember clubMember;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewBoats;

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
        try {
            tableViewBoats.getItems().clear();

            TableColumn<Boat, String> C1 = new TableColumn("ID BARCA");
            C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
            C1.setStyle("-fx-alignment: CENTER;");
            TableColumn<Boat, String> C2 = new TableColumn("NOME");
            C2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
            C2.setStyle("-fx-alignment: CENTER;");
            TableColumn<Boat, String> C3 = new TableColumn("LUNGHEZZA");
            C3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getWidth())));
            C3.setStyle("-fx-alignment: CENTER;");
            TableColumn<Boat, String> C4 = new TableColumn("ID PROPRIETARIO");
            C4.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdClubMember())));
            C4.setStyle("-fx-alignment: CENTER;");

            C1.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(4));
            C2.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(4));
            C3.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(4));
            C4.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(4));
            tableViewBoats.getColumns().addAll(C1, C2, C3, C4);


            tableViewBoats.setItems((FXCollections.observableArrayList(Client.getInstance().getAllBoats())));
        }catch (Exception e){
            System.out.println(e.toString());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading all boats!");
            alert.showAndWait();
        }
    }
}
