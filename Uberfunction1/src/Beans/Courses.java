/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author 0111005906
 */
public class Courses {
 
    private int courseID;
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
