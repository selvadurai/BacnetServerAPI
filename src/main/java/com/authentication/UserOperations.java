package com.authentication;

import org.mindrot.jbcrypt.BCrypt;

import com.dao.UserDAO;
import com.pojo.User;

public class UserOperations {
	
    public static String hashPassword(String plainTextPassword) {
        // The higher the work factor, the more computational power is needed
        int workFactor = 12;
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(workFactor));
    }

    // Verify a password against a hashed password
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
	
	
	public static void updateOrCreateUser(String username,String password) {
		
		String hashPassword=UserOperations.hashPassword(password);
		User admin=new User(username,hashPassword);
		
		UserDAO userDAO = new UserDAO();
		userDAO.insertOrUpdateUser(admin);
		
		
	}
	
	
	public static boolean isUserValid(String username,String password) {
		
		UserDAO userDAO = new UserDAO();
		User admin=userDAO.getUser();
		
		if(admin.getUsername().equals(username)==true && verifyPassword(password,admin.getPassword())==true )
             return true;  
		else
			return false;
	}
	
	
	
	
	

}
