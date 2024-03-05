package com.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.dao.ApiTokenDAO;

public class ApiTokenOperations {
	
	public static String ApiToken;
	
	
	
	public static void initToken() {
		ApiTokenDAO apiTokenDAO = new ApiTokenDAO();
		
		//If token doesn't exist then 
		//generate token
		if(apiTokenDAO.getToken()==null) {
			apiTokenDAO.updateOrCreateToken(ApiTokenOperations.generateRandomHash());
			System.out.println("Randomly Generated token");
		}
		
		ApiTokenOperations.ApiToken=apiTokenDAO.getToken();
	}
	
	
	public static boolean isTokenValid(String Token) {
		return ApiTokenOperations.ApiToken.equals(Token);
	}
	
	public static void generateNewToken() {
		ApiTokenDAO apiTokenDAO = new ApiTokenDAO();
		apiTokenDAO.updateOrCreateToken(ApiTokenOperations.generateRandomHash());
		ApiTokenOperations.ApiToken=apiTokenDAO.getToken();
	}
	
	

	
	
	public static String generateRandomHash() {
        try {
            // Generate a random 11-byte array
            byte[] randomBytes = new byte[11];
            SecureRandom.getInstanceStrong().nextBytes(randomBytes);

            // Create a message digest (SHA-256 in this example)
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(randomBytes);

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Return the first 11 characters as the random hash
            return hexString.toString().substring(0, 11);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle exceptions according to your application's needs
            return null;
        }
   }
	
	

}
