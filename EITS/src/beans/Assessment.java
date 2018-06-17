/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javafx.scene.text.Text;

/*
Student Number: 7100438818

Name: Matteo Baldini

Date: 10/06/18

Purpose: 

Known Bugs:
 */
public class Assessment extends Courses {
    
    private int assessmentID;
    private int courseID;
    private String title;
    private String description; 
    private String date;
    
    private int studentID;
    
    private int diplomaID;
    private String name;
    private String industry;
    private String location;
    private String degree;
    
    public Assessment(int diplomaid, int courseid, int assessmentid, String titleA, String descriptionA, String dateA) {
    
        diplomaID = diplomaid;
        courseID = courseid;
        assessmentID = assessmentid;
        title = titleA;
        description = descriptionA;
        date = dateA;
    
    }
    
    public Assessment() {
    
    }
    
    public Assessment(int diplomaID) {
        this.diplomaID = diplomaID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiplomaID() {
        return diplomaID;
    }

    public void setDiplomaID(int diplomaID) {
        this.diplomaID = diplomaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    
    
    
}
