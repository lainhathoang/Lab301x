package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
	// Hash passsword method
    public String hashPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            
            StringBuffer hexString = new StringBuffer();
            
            for (int i = 0; i < messageDigest.length; i++) {
            	hexString.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Compare hashed password
    public boolean checkPasswordMD5(String userInputPassword, String hashedPasswordFromDatabase) {
        String hashedInputPassword = hashPasswordMD5(userInputPassword);
        return hashedInputPassword.equals(hashedPasswordFromDatabase);
    }
    
}
