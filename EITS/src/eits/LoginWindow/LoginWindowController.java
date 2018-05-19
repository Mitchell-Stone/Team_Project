/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eits.LoginWindow;

/**
 *
 * @author mitch
 */
public class LoginWindowController {

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;
    
    public void verifyUserLogin(String userName, String password){
        
        /*execute the sql query to verify users login details
        
        basic logic
        
        if (user == student) {
            show student dashboar
        }else if(user == caseworker){
            show case worker dashboard
        }else if(user == administrator{
            show admin dashboard
        }
        
        */
    }
    
    
}
