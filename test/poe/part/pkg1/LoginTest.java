/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginTest {

    private final Login login = new Login("Kyle", "Smith");

    @Test
    void usernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void usernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    void passwordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void passwordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void cellNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void cellNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    void localNumberWithoutInternationalCodeAccepted() {
        assertTrue(login.checkLocalNumber("0812345678"));
        assertTrue(login.checkCellPhoneNumber("0812345678"));
    }

    @Test
    void localNumberWrongLengthRejected() {
        assertFalse(login.checkLocalNumber("081234567"));
        assertFalse(login.checkLocalNumber("08123456789"));
        assertFalse(login.checkCellPhoneNumber("08174A1234"));
    }

    @Test
    void internationalNumberAccepted() {
        assertTrue(login.checkInternationalNumber("+27812345678"));
        assertTrue(login.checkCellPhoneNumber("+27812345678"));
    }

    @Test
    void internationalNumberWithoutPlusRejected() {
        assertFalse(login.checkInternationalNumber("27817421062"));
        assertFalse(login.checkCellPhoneNumber("27817421062"));
    }

    @Test
    void registerAcceptsLocalNumber() {
        assertTrue(login.registerUser("Kut_1", "Kutlw@n07#", "0817421062")
                .endsWith("registered successfully."));
    }

    @Test
    void loginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void loginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrong"));
    }
}