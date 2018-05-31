
package model;

import beans.Student;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentModel extends MainModel {
    
    public Student getStudent(Student bean) throws SQLException {
    
        Student student = null;
        
        ResultSet rs = null;
        
        String sql = "SELECT * FROM $tablename WHERE $column = ?";
        
        String query = sql.replace("$tablename", bean.getTable().replace("$column", bean.getColumn()));
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, bean.getID());
            
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                
                bean.setID(rs.getInt(1));
                bean.setFirstName(rs.getString("firstName"));
                bean.setLastName(rs.getString("lastName"));
                
                return student;
                
            } else {
                
                return null;
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        
    }
}
