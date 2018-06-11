/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import beans.Diploma;
import beans.Student;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mitch
 */
public class DiplomaModel {

    public ObservableList<Diploma> getAllDiplomas() throws SQLException {

        ObservableList<Diploma> diplomaList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM diploma";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            Diploma diploma;

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                diploma = new Diploma(
                        rs.getInt("diplomaID"),
                        rs.getString("name"),
                        rs.getString("industry"),
                        rs.getString("location"),
                        rs.getString("degree"));
                diplomaList.add(diploma);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return diplomaList;
    }

    public ObservableList<Courses> getCoursesByDiplomaID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ObservableList<Diploma> getDiplomaByStudent(int diplomaID) throws SQLException {

        ObservableList<Diploma> diplomaList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT name FROM diploma WHERE diplomaID = ?";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            Diploma diploma;
            stmt.setInt(1, diplomaID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                diploma = new Diploma(
                        rs.getInt("diplomaID"),
                        rs.getString("name"),
                        rs.getString("industry"),
                        rs.getString("location"),
                        rs.getString("degree"));
                diplomaList.add(diploma);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return diplomaList;
    }

    public static boolean addNewDiploma(Diploma bean) throws SQLException{
    
        String query = "INSERT INTO diploma (name, industry, location, degree) VALUES (?, ?, ?, ?)";
        
        ResultSet keys = null;
        
        try{
            Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
            stmt.setString(1, bean.getDiplomaName());
            stmt.setString(2, bean.getDiplomaIndustry());
            stmt.setString(3, bean.getDiplomaLocation());
            stmt.setString(4, bean.getCourseType());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
        
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if(keys != null) {
                keys.close();
            }
        }
    }
    
}
