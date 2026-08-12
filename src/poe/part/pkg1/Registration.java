/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

/**
 *
 * @author Student
 */
public class Registration { //start of class
    
    String userName;
    String passWord;
    String cellPhone;
    
    public boolean checkUserName(String username){ //start of username mnethod
        if(username.contains("@") && username.length() >= 3){
            return true;     
        }else{
            return false;
        
        }
    } //end of username method
    
    public boolean checkPasswordComplexity(String password){ //start of my password method
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[^a-zA-Z0-9].*");
    }//end of password method
    
    public boolean checkCellPhoneNumber(String cellphone){ //start of the method for checking cellphone number length is correct
        return cellphone.matches(".*\\+27[0-9]{9}$")
                || cellphone.matches(".*^0[0-9]{9}");
        
    }//end of cellphone method
    
    public String registerUser(String username, 
            String password, 
            String cellphone){
        
        if(checkUserName(username) && 
                checkPasswordComplexity(password) &&
                checkCellPhoneNumber(cellphone)){
            this.userName = username;
            this.passWord = password;
            this.cellPhone = cellphone;
            
            return "Registration was a success";
        }else{
            return "Registration was unsuccessful";
        }
        
    }
   
    public boolean registerStatus(String username, String password , String cellphone){
        return checkUserName(username) &&
                checkPasswordComplexity(password) &&
                checkCellPhoneNumber(cellphone);
                
    }
    
} //end of class
