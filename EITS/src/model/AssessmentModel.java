/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



/**
 *
 * @author Martin
 */
public class AssessmentModel {
    
    public static ArrayList getCoursesByDiplomaID(int id) {
        
        ArrayList ggg = null;
        
        ResultSet rs;
    
        String sql = "SELECT * FROM diplomatocourses WHERE diplomaID = 1";
        
        try(
                    Connection conn = DbUtil.getConn(DbType.MYSQL);
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ) {
            
                    rs = stmt.executeQuery();
            
                while(rs.next()) {
                
                    ggg.add(rs.getInt("courseID"));
                
                }
                
                return ggg;
            
        } catch (Exception e) {
                System.out.println(e);
                return null;
        }
    
    }
    
}
