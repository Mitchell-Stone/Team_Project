/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seurity;

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
