package com.seaclub.client;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
import com.seaclub.Model.StorageRegister;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class to manage the information and action about the view CompetitionList
 */
public class CompetitionListController {
    private ClubMember clubMember;

    @FXML
    private TextField priceTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewCompetitions;

    /**
     * Method used to manage the pressing of BACK button
     * @throws IOException create Input/Output exception
     */
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

    /**
     * Method used to set the club member for the view
     * @param clubMember is the club member
     */
    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        setTableView();
        setDate();


    }

    /**
     * Method used for adding new competition
     */
    @FXML
    protected void addOnClick(){
        if(datePicker.getValue() == null || datePicker.getValue().toString().equals("") || priceTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }
        else if(Float.parseFloat(priceTextField.getText()) > 10000.00){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price too high!");
            alert.showAndWait();
        }
        else{
            try {
                Competition competition = new Competition();
                //get value from DatePicker
                LocalDate dateFromDatePicker = datePicker.getValue();
                //Convert in Date value
                Date dateCorrect = Date.from(dateFromDatePicker.atStartOfDay(ZoneId.systemDefault()).toInstant());
                //Convert in sqlDate value
                java.sql.Date sqlDate = new java.sql.Date(dateCorrect.getTime());

                competition.setDate(sqlDate);
                competition.setPrice(Float.valueOf(priceTextField.getText()));
                if(Client.getInstance().addNewCompetition(competition)){
                    setTableView();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Competition NOT added! Try later");
                    alert.showAndWait();
                }
            }catch (Exception e){
                System.out.println("Error: "+e.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Enter the correct information!");
                alert.showAndWait();
            }
        }
    }

    /**
     * Method used to set the minimum date of DatePicker and the current date
     */
    private void setDate(){
        LocalDate now = LocalDate.now();
        datePicker.setDayCellFactory(datePicker1 -> new DateCell() {
            @Override public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(now));
            }});
        datePicker.setValue(now);
    }

    /**
     * Method used for setting the TableView information
     */
    private void setTableView(){
        try {
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
        }catch(Exception e){
            System.out.println(e.toString());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading all competition... try later");
            alert.showAndWait();
        }
    }
}
