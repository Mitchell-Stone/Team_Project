/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Omni {
    
    private String database;
    private String table;
    private String column;
    private int number;
    private String word;
    
    private int inputType;
    
    private ArrayList<String> columnNames;
    private ArrayList<String> columnTypes;
    
    private int adminID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    
    private int assessmentID;
    private int courseID;
    private String title;
    private String description;
    private String date;
    
    private int employeeID;
    private String specialty;
    private String bio;
    
    private String name;
    private String industry;
    private String location;
    private int numberOfHours;
    private int finishingDegree;
    
    private int diplomaID;
    private String degree;
    
    private int studentID;
    private int industryPreference;
    private int visitCount;
    private int averageGrade;
    private int assessmentsPassed;
    
    private int grade;
    
    public Omni(int nv1, String vv1, String vv2, String vv3, String vv4, String vv5) {
    
         adminID = nv1;
         firstName = vv1;
         lastName = vv2;
         email = vv3;
         password = vv4;
         phoneNumber = vv5;
        
    }
    
    public Omni(int nv1, int nv2, String vv2, String vv3, String vv4) {
    
         assessmentID = nv1;
         courseID = nv2;
         title = vv2;
         description = vv3;
         date = vv4;
        
    }
    
    public Omni(String vv1, int nv2, int nv3) {
    
         date = vv1;
         studentID = nv2;
         courseID = nv3;
         
    }
    
    public Omni(int nv1, String vv1, String vv2, String vv3, String vv4, String vv5, String vv6, String vv7) {
    
         employeeID = nv1;
         firstName = vv1;
         lastName = vv2;
         email = vv3;
         password = vv4;
         specialty = vv5;
         phoneNumber = vv6;
         bio = vv7;
        
    }
    
    public Omni(int nv1, String vv1, String vv2, String vv3, int nv2, int nv3) {
    
         courseID = nv1;
         name = vv1;
         industry = vv2;
         location = vv3;
         numberOfHours = nv2;
         finishingDegree = nv3;
        
    }
    
    public Omni(int nv1, String vv1, String vv2, String vv3, String vv4) {
    
         diplomaID = nv1;
         name = vv1;
         industry = vv2;
         location = vv3;
         degree = vv4;
        
    }
    
    public Omni(int nv1, int nv2) {
    
        diplomaID = nv1;
        courseID = nv2;
        
    }
    
    public Omni(int nv1, int nv2, int nv3, String vv1, String vv2, String vv3, String vv4, int nv4, int nv5, int nv6, int nv7) {
    
        studentID = nv1;
        diplomaID = nv2;
        employeeID = nv3;
        firstName = vv1;
        lastName = vv2;
        email = vv3;
        password = vv4;
        industryPreference = nv4;
        visitCount = nv5;
        averageGrade = nv6;
        assessmentsPassed = nv7;
        
    }
    
    public Omni(int nv1, int nv2, int nv3, int nv4) {
        
        assessmentID = nv1;
        courseID = nv2;
        studentID = nv3;
        grade = nv4;
    
    }
    
    public Omni() {
    
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
    }

    public ArrayList<String> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(ArrayList<String> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public int getDiplomaID() {
        return diplomaID;
    }

    public void setDiplomaID(int diplomaID) {
        this.diplomaID = diplomaID;
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

    public int getIndustryPreference() {
        return industryPreference;
    }

    public void setIndustryPreference(int industryPreference) {
        this.industryPreference = industryPreference;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(int averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAssessmentsPassed() {
        return assessmentsPassed;
    }

    public void setAssessmentsPassed(int assessmentsPassed) {
        this.assessmentsPassed = assessmentsPassed;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    
}
