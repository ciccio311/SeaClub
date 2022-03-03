package com.seaclub.client;

import com.seaclub.Model.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to manage the information and action about the view Competition
 */
public class CompetitionController {
    private ClubMember clubMember;
    private final Boat boatSelected = new Boat();
    private Competition competitionSelected = new Competition();
    private ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private Label RacePriceLabel;

    @FXML
    private ComboBox DateComboBox;

    @FXML
    private ComboBox BoatComboBox;

    @FXML
    private RadioButton BanckTransferRadioButton;

    @FXML
    private RadioButton CardRadioButton;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewCompetition;

    private List<String> register;

    /**
     * Method used to set the club member for the view
     * @param clubMember is the club member
     */
    public void setClubMember(ClubMember clubMember) {
        try {
            this.clubMember = clubMember;
            TableColumn<String, String> C1 = new TableColumn("ID");
            C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(split(c.getValue(), 0))));
            C1.setStyle("-fx-alignment: CENTER;");
            TableColumn<String, String> C2 = new TableColumn("DATE");
            C2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(split(c.getValue(), 1))));
            C2.setStyle("-fx-alignment: CENTER;");
            TableColumn<String, String> C3 = new TableColumn("BOAT");
            C3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(split(c.getValue(), 2))));
            C3.setStyle("-fx-alignment: CENTER;");

            C1.prefWidthProperty().bind(tableViewCompetition.widthProperty().divide(3));
            C2.prefWidthProperty().bind(tableViewCompetition.widthProperty().divide(3));
            C3.prefWidthProperty().bind(tableViewCompetition.widthProperty().divide(3));

            tableViewCompetition.getColumns().addAll(C1, C2, C3);
            setView();
        }
        catch(Exception e){

            System.out.println(e);
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
     * Method used to manage the pressing of DATE combo box
     */
    @FXML
    protected void OnClickDate(){
        String selectedItem = (String) DateComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            String words[] = selectedItem.split(" ");
            RacePriceLabel.setText(words[2]);
            competitionSelected.setId(Integer.valueOf(words[0]));
        }
    }

    /**
     * Method used to manage the pressing of BOAT combo box
     */
    @FXML
    protected void OnClickBoat(){
        String selectedItem = (String) BoatComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            String words[] = selectedItem.split(" ");
            boatSelected.setId(Integer.valueOf(words[0]));
        }
    }

    /**
     * Method used to manage the pressing of SUBMIT button
     */
    @FXML
    protected void SubmitOnClick() {
        Integer checkQuote = clubMember.isMembershipQuoteExpired();
        if (checkQuote!=null){
            if(checkQuote==0) {

                if (DateComboBox.getSelectionModel().isEmpty() || BoatComboBox.getSelectionModel().isEmpty() ||
                        (!CardRadioButton.isSelected()) && !BanckTransferRadioButton.isSelected()
                        ||
                        (CardRadioButton.isSelected()) && BanckTransferRadioButton.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
                    alert.showAndWait();
                } else {
                    CompetitionRegister competitionRegister = new CompetitionRegister();
                    competitionRegister.setIdBoat(boatSelected.getId());
                    competitionRegister.setIdCompetition(competitionSelected.getId());
                    competitionRegister.setIdClubMember(clubMember.getId());
                    if (CardRadioButton.isSelected())
                        competitionRegister.setPaymentMethod("Card");
                    if (BanckTransferRadioButton.isSelected())
                        competitionRegister.setPaymentMethod("Bank transfer");
                    if(Client.getInstance().addBoatToCompetition(competitionRegister)) {

                        DateComboBox.valueProperty().set(null);
                        BoatComboBox.valueProperty().set(null);
                        CardRadioButton.setSelected(false);
                        BanckTransferRadioButton.setSelected(false);
                        RacePriceLabel.setText("NA");

                        setTableView();
                        tableViewCompetition.refresh();
                        setView();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Boat NOT registered! Try later...");
                        alert.showAndWait();
                    }
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Membership quote expired!");
                alert.showAndWait();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to pay membership quote!");
            alert.showAndWait();
        }
    }

    /**
     * Method used to set TableView cells
     */
    private void setTableView(){
        tableViewCompetition.getItems().clear();

        register = new ArrayList<String>();

        register = Client.getInstance().getCompetitionRegisterByMemberId(this.clubMember.getId());

        if(register!=null)
            tableViewCompetition.setItems((FXCollections.observableArrayList(register)));
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading your competition register... try later");
            alert.showAndWait();
        }

    }

    /**
     * Method used to set parameters of the view
     */
    public void setView(){
        try{
            DateComboBox.getItems().clear();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            setTableView();
            List<Competition> competitions = new ArrayList<Competition>();
            competitions = Client.getInstance().getCompetitionAvailable(register);
            if(competitions!=null) {
                //default time zone
                ZoneId defaultZoneId = ZoneId.systemDefault();

                LocalDate now = LocalDate.now();

                Date dateNow = Date.from(now.atStartOfDay(defaultZoneId).toInstant());

                for (var comp : competitions) {
                    if (comp.getDate().after(dateNow)) {
                        String info = comp.getId() + " " + dateFormat.format(comp.getDate()) + " " + comp.getPrice() + "€";
                        DateComboBox.getItems().add(info);
                    }
                }
                BoatComboBox.getItems().clear();
                for (var boat : clubMember.getBoatAvailabe()) {
                    String boatInfo = boat.getId() + " " + boat.getName();
                    BoatComboBox.getItems().add(boatInfo);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading your available competition... try later");
                alert.showAndWait();
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Method used to split a string
     * @param s string which we want to split
     * @param i which parameter of the split result to return
     * @return the required split value
     */
    private String split(String s, int i){
        String word[] = s.split(", ");
        return word[i];
    }

}