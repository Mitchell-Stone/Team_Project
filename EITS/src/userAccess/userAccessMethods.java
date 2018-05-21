
package userAccess;

import beans.Login;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static sun.security.jgss.GSSUtil.login;

public class userAccessMethods {
    
    public static boolean checkUserPass(Login bean) {
    
        String sql = "SELECT * FROM $tablename WHERE email = ? AND password = ?";
        
        String query = sql.replace("$tablename", bean.getTable());
        
        ResultSet rs;
        
        try (
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setString(1, bean.getEmail());
            stmt.setString(2, bean.getPassword());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getString("firstName") + " | " + rs.getString("lastName"));
                
                return true;
                
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    
    }
    
}
