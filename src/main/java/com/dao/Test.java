package com.dao;

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
