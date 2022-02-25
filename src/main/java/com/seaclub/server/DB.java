package com.seaclub.server;
import com.seaclub.Manager.BoatManager;
import com.seaclub.Manager.ClubMemberManager;
import com.seaclub.Manager.CompetitionManager;
import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        /*
        *
        * SELECT socio.CF, socio.Dipendente, socio.Nome, socio.Cognome, socio.Indirizzo, socio.Password
FROM socio,quota_associazione,registro_associazioni
WHERE socio.CF = registro_associazioni.IDSocio AND quota_associazione.IDQuota_ass = registro_associazioni.IDQuota_ass AND DATEDIFF(NOW(),registro_associazioni.Data_pagamento)<365; * */

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


}
