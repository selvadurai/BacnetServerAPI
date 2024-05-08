package com.authentication;

/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * Author: Jonathan Kevin Selvadurai
 * Date: May 7 2024
 */



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
	
	public static void factoryReset() {
		UserDAO userDAO = new UserDAO();
		
		if( !userDAO.recordExists()) {
			UserOperations.updateOrCreateUser("admin","admin");
		}
		
		
		
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
