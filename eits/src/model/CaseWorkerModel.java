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

/**
 *
 * @author mitch
 */
public class CaseWorkerModel {
    
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
