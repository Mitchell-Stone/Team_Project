
package beans;

public class Student extends User {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    private String firstName;
    private String lastName;
    private String email;
    
    public Student() {
    
    }
    
    public Student(String fName, String lName, String email){
        firstName = fName;
        lastName = lName;
        this.email = email;
    }    
}
