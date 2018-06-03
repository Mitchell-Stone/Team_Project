/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author mitch
 */
public class Administrator {
    public Administrator(){
        
    }
    
    public Administrator(int id, String fname, String lname, String email){
        adminID = id;
        firstName = fname;
        lastName = lname;
        this.email = email;
    }
    
    public int getAdminID() {
        return adminID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    private int adminID;
    private String firstName;
    private String lastName;
    private String email;
    
    
    
}
