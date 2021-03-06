package com.seaclub.Model;

import com.seaclub.client.Client;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * The class {@code ClubMember} provides an implementation of a
 * Member of secific Club.
 *
 **/
public class ClubMember implements Serializable {
    private int id;
    private String CF;
    private int dipendente;
    private String name;
    private String surname;
    private String address;
    private String password;
    private List<Boat> boats;


    /**
     * First class constructor.
     *
     **/
    public ClubMember(){
        this.boats = new ArrayList<Boat>();
    }

    /**
     * Second class constructor.
     * @param cf the member's id.
     * @param dipendente 0 if he is only a club member, 1 if he is an employee
     * @param name the member's name.
     * @param surname the member's surname.
     * @param addres the member's address.
     * @param pswd the member's password.
     **/
    public  ClubMember(int id,String cf, int dipendente, String name, String surname, String addres, String pswd){
        this.id = id;
        this.CF = cf;
        this.dipendente = dipendente;
        this.name = name;
        this.surname = surname;
        this.address = addres;
        this.password = pswd;
        this.boats = new ArrayList<Boat>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * Sets the member's name.
     *
     * @param name the name.
     *
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the member's name.
     *
     * @return the name.
     *
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the type of the club member.
     *
     * @param dip 0 or 1.
     *
     **/
    public void setDipendente(int dip) {
        this.dipendente = dip;
    }

    /**
     * Gets the type of the club member.
     *
     * @return the 0 or 1.
     *
     **/
    public int getDipendente() {
        return dipendente;
    }

    /**
     * Gets the member's address.
     *
     * @return the address.
     *
     **/
    public String getAddress() {
        return address;
    }


    /**
     * Gets the member's id.
     *
     * @return the id.
     *
     **/
    public String getCF() {
        return CF;
    }


    /**
     * Gets the member's password.
     *
     * @return the password.
     *
     **/
    public String getPassword() {
        return password;
    }


    /**
     * Gets the member's surname.
     *
     * @return the surname.
     *
     **/
    public String getSurname() {
        return surname;
    }


    /**
     * Sets the member's address.
     *
     * @param address the address.
     *
     **/
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Sets the member's id.
     *
     * @param CF the id.
     *
     **/
    public void setCF(String CF) {
        this.CF = CF;
    }


    /**
     * Sets the member's password.
     *
     * @param password the password.
     *
     **/
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Sets the member's surname.
     *
     * @param surname the surname.
     *
     **/
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * Gets the member's boats.
     *
     * @return the boats.
     *
     **/
    public List<Boat> getBoats() {
        return boats;
    }

    /**
     * Sets the member's boats.
     *
     * @param boats the boats.
     *
     **/
    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    public MembershipRegister getLastPaymentQuote(List<MembershipRegister> registers){
        try {
            List<MembershipRegister> myMembershipRegister = new ArrayList<MembershipRegister>();

            for (var x : registers) {
                if (x.getIdClubMember() == id) {
                    myMembershipRegister.add(x);
                }
            }
            return myMembershipRegister.get(myMembershipRegister.size() - 1);
        }catch(Exception e){
            System.out.println(e);
            return  null;
        }
    }

    public Boat getBoatById(int id){
        for(var x:boats){
            if(x.getId()==id)
                return x;
        }
        return null;
    }

    public List<Boat> getBoatExpired(){

        List<Boat> expiredBoat = new ArrayList<Boat>();

        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate now = LocalDate.now();
        LocalDate dateMinusYear = now.minusYears(1);
        Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
        StorageRegister storageRegister = new StorageRegister();

        for(var boat:boats){
            storageRegister = Client.getInstance().getLastStorageRegister(boat);
            if(storageRegister!=null){
                if(storageRegister.getDatePayment().before(dateNow)){
                    //expired
                    expiredBoat.add(boat);
                }
            }else
                expiredBoat.add(boat);
        }
        return expiredBoat;

    }

    public List<Boat> getBoatAvailabe(){

        List<Boat> validBoat = new ArrayList<Boat>();

        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate now = LocalDate.now();
        LocalDate dateMinusYear = now.minusYears(1);
        Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
        StorageRegister storageRegister = new StorageRegister();

        for(var boat:boats){
            storageRegister = Client.getInstance().getLastStorageRegister(boat);
            if(storageRegister!=null){
                if(!(storageRegister.getDatePayment().before(dateNow))){
                    //expired
                    validBoat.add(boat);
                }
            }
        }
        return validBoat;

    }

    public Integer isMembershipQuoteExpired(){
        MembershipRegister membershipRegister = getLastPaymentQuote(Client.getInstance().getAllMembershipQuoteRegister());
        if(membershipRegister != null) {
            //default time zone
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate now = LocalDate.now();
            LocalDate dateMinusYear = now.minusYears(1);
            Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());
            if (membershipRegister.getDatePayment().before(dateNow)) {
                //expired
                return 1;
            }
            else
                return 0;
        }else
            return null;
    }
}
