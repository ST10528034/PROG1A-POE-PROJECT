/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testClasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class testUsernamePasswordCellphoneLogin {
    
    public testUsernamePasswordCellphoneLogin() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    //Username testing
    @Test
    public void testValidUsername(){
        String username = "kyl_1";
        assertTrue(UserValidator.isValidUsername(username));
    }
    
    @Test
    public void testInvalidUsername(){
        String username = "kyle!!!!!";
        assertFalse(UserValidator.isValidUsername(username));
    }
    
    //Password testing
    @Test
    public void testValidPassword(){
        String password = "Ch&&sec@ke99!";
        assertTrue(UserValidator.isValidPassword(password));
    }
    
    @Test
    public void testInvalidPassword(){
        String password = "password";
        assertFalse(UserValidator.isValidPassword(password));
    }
    
    //cellphone testing
    @Test
    public void testValidCellphone(){
        String cellphone = "+27838968976";
        assertTrue(UserValidator.isValidCellphone(cellphone));
    }
    
    @Test
    public void testInvalidCellphone(){
        String cellphone = "08966553";
        assertFalse(UserValidator.isValidCellphone(cellphone));
    }
    
    //login testing
    @Test
    void testLoginSuccess(){
        assertTrue(UserValidator.login("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginFailed(){
        assertFalse(UserValidator.login("wrongUsername", "wrongPassword"));
    }
}
