package com.seaclub.client;

import com.seaclub.Manager.ClubMemberManager;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.StorageRegister;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the information and action about the view UserList
 */
public class UserListController {
    private ClubMember clubMember;

    @FXML
    private TextField idSearchTextFIeld;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewUsers;

    /**
     * Method used to go back to the previous one view
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
     * First method for initialize the view.
     * @param clubMember the member that go into this view
     */
    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        setTableView();
    }

    /**
     * Method used to set TableView cells
     */
    private void setTableView(){
        try{
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
        }catch(Exception e){
            System.out.println(e.toString());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading all club member...");
            alert.showAndWait();
        }
    }

    /**
     * Method used to search for information about a specific user
     */
    @FXML
    protected void searchOnClick(){
        if(idSearchTextFIeld.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert users id!");
            alert.showAndWait();
        }else {
            try {
                tableViewUsers.getItems().clear();
                ClubMember clubMemberToSearch = new ClubMember();
                clubMemberToSearch.setId(Integer.valueOf(idSearchTextFIeld.getText()));
                clubMemberToSearch = Client.getInstance().updateClubMember(clubMemberToSearch);
                if(clubMemberToSearch!=null)
                    tableViewUsers.getItems().add(clubMemberToSearch);
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Doesn't exist an User with id: "+idSearchTextFIeld.getText());
                    alert.showAndWait();
                }
            }catch(Exception e){
                System.out.println(e.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                alert.showAndWait();
            }

        }
    }
}