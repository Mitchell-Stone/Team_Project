/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author 0111005906
 */
public class Courses {

    public Courses(int id, String courseName, String locationName, int hours, int degree ){
        courseID = id;
        name = courseName;
        location = locationName;
        numberOfHours = hours;
        finishingDegree = degree;
    }
    
    private int courseID;
    private String name;
    private String location;
    private int numberOfHours;
    private int finishingDegree;
    
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
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

    
}
