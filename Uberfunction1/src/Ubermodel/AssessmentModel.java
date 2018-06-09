/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ubermodel;

import Beans.Assessment;
import Beans.Omni;
import Uberdb.DbType;
import Uberdb.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author Martin
 */
public class AssessmentModel {
    
    public static ObservableList<Omni> getAssessmentsByDiplomaID(int id) throws SQLException {
        
        ObservableList<Omni> assessmentList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM assessment WHERE assessmentID = ?";

        try(
                java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, id);
            
            Omni assessment;
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                assessment = new Omni(rs.getInt("assessmentID"), rs.getInt("courseID"), rs.getString("title"), rs.getString("description"), rs.getString("date"));
                assessmentList.add(assessment);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        
        return assessmentList;
    
    }
    
}
