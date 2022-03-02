package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.CompetitionRegister;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionRegisterListController {
    private ClubMember clubMember;

    @FXML
    private TextField idSearchTextFIeld;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewCompetitionsRegister;

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
        tableViewCompetitionsRegister.getItems().clear();

        TableColumn<CompetitionRegister, String> C1 = new TableColumn("ID REGISTRO GARE");
        C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        C1.setStyle("-fx-alignment: CENTER;");
        TableColumn<CompetitionRegister, String> C2 = new TableColumn("ID GARA");
        C2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdCompetition())));
        C2.setStyle("-fx-alignment: CENTER;");
        TableColumn<CompetitionRegister, String> C3 = new TableColumn("ID BARCA");
        C3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdBoat())));
        C3.setStyle("-fx-alignment: CENTER;");
        TableColumn<CompetitionRegister, String> C4 = new TableColumn("ID SOCIO");
        C4.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdClubMember())));
        C4.setStyle("-fx-alignment: CENTER;");
        TableColumn<CompetitionRegister, String> C5 = new TableColumn("METODO PAGAMENTO");
        C5.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPaymentMethod())));
        C5.setStyle("-fx-alignment: CENTER;");

        C1.prefWidthProperty().bind(tableViewCompetitionsRegister.widthProperty().divide(5));
        C2.prefWidthProperty().bind(tableViewCompetitionsRegister.widthProperty().divide(5));
        C3.prefWidthProperty().bind(tableViewCompetitionsRegister.widthProperty().divide(5));
        C4.prefWidthProperty().bind(tableViewCompetitionsRegister.widthProperty().divide(5));
        C5.prefWidthProperty().bind(tableViewCompetitionsRegister.widthProperty().divide(5));
        C5.prefWidthProperty().bind(tableViewCompetitionsRegister.widthProperty().divide(5));
        tableViewCompetitionsRegister.getColumns().addAll(C1, C2, C3, C4, C5);

        tableViewCompetitionsRegister.setItems((FXCollections.observableArrayList(Client.getInstance().getAllCompetitionRegister())));
    }

    /**
     * Method used to search subscribers register to a specific competition
     */
    @FXML
    protected void searchOnClick(){
        if(idSearchTextFIeld.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert competition's id!");
            alert.showAndWait();
        }else {
            try {
                List<CompetitionRegister> competitionRegisters = new ArrayList<CompetitionRegister>();
                competitionRegisters = Client.getInstance().getCompetitionRegisterByIdComp(Integer.valueOf(idSearchTextFIeld.getText()));
                if(competitionRegisters!=null && competitionRegisters.size() > 0) {
                    tableViewCompetitionsRegister.getItems().clear();
                    tableViewCompetitionsRegister.setItems((FXCollections.observableArrayList(competitionRegisters)));
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Doesn't exist a competition with id: "+idSearchTextFIeld.getText());
                    alert.showAndWait();
                }
            }catch(Exception e){
                System.out.println(e.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Enter the correct information!");
                alert.showAndWait();
            }

        }
    }
}
