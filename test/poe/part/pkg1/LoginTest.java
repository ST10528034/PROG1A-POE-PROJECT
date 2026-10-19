/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginTest {

    private final Login login = new Login("Kyle", "Smith");

    @Test
    public void usernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void usernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void passwordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void passwordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void cellNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void cellNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void localNumberWithoutInternationalCodeAccepted() {
        assertTrue(login.checkLocalNumber("0817421062"));
        assertTrue(login.checkCellPhoneNumber("0817421062"));
    }

    @Test
    public void localNumberWrongLengthRejected() {
        assertFalse(login.checkLocalNumber("081742106"));
        assertFalse(login.checkLocalNumber("08174210622"));
        assertFalse(login.checkCellPhoneNumber("08174A1062"));
    }

    @Test
    public void internationalNumberAccepted() {
        assertTrue(login.checkInternationalNumber("+27817421062"));
        assertTrue(login.checkCellPhoneNumber("+27817421062"));
    }

    @Test
    public void internationalNumberWithoutPlusRejected() {
        assertFalse(login.checkInternationalNumber("27817421062"));
        assertFalse(login.checkCellPhoneNumber("27817421062"));
    }

    @Test
    public void registerAcceptsLocalNumber() {
        assertTrue(login.registerUser("Kut_1", "Kutlw@n07#", "0817421062")
                .endsWith("registered successfully."));
    }

    @Test
    public void loginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void loginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrong"));
    }
}
