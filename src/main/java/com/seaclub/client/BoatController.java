package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class BoatController {

    @FXML
    private TextField name;

    @FXML
    private TextField width;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Boat> tableViewBoats;

    private ClubMember clubMember;

    @FXML
    protected void addOnClick()throws IOException {
        if(name.getText().length()==0 || width.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }
        else
        {
            Boat b = new Boat();
            b.setName(name.getText());
            b.setWidth(Float.valueOf(width.getText()));
            b.setIdClubMember(this.clubMember.getId());
            Client.getInstance().addNewBoat(b);

            this.clubMember = Client.getInstance().updateClubMember(clubMember);

            tableViewBoats.setItems(FXCollections.observableList((this.clubMember.getBoats())));
            tableViewBoats.refresh();

            name.setText("");
            width.setText("");
        }
    }

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

        TableColumn<Boat, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        column1.setStyle("-fx-alignment: CENTER; -fx-pref-width: 120px;");

        TableColumn<Boat, String> column2 = new TableColumn<>("Width");
        column2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getWidth())));
        column2.setStyle("-fx-alignment: CENTER; -fx-pref-width: 100px;");

        tableViewBoats.getColumns().add(column1);
        tableViewBoats.getColumns().add(column2);

        addButtonsToTable(tableViewBoats);

        tableViewBoats.setItems(FXCollections.observableList((cm.getBoats())));

    }


    private static void addButtonsToTable(TableView<Boat> tableViewBoats) {
        TableColumn<Boat, Void> colBtnDelete = new TableColumn("Delete");
        colBtnDelete.setStyle("-fx-alignment: CENTER; -fx-pref-width: 60px;");

        Callback<TableColumn<Boat, Void>, TableCell<Boat, Void>> cellFactoryDelete = new Callback<TableColumn<Boat, Void>, TableCell<Boat, Void>>() {
            @Override
            public TableCell<Boat, Void> call(final TableColumn<Boat, Void> param) {
                final TableCell<Boat, Void> cell = new TableCell<Boat, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction(new EventHandler<>() {

                            @Override
                            public void handle(javafx.event.ActionEvent arg0) {
                                Boat boat = getTableView().getItems().get(getIndex());

                                //UserManager.getInstance().removeBoat(boat);
                                //AlertManager.getInstance().showInfoMessage("Boat " + boat.getName() + " has been deleted!");

                                //update tableView
                                //tableViewBoats.setItems(FXCollections.observableList(((Partner) UserManager.getInstance().getPerson()).getBoats()));
                                tableViewBoats.refresh();
                            }
                        });

                        //setting button BUY style
                        btn.setMaxWidth(Double.MAX_VALUE);
                        btn.setStyle("-fx-background-color: RED;"
                                + "-fx-text-fill: WHITE;");

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnDelete.setCellFactory(cellFactoryDelete);

        tableViewBoats.getColumns().add(colBtnDelete);
    }
}