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

/**
 * Class to manage the information and action about the view Boat
 */
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

    /**
     * Method used to manage the pressing of ADD button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void addOnClick()throws IOException {
        if(name.getText().length()==0 || width.getText().length()==0 || Float.valueOf(width.getText())<1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields correct!");
            alert.showAndWait();
        }
        else if(name.getText().length()>15 || Float.valueOf(width.getText())>100.00){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fields too long!");
            alert.showAndWait();
        }
        else
        {
            try {
                Boat b = new Boat();
                b.setName(name.getText());
                b.setWidth(Float.valueOf(width.getText()));
                b.setIdClubMember(this.clubMember.getId());
                if(Client.getInstance().addNewBoat(b)) {

                    this.clubMember = Client.getInstance().updateClubMember(clubMember);
                    if(this.clubMember!=null) {
                        tableViewBoats.setItems(FXCollections.observableList((this.clubMember.getBoats())));
                        tableViewBoats.refresh();

                        name.setText("");
                        width.setText("");
                    }else{
                        throw new Exception("Info not updated... try later!");
                    }
                }else
                    throw new Exception("Something went wrong... try later!");
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Insert length with dot!");
                alert.showAndWait();
            }
        }
    }

    /**
     * Method used to manage the pressing of BACK button
     * @throws IOException create Input/Output exception
     */
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

    /**
     * Method used to set the club member for the view
     * @param cm is the club member
     */
    public void setClubMember(ClubMember cm){
        this.clubMember = cm;

        TableColumn<Boat, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        column1.setStyle("-fx-alignment: CENTER;");
        column1.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(3));

        TableColumn<Boat, String> column2 = new TableColumn<>("Width");
        column2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getWidth())));
        column2.setStyle("-fx-alignment: CENTER;");
        column2.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(3));

        tableViewBoats.getColumns().add(column1);
        tableViewBoats.getColumns().add(column2);

        addButtonsToTable(tableViewBoats);

        tableViewBoats.setItems(FXCollections.observableList((cm.getBoats())));

    }

    /**
     * Method used to add the DELETE button in the table view
     * @param tableViewBoats is the table view
     */
    private void addButtonsToTable(TableView<Boat> tableViewBoats) {
        TableColumn<Boat, Void> colBtnDelete = new TableColumn("Delete");
        colBtnDelete.setStyle("-fx-alignment: CENTER;");
        colBtnDelete.prefWidthProperty().bind(tableViewBoats.widthProperty().divide(3));

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

                                if(Client.getInstance().deleteBoat(boat)){
                                    clubMember.getBoats().remove(boat);;
                                    if(Client.getInstance().updateNotificationStorage(clubMember)) {
                                        tableViewBoats.getItems().remove(boat);
                                        tableViewBoats.refresh();
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Boat deleted!");
                                        alert.showAndWait();
                                    }else{
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Notificatioin not update...");
                                        alert.showAndWait();
                                    }
                                }else{
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                                    alert.showAndWait();
                                }
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