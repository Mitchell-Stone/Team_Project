
package beans;
/*
Student Number: 0111005906

Name: Mitch Stone

Date: 10/06/18

Purpose:

Known Bugs:
 */

public class User {
    
    private static String firstName;
    private static String lastName;
    private String IDType;
    private String email;
    private String password;
    private String userName;
    private String table;
    private String column;
    private String idType;
    private static int ID;

    
    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }
    
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
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

    public String getPassword() {
        return password;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public void setLastName(String lName) {
        lastName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }
    
}
