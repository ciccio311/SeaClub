package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.NotificationsRegister;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the information and action about the view MENUEMPLOYEE
 */
public class MenuEmployeeController {
    private ClubMember clubMember;

    private boolean check;

    @FXML
    private Button btnLogout;

    @FXML
    private Button buttonUsers;

    @FXML
    private Button buttonBoats;

    @FXML
    private Button buttonCompetitions;

    @FXML
    private Button buttonNotification;

    @FXML
    private Button buttonMembershipList;

    @FXML
    private Button buttonStorageList;

    @FXML
    private Button buttonCompetitionRegisterList;

    /**
     * Method used to manage the pressing of LOGOUT button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void logoutOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of USERS button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void buttonUsersOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserList.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        UserListController ulc = fxmlLoader.getController();
        ulc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of BOATS button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void buttonBoatsOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoatList.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        BoatListController blc = fxmlLoader.getController();
        blc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of COMPETITIONS button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void buttonCompetitionsOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CompetitionList.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        CompetitionListController clc = fxmlLoader.getController();
        clc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of NOTIFICATION button
     */
    @FXML
    protected void buttonNotificationOnClick(){
        /*List<ClubMember> memberExpired = new ArrayList<ClubMember>();
        memberExpired = Client.getInstance().getMemberExpired();
        if(!(memberExpired.size()==0) && memberExpired!=null) {
            for (var x : memberExpired) {
                NotificationsRegister notificationsRegister = new NotificationsRegister();
                notificationsRegister.setIdNotification(1);
                notificationsRegister.setIdMember(x.getId());
                notificationsRegister.setInfo("");
                Date date = new Date(System.currentTimeMillis());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                notificationsRegister.setDateSender(sqlDate);

                check=Client.getInstance().addNotificationRegister(notificationsRegister);
            }

            if (check) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Notifications membership send!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                alert.showAndWait();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There aren't member with quote expired!");
            alert.showAndWait();
        }*/
        boolean notifyBoatExpired = Client.getInstance().SendNotificationBoatExpired();
        boolean notifyMembershipExpired = Client.getInstance().SendNotificationMembershipExpired();
        Alert alert;
        if(notifyMembershipExpired){
            alert = new Alert(Alert.AlertType.INFORMATION, "All new notifications membership send!");
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR, "Membership notification NOT send!");
        }
        alert.showAndWait();
        if(notifyBoatExpired){
            alert = new Alert(Alert.AlertType.INFORMATION, "All new notifications boat expired send!");
            alert.showAndWait();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR, "Boat notification NOT send!");
            alert.showAndWait();
        }
    }

    /**
     * Method used to manage the pressing of MEMBERSHIP LIST button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void buttonMembershipListOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MembershipRegister.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        MembershipRegisterController mrc = fxmlLoader.getController();
        mrc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of STORAGE LIST button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void buttonStorageListOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StorageRegister.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        StorageRegisterController src = fxmlLoader.getController();
        src.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to manage the pressing of COMPETITION REGISTER LIST button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected void buttonCompetitionRegisterListOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CompetitionRegisterList.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        CompetitionRegisterListController crlc = fxmlLoader.getController();
        crlc.setClubMember(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method used to set the club member for the view
     * @param clubMember is the club member
     */
    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
    }


    /**
     * Method used to manage the pressing of Add new employee button
     * @throws IOException create Input/Output exception
     */
    @FXML
    protected  void addEmployeeOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sign up.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        SignUpController src = fxmlLoader.getController();
        src.setClubMemberDip(clubMember);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) btnLogout.getScene().getWindow();
        stage2.close();
    }
}