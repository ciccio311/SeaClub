package com.seaclub.client;

import com.seaclub.Model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionController {
    private ClubMember clubMember;
    private Boat boatSelected = new Boat();
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

    @FXML
    protected void OnClickDate(){
        String selectedItem = (String) DateComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            String words[] = selectedItem.split(" ");
            RacePriceLabel.setText(words[2]);
            competitionSelected.setId(Integer.valueOf(words[0]));
        }
    }

    @FXML
    protected void OnClickBoat(){
        String selectedItem = (String) BoatComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            String words[] = selectedItem.split(" ");
            boatSelected.setId(Integer.valueOf(words[0]));
        }
    }

    @FXML
    protected void SubmitOnClick(){
        if(DateComboBox.getSelectionModel().isEmpty() || BoatComboBox.getSelectionModel().isEmpty() ||
                (!CardRadioButton.isSelected()) && !BanckTransferRadioButton.isSelected()
                ||
                (CardRadioButton.isSelected()) && BanckTransferRadioButton.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert all fields!");
            alert.showAndWait();
        }else{
            CompetitionRegister competitionRegister = new CompetitionRegister();
            competitionRegister.setIdBoat(boatSelected.getId());
            competitionRegister.setIdCompetition(competitionSelected.getId());
            competitionRegister.setIdClubMember(clubMember.getId());
            if(CardRadioButton.isSelected())
                competitionRegister.setPaymentMethod("Card");
            if(BanckTransferRadioButton.isSelected())
                competitionRegister.setPaymentMethod("Bank transfer");
            Client.getInstance().addBoatToCompetition(competitionRegister);

            DateComboBox.valueProperty().set(null);
            BoatComboBox.valueProperty().set(null);
            CardRadioButton.setSelected(false);
            BanckTransferRadioButton.setSelected(false);
            RacePriceLabel.setText("NA");

            setListView();
            tableViewCompetition.refresh();
            setView();
        }
    }

    private void setListView(){
        tableViewCompetition.getItems().clear();

        register = new ArrayList<String>();
        register = Client.getInstance().getCompetitionRegisterByMemberId(this.clubMember.getId());


        tableViewCompetition.setItems((FXCollections.observableArrayList(register)));

    }

    private List<Competition> getCompetitionAvailable(List<Competition> list){
        List<Integer> ids = new ArrayList<Integer>();
        for(var x:register){
            String word[] = x.split(", ");
            ids.add(Integer.valueOf(word[0]));
        }

        for(var id:ids){
            list.removeIf(n -> n.getId()==id);
        }

        return list;
    }

    public void setView(){
        try{
            DateComboBox.getItems().clear();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            setListView();
            List<Competition> competitions = new ArrayList<Competition>();
            competitions = getCompetitionAvailable(Client.getInstance().getAllCompetition());

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

            for (var boat : clubMember.getBoats()) {
                String boatInfo = boat.getId() + " " + boat.getName();
                BoatComboBox.getItems().add(boatInfo);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String split(String s, int i){
        String word[] = s.split(", ");
        return word[i];
    }

}