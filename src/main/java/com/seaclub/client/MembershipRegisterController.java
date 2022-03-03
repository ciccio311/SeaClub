package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.MembershipRegister;
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
 * Class to manage the information and action about the view MembershipRegister
 */
public class MembershipRegisterController {
    private ClubMember clubMember;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tableViewRegister;

    /**
     * Method used to manage the pressing of BACK button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void backOnClick() throws IOException {
        if(clubMember.getDipendente()==1){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEmployee.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            MenuEmployeeController mec = fxmlLoader.getController();
            mec.setClubMember(this.clubMember);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 = (Stage) btnBack.getScene().getWindow();
            stage2.close();
        }
        else
        {
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
    }

    /**
     * Method used to set the club member for the view
     * @param cm is the club member
     */
    public void setClubMember(ClubMember cm){
        this.clubMember = cm;
        setTableView();
    }

    /**
     * Method used for setting the TableView information
     */
    private void setTableView(){
        tableViewRegister.getItems().clear();

        TableColumn<MembershipRegister, String> C1 = new TableColumn("ID SOCIO");
        C1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getIdClubMember())));
        C1.setStyle("-fx-alignment: CENTER;");
        TableColumn<MembershipRegister, String> C2 = new TableColumn("PAGAMENTO");
        C2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPaymentMethod()));
        C2.setStyle("-fx-alignment: CENTER;");
        TableColumn<MembershipRegister, String> C3 = new TableColumn(" DATA PAGAMENTO");
        C3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getDatePayment())));
        C3.setStyle("-fx-alignment: CENTER;");

        if(this.clubMember.getDipendente() == 1){
            try {
                //SE DIPENDENTE GET ALL MEMBERSHIP REGISTER
                C1.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(3));
                C2.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(3));
                C3.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(3));
                tableViewRegister.getColumns().addAll(C1, C2, C3);

                tableViewRegister.setItems((FXCollections.observableArrayList(Client.getInstance().getAllMembershipQuoteRegister())));
            }catch (Exception e){
                System.out.println(e.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading all membership quote register...try later!");
                alert.showAndWait();
            }
        }
        else {
            try {
                //SE club member GET MEMBERSHIP REGISTER BY CLUB MEMBER ID
                C2.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(2));
                C3.prefWidthProperty().bind(tableViewRegister.widthProperty().divide(2));
                tableViewRegister.getColumns().addAll(C2, C3);
                List<MembershipRegister> reg = new ArrayList<MembershipRegister>();
                for (var x : Client.getInstance().getAllMembershipQuoteRegister()) {
                    if (x.getIdClubMember() == this.clubMember.getId()) {
                        reg.add(x);
                    }
                }

                tableViewRegister.setItems((FXCollections.observableArrayList(reg)));
            }catch(Exception e){
                System.out.println(e.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error for downloading your membership quote register...try later!");
                alert.showAndWait();
            }
        }
    }
}