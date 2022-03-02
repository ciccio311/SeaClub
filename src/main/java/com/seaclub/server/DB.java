package com.seaclub.server;
import com.seaclub.Manager.*;
import com.seaclub.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    /* SQL DB ELEMENTS */
    private static final String DBURL = "jdbc:mysql://localhost:3306/circolovelico";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    //private static final String ARG = "createDatabaseIfNotExist=true&serverTimezone=UTC";

    private Statement stmt;
    private Connection conn;

    private static DB instance = null;

    private DB(){
        try{
            conn = DriverManager.getConnection(
                    DBURL, LOGIN, PASSWORD);
            stmt = (Statement) conn.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static DB getInstance() {
        if (instance == null)
            instance = new DB();
        return instance;
    }

    /**
     * Method used to get the whole Boat table
     */
    public void getBoats(){
        String selectString = "SELECT * FROM barca;";
        try {

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);

            List<Boat> boats = new ArrayList<Boat>();

            while(rset.next()) {
                Boat boat = new Boat(rset.getInt("IDBarca"),
                        rset.getString("Nome"),rset.getFloat("Lunghezza"),
                        rset.getInt("IDProprietario"));
                boats.add(boat);
            }

            BoatManager.getInstance().setBoats(boats);
            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get notification with specific id
     */
    public void getNotification(int id){

        try {

            String selectString = "SELECT * " +
                    "FROM notifica " +
                    "WHERE notifica.ID = "+id+";";

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);


            while(rset.next()) {
               Notification notification = new Notification(rset.getInt("ID"),rset.getString("Nome"));
               NotificationsRegisterManager.getInstance().setNotification(notification);
            }


            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method used to get the whole NotificationRegister table
     */
    public void getNotificatioinRegister(){
        String selectString = "SELECT * FROM registro_notifiche;";
        try {

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);

            List<NotificationsRegister> registers = new ArrayList<NotificationsRegister>();

            while(rset.next()) {

                NotificationsRegister notificationsRegister = new NotificationsRegister(rset.getInt("ID"),
                        rset.getString("Info"),rset.getDate("DataInvio"),
                        rset.getInt("IDSocio"),rset.getInt("IDNotifica"));
                registers.add(notificationsRegister);
            }

            NotificationsRegisterManager.getInstance().setRegisters(registers);

            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Method used to get the whole Storage Quote register table
     */
    public void getBoatStorageQuote(){
        String selectString = "SELECT * FROM registro_rimessaggi;";
        try {

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);

            List<StorageRegister> registers = new ArrayList<StorageRegister>();

            while(rset.next()) {
                StorageRegister storage = new StorageRegister(rset.getInt("IDRegistro_rim"),
                        rset.getInt("IDQuota_rim"),rset.getInt("IDBarca"),
                        rset.getInt("IDSocio"), rset.getString("Metodo_pag"),rset.getDate("Data_pagamento"),
                        rset.getFloat("Prezzo"));

                registers.add(storage);
            }

            StorageRegisterManager.getInstance().setRegisters(registers);

            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get Last row Storage Quote register table
     */
    public void getLastBoatStorageQuote(Boat boat){
        String selectString = "SELECT * " +
                "FROM registro_rimessaggi r " +
                "WHERE r.IDBarca=" +boat.getId()+" "+
                "ORDER BY r.Data_pagamento DESC " +
                "LIMIT 1;";
        try {

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);

            if(rset.next()) {
                StorageRegister storage = new StorageRegister(rset.getInt("IDRegistro_rim"),
                        rset.getInt("IDQuota_rim"), rset.getInt("IDBarca"),
                        rset.getInt("IDSocio"), rset.getString("Metodo_pag"),
                        rset.getDate("Data_pagamento"), rset.getFloat("Prezzo"));


                StorageRegisterManager.getInstance().setLastStorageRegister(storage);
            }
            else
                StorageRegisterManager.getInstance().setLastStorageRegister(null);

            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get the whole CompetitionRegister table
     */
    public void getCompetitionRegister(){
        String selectString = "SELECT * FROM registro_gare;";
        try {

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);

            List<CompetitionRegister> registers = new ArrayList<CompetitionRegister>();

            while(rset.next()) {
                CompetitionRegister competitionRegister = new CompetitionRegister(rset.getInt(""),rset.getInt(""),
                        rset.getInt(""),rset.getInt(""),rset.getString(""));

                registers.add(competitionRegister);
            }

            CompetitionRegisterManager.getInstance().setCompetitionRegisters(registers);

            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get the whole MembershipQuote table
     */
    public void getMembershipQuoteRegister(){
        String selectString = "SELECT * FROM registro_associazioni;";
        try {

            Statement stmt2 = (Statement) conn.createStatement();

            ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);

            List<MembershipRegister> registers = new ArrayList<MembershipRegister>();

            while(rset.next()) {
                MembershipRegister membershipRegister = new MembershipRegister(rset.getInt("IDRegistro_ass"),
                        rset.getInt("IDQuota_ass"),rset.getInt("IDSocio"),rset.getString("Metodo_pag"),
                        rset.getDate("Data_pagamento"));

               registers.add(membershipRegister);
            }

            MembershipRegisterManager.getInstance().setMembershipRegisters(registers);
            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get the whole competition table
     */
    public void getCompetition(){

        try {
            String selectString = "SELECT * FROM gara;";
            ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);

            List<Competition> competitions = new ArrayList<Competition>();

            while(rset.next()) {
                Competition competition = new Competition(rset.getInt("IDGara"),
                        rset.getFloat("Prezzo"),rset.getDate("Data"));
                competitions.add(competition);
            }

            CompetitionManager.getInstance().setCompetitionList(competitions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method used to get the whole clubMember table
     */
    public void getClubMembers() {
        String selectString = "SELECT * FROM socio;";
        try {

            ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);

            List<ClubMember> members = new ArrayList<ClubMember>();

            while(rset.next()) {
                ClubMember member = new ClubMember(rset.getInt("ID"),rset.getString("CF"),rset.getInt("Dipendente"),
                        rset.getString("Nome"), rset.getString("Cognome"),
                        rset.getString("Indirizzo"), rset.getString("Password"));
                member.setBoats(BoatManager.getInstance().getMemberBoatsFromIDMember(member));
                members.add(member);
            }

            ClubMemberManager.getInstance().setMembers(members);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewMember(ClubMember member){

        String insertSql = "insert into socio values (?,?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new member
            pstmt.setInt(1, member.getId());
            pstmt.setString(2, member.getCF());
            pstmt.setInt(3,  member.getDipendente());
            pstmt.setString(4, member.getName());
            pstmt.setString(5, member.getSurname());
            pstmt.setString(6, member.getAddress());
            pstmt.setString(7, member.getPassword());

            pstmt.execute();
            System.out.println("User added to DB => " + member.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewStorageRegister(StorageRegister storageRegister){

        String insertSql = "insert into registro_rimessaggi values (?,?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new storage register
            pstmt.setInt(1, storageRegister.getID());
            pstmt.setInt(2, storageRegister.getIdQuote());
            pstmt.setInt(3,  storageRegister.getIdBoat());
            pstmt.setInt(4,storageRegister.getIdClubMember());
            pstmt.setString(5, storageRegister.getPaymentMethod());
            pstmt.setDate(6, (Date) storageRegister.getDatePayment());
            pstmt.setFloat(7, storageRegister.getPrice());


            pstmt.execute();
            System.out.println("Storage register added to DB!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addNewBoat(Boat boat){
        try {
            String insertSql = "insert into barca values (?,?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new boat
            pstmt.setInt(1, boat.getId());
            pstmt.setString(2,  boat.getName());
            pstmt.setFloat(3, boat.getWidth());
            pstmt.setInt(4, boat.getIdClubMember());
            pstmt.execute();
            System.out.println("Boat added to DB => " + boat.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addNewCompetition(Competition competition){
        try {
            String insertSql = "insert into gara values (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new competition
            pstmt.setInt(1, competition.getId());
            pstmt.setFloat(2,  competition.getPrice());
            pstmt.setDate(3, (Date) competition.getDate());
            pstmt.execute();
            System.out.println("Competition added to DB => " + competition.getId());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addNewBoatInCompetition(CompetitionRegister compRegister){
        try {
            String insertSql = "insert into registro_gare values (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new competition register
            pstmt.setInt(1, compRegister.getID());
            pstmt.setInt(2,  compRegister.getIdCompetition());
            pstmt.setInt(3, compRegister.getIdBoat());
            pstmt.setInt(4, compRegister.getIdClubMember());
            pstmt.setString(5, compRegister.getPaymentMethod());
            pstmt.execute();
            System.out.println("Competition added to DB => " + compRegister.getID());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCompetitionRegisterByMemberId(int id){
        try {
            String insertSql = "SELECT gara.IDGara, gara.Data, barca.Nome\n" +
                    "FROM gara, socio, barca, registro_gare\n" +
                    "WHERE socio.ID = barca.IDProprietario AND socio.ID = registro_gare.IDSocio AND gara.IDGara = registro_gare.IDGara AND barca.IDBarca = registro_gare.IDBarca AND socio.ID = "+id+";";

            List<String> info = new ArrayList<String>();

            ResultSet rset = ((java.sql.Statement) stmt).executeQuery(insertSql);

            while(rset.next()) {
                String riga = rset.getInt("IDGara") + ", " + rset.getString("Data") + ", " + rset.getString("Nome");
                info.add(riga);
            }
            CompetitionManager.getInstance().setcRegister(info);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(ClubMember cm){
        try {
            String insertSql = "UPDATE socio SET Indirizzo = ?, Password = ? WHERE socio.ID="+cm.getId()+";";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new competition register
            pstmt.setString(1, cm.getAddress());
            pstmt.setString(2,  cm.getPassword());

            pstmt.execute();
            System.out.println("Update value in DB for user => " + cm.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param boat boat that will be eliminated
     */
    public void deleteBoat(Boat boat){
        try {
            String insertSql = "UPDATE barca SET IDProprietario = 1 WHERE barca.IDBarca="+boat.getId()+";";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            pstmt.execute();
            System.out.println("Delete boat in DB with id => " + boat.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMembershipRegisterQuote(MembershipRegister mr){
        try {
            String insertSql = "insert into registro_associazioni values (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new competition register
            pstmt.setInt(1, mr.getID());
            pstmt.setInt(2,  mr.getIDQuote());
            pstmt.setInt(3, mr.getIdClubMember());
            pstmt.setString(4, mr.getPaymentMethod());
            pstmt.setDate(5, (Date) mr.getDatePayment());
            pstmt.execute();
            System.out.println("Membership quote added to DB");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNotificationRegister(NotificationsRegister notificationsRegister){
        try {
            String insertSql = "insert into registro_notifiche values (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            //configure PreparedStatement with values of new Notification register
            pstmt.setInt(1,notificationsRegister.getId());
            pstmt.setString(2, notificationsRegister.getInfo());
            pstmt.setDate(3, (Date) notificationsRegister.getDateSender());
            pstmt.setInt(4, notificationsRegister.getIdMember());
            pstmt.setInt(5, notificationsRegister.getIdNotification());

            pstmt.execute();
            System.out.println("Notification register added to DB!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNotificationRegisterStorage(int idSocio, String info){
        try {
            String insertSql = "UPDATE registro_notifiche SET Info = '"+info+"' WHERE registro_notifiche.IDSocio="+idSocio+" AND registro_notifiche.IDNotifica=2;";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            pstmt.execute();
            System.out.println("Update notification!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNotificationRegisterMembership(int idSocio){
        try {
            String insertSql = "DELETE FROM registro_notifiche WHERE registro_notifiche.IDSocio="+idSocio+" AND registro_notifiche.IDNotifica=1;";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            pstmt.execute();
            System.out.println("Delete notification membership for id => " + idSocio);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteNotificationRegisterStorage(int idSocio){
        try {
            String insertSql = "DELETE FROM registro_notifiche WHERE registro_notifiche.IDSocio="+idSocio+" AND registro_notifiche.IDNotifica=2;";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            pstmt.execute();
            System.out.println("Delete notification storage for id => " + idSocio);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
