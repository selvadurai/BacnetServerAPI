package com.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.authentication.UserOperations;
import com.pojo.BacnetSettings;
import com.pojo.Device;
import com.pojo.DeviceTemplate;
import com.pojo.User;

public class Test {

	public static void main(String[] args) {
		
		//BacnetSettingsDAO bacnetSettingDAO = new BacnetSettingsDAO();
		//String ipAddress, String submask, int networkPrefix, int instanceId, int bacnetPort, String bacnetServerName
		//BacnetSettings bacnetSettings = new BacnetSettings("192.168.0.11","255.255.255.255",24,1111,47808,"BacnetAPI Server");
		//bacnetSettingDAO.addOrUpdateBacnetSettings(bacnetSettings);
		
		//ApiTokenDAO apiTokenDAO = new ApiTokenDAO();
		//apiTokenDAO.updateOrCreateToken("Haryana");
		//System.out.println(apiTokenDAO.getToken());
		//User user = new User("su", "su");
		//User user1 = new User("admin", "admin");

		
		
		
		//UserDAO userDAO = new UserDAO();
		//userDAO.insertOrUpdateUser(user);
		//userDAO.insertOrUpdateUser(user1);
		
		//UserOperations.updateOrCreateUser("java","java");
		
		System.out.println( UserOperations.isUserValid("admin", "admin"));
		
		
	}

}
