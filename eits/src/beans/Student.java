
package beans;

public class Student extends User {

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

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
    
    private int studentID;
    private String firstName;
    private String lastName;
    private String email;
    
    
    public Student() {
    
    }
    
    public Student(int id, String fName, String lName, String email){
        studentID = id;
        firstName = fName;
        lastName = lName;
        this.email = email;
    }    
}
