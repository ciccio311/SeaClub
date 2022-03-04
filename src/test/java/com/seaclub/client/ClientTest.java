package com.seaclub.client;

import com.seaclub.Model.ClubMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void getInstance() {
    }

    @Test
    void addMember() {
        ClubMember testMember = new ClubMember();
        testMember.setName("primo");
        testMember.setSurname("primo");
        testMember.setDipendente(0);
        testMember.setAddress("primo");
        testMember.setCF("primo");
        testMember.setPassword("1");

        assertTrue(Client.getInstance().addMember(testMember));
    }

    @Test
    void deleteBoat() {
    }

    @Test
    void addStorageRegister() {
    }

    @Test
    void sendNotificationBoatExpired() {
    }

    @Test
    void sendNotificationMembershipExpired() {
    }

    @Test
    void addNotificationRegister() {
    }

    @Test
    void login() {
    }

    @Test
    void getMemberByCF() {
    }

    @Test
    void getNotification() {
    }

    @Test
    void getLastStorageRegister() {
    }

    @Test
    void getMemberExpired() {
    }

    @Test
    void updateClubMember() {
    }

    @Test
    void getAllCompetition() {
    }

    @Test
    void getAllStorageRegisterQuote() {
    }

    @Test
    void getAllCompetitionRegister() {
    }

    @Test
    void getAllMembershipQuoteRegister() {
    }

    @Test
    void getCompetitionRegisterByIdComp() {
    }

    @Test
    void getCompetitionAvailable() {
    }

    @Test
    void addNewBoat() {
    }

    @Test
    void addNewCompetition() {
    }

    @Test
    void addBoatToCompetition() {
    }

    @Test
    void getCompetitionRegisterByMemberId() {
    }

    @Test
    void updateMemberInfo() {
    }

    @Test
    void addMembershipRegisterQuote() {
    }

    @Test
    void updateNotificationStorage() {
    }

    @Test
    void deleteNotificationMembership() {
    }

    @Test
    void getAllClubMember() {
    }

    @Test
    void getAllBoats() {
    }

    @Test
    void getAllCompetitions() {
    }
}