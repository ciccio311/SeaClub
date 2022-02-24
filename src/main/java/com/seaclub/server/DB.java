package com.seaclub.server;
import com.seaclub.Model.ClubMember;

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
            System.out.println("CONNESSO!");

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
                ClubMember member = new ClubMember(rset.getString("CF"),rset.getInt("Dipendente"),
                        rset.getString("Nome"), rset.getString("Cognome"),
                        rset.getString("Indirizzo"), rset.getString("Password"));
                members.add(member);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
