
package beans;

public class Student extends User {
    
    public Student(int id, String fName, String lName, String email){
        studentID = id;
        firstName = fName;
        lastName = lName;
        this.email = email;
    }
    
    public Student(int id, String fName, String lName, String email, int diplomaID, int employeeID){
        studentID = id;
        firstName = fName;
        lastName = lName;
        this.email = email;
        this.diplomaID = diplomaID;
        this.employeeID = employeeID;
        
    }
    
    
    private int studentID;
    private String firstName;
    private String lastName;
    private String email;
    private int employeeID;
    private int diplomaID;
    
    
    public Student() {
    
    }

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

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getDiplomaID() {
        return diplomaID;
    }

    public void setDiplomaID(int diplomaID) {
        this.diplomaID = diplomaID;
    }
    
}
