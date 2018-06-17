/*

Student Number: 7100438818

Name: Matteo Baldini

Date: 18/06/2018

Purpose:

Known Bugs: none

*/
package security;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Martin
 */
public class SecurityMethods {
    
    public static String getHash(String input) throws NoSuchAlgorithmException {
    
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] byteOfTextToHash = input.getBytes(StandardCharsets.UTF_8);
        byte[] hashedByetArray = digest.digest(byteOfTextToHash);
        String encoded = Base64.encode(hashedByetArray);
        
        return encoded;
    
    }
    
}
