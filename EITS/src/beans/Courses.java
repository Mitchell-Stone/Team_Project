/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/*
Student Number: 6105091917

Name: Dion Bird

Date: 10/06/18

Purpose: 

Known Bugs:
 */
public class Courses {


 
    private int courseID;
    private int diplomaID;
    private String name;
    private String industry;
    private String location;
    private int numberOfHours;
    private int finishingDegree;
    private String table;

    public Courses(int courseID, String name, String industry, String location, int numberOfHours, int finishingDegree) {
        this.courseID = courseID;
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.numberOfHours = numberOfHours;
        this.finishingDegree = finishingDegree;
    }

    public Courses() {
        
    }

    public Courses(int diplomaID, int courseID, String name, String industry, String location, int numOfHours, int finDegree) {
        this.diplomaID = diplomaID;
        this.courseID = courseID;
        this.name = name;
        this.industry = industry;
        this.location = location;
        numberOfHours = numOfHours;
        finishingDegree = finDegree;
        
    }
    
    public int getDiplomaID() {
        return diplomaID;
    }

    public void setDiplomaID(int dimplomaID) {
        this.diplomaID = dimplomaID;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getFinishingDegree() {
        return finishingDegree;
    }

    public void setFinishingDegree(int finishingDegree) {
        this.finishingDegree = finishingDegree;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
