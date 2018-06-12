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
public class CaseWorker extends User{
     private int employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    public CaseWorker(){
        
    }
    
    public CaseWorker(int empID, String fname, String lname, String email){
        employeeID = empID;
        firstName = fname;
        lastName = lname;
        this.email = email;
    }
    public CaseWorker(String email, String phoneNumber){
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    

    public int getEmployeeID() {
        return employeeID;
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

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
