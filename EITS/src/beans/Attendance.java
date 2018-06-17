/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/*
Student Number: 7100438818

Name: Matteo Baldini

Date: 10/06/18

Purpose: 

Known Bugs:
 */
public class Attendance extends Courses {
    
    private String date;
    private int studentID;
    private int courseID;
    
    public Attendance(String date, int studentID,int courseID){
        this.date = date;
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public Attendance() {

    }
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    
}
