package com.seaclub.client;

import com.seaclub.Model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used for testing Client
 */
class ClientTest {

    private ClubMember testcm = new ClubMember();

    /**
     * test if the notification boat expired are sent
     */
    @Test
    void sendNotificationBoatExpired() {
        assertTrue(Client.getInstance().SendNotificationBoatExpired());
    }

    /**
     * test if the notification membership quote are sent
     */
    @Test
    void sendNotificationMembershipExpired() {
        assertTrue(Client.getInstance().SendNotificationMembershipExpired());
    }

    /**
     * test if the login with the clubmember 1 (DEFAULT) was successfully
     */
    @Test
    void login() {
        testcm.setPassword("1");
        testcm.setId(1);
        assertInstanceOf(testcm.getClass(), Client.getInstance().login(testcm));
    }

    /**
     * test if the method getMemberByCF with the clubmember 1 (DEFAULT) was successfully
     */
    @Test
    void getMemberByCF() {
        testcm.setCF("X");
        assertInstanceOf(testcm.getClass(), Client.getInstance().getMemberByCF(testcm));
    }

    /**
     * test if the method getNotification with the clubmember 1 was successfully
     */
    @Test
    void getNotification() {
        assertInstanceOf(String.class, Client.getInstance().getNotification(1));
    }

    /**
     * test if the method getLastStorageRegister with boat ID = 13 (DEFAULT) was successfully
     */
    @Test
    void getLastStorageRegister() {
        StorageRegister st = new StorageRegister();
        Boat bt = new Boat();
        bt.setId(13);
        assertInstanceOf(st.getClass(), Client.getInstance().getLastStorageRegister(bt));
    }

    /**
     * test if the method getMemberExpired was successfully
     */
    @Test
    void getMemberExpired() {
        List<ClubMember> tcb = new ArrayList<ClubMember>();
        assertInstanceOf(tcb.getClass(), Client.getInstance().getMemberExpired());
    }


    /**
     * test if the method getAllStorageRegisterQuote was successfully
     */
    @Test
    void getAllStorageRegisterQuote() {
        List<StorageRegister> tsr = new ArrayList<StorageRegister>();
        assertInstanceOf(tsr.getClass(), Client.getInstance().getAllStorageRegisterQuote());
    }

    /**
     * test if te method getAllCompetitionRegister was successfully
     */
    @Test
    void getAllCompetitionRegister() {
        List<CompetitionRegister> tcr = new ArrayList<CompetitionRegister>();
        assertInstanceOf(tcr.getClass(), Client.getInstance().getAllCompetitionRegister());
    }

    /**
     * test if the method getAllMembershipQuoteRegister was successfully
     */
    @Test
    void getAllMembershipQuoteRegister() {
        List<MembershipRegister> tmr = new ArrayList<MembershipRegister>();
        assertInstanceOf(tmr.getClass(), Client.getInstance().getAllMembershipQuoteRegister());
    }

    /**
     * test if the method getCompetitionRegisterByIdComp was successfully with parameter 1 DEFAULT
     */
    @Test
    void getCompetitionRegisterByIdComp() {
        List<CompetitionRegister> tcr = new ArrayList<CompetitionRegister>();
        assertInstanceOf(tcr.getClass(), Client.getInstance().getCompetitionRegisterByIdComp(1));
    }

    /**
     * test if the method getCompetitionRegisterByMemberId was successfully with id club member=1 DEFAULT
     */
    @Test
    void getCompetitionRegisterByMemberId() {
        List<String> ts = new ArrayList<String>();
        assertInstanceOf(ts.getClass(), Client.getInstance().getCompetitionRegisterByMemberId(1));
    }

    /**
     * test if the method getAllClubMember was successfully
     */
    @Test
    void getAllClubMember() {
        List<ClubMember> tcm = new ArrayList<ClubMember>();
        assertInstanceOf(tcm.getClass(), Client.getInstance().getAllClubMember());
    }

    /**
     * test if te method getAllBoats was successfully
     */
    @Test
    void getAllBoats() {
        List<Boat> tb = new ArrayList<Boat>();
        assertInstanceOf(tb.getClass(), Client.getInstance().getAllBoats());
    }

    /**
     * test if the method getAllCompetitions was successfully
     */
    @Test
    void getAllCompetitions() {
        List<Competition> tc = new ArrayList<Competition>();
        assertInstanceOf(tc.getClass(), Client.getInstance().getAllCompetitions());
    }
}