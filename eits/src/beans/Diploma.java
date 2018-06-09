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
public class Diploma {

    public Diploma(int id, String name, String industry, String location, String type) {
        diplomaID = id;
        diplomaName = name;
        diplomaIndustry = industry;
        diplomaLocation = location;
        courseType = type;
    }

    public int getDiplomaID() {
        return diplomaID;
    }

    public String getDiplomaName() {
        return diplomaName;
    }

    public String getDiplomaIndustry() {
        return diplomaIndustry;
    }

    public String getDiplomaLocation() {
        return diplomaLocation;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setDiplomaID(int diplomaID) {
        this.diplomaID = diplomaID;
    }

    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }

    public void setDiplomaIndustry(String diplomaIndustry) {
        this.diplomaIndustry = diplomaIndustry;
    }

    public void setDiplomaLocation(String diplomaLocation) {
        this.diplomaLocation = diplomaLocation;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    private int diplomaID;
    private String diplomaName;
    private String diplomaIndustry;
    private String diplomaLocation;
    private String courseType;
}
