/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.CaseWorker;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mitch
 */
public class CaseWorkerModel {
    
    public ObservableList<CaseWorker> getAllCaseWorkers() throws SQLException{
        ObservableList<CaseWorker> caseWorkerList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all case workers
        String query = "SELECT * FROM caseworker";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            CaseWorker caseWorker;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                caseWorker = new CaseWorker(rs.getInt("employeeID"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
                caseWorkerList.add(caseWorker);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }  
        return caseWorkerList;
    } 
    
    public static boolean updateCaseWorker(CaseWorker bean) {
    
        String sql = "UPDATE caseworker SET firstName = ?, lastName = ?, email = ? WHERE employeeID = ?";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setString(1, bean.getFirstName());
            stmt.setString(2, bean.getLastName());
            stmt.setString(3, bean.getEmail());
            stmt.setInt(4, bean.getEmployeeID());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    
    }
}
