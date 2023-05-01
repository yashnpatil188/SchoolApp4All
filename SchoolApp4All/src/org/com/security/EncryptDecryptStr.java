package org.com.security;

public class EncryptDecryptStr {

    /*public static void main(String[] args) {
        try {
        String password = "Yashpal";
            System.out.println("plain pass="+password);
            
        String encryptedPassword = AESCrypt.encrypt(password);
            System.out.println("encrypted pass="+encryptedPassword);
            
        String decryptedPassword = AESCrypt.decrypt(encryptedPassword);    
                System.out.println("decrypted pass="+decryptedPassword);
                
        } catch(Exception e) { System.out.println("bug"+e.getMessage()); }
    }*/
    
    public String encryptString(String str){
    	String encryptedStr = "";
    	try {
            encryptedStr = AESCrypt.encrypt(str);
            } catch(Exception e) { System.out.println("bug"+e.getMessage()); }
    	return encryptedStr;
	}
	
    public String decryptString(String str){
    	String decryptedStr = "";
    	try {
    		decryptedStr = AESCrypt.decrypt(str);
            } catch(Exception e) { System.out.println("bug"+e.getMessage()); }
    	return decryptedStr;
	}
    
}