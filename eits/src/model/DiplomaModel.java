/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import beans.Diploma;
import beans.Student;
import db.DbType;
import db.DbUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
