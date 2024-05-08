package server;

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

import com.dao.BacnetSettingsDAO;
import com.pojo.BacnetSettings;

public class InitBacnetServerConfig {
	
	//Default Values
	public static String ipaddress="0.0.0.0";
	public static String subnetMask="255.255.255.255";
	public static int networkPrefix=24;
	public static int instance=1111;
	public static int bacnetPort=47808;
	public static String deviceName="BacnetAPI Server";
	
    //Sets default bacnet variables 	
	public static void setDefaultBacnetVariables() {
		BacnetSettingsDAO bacnetSettingDAO = new BacnetSettingsDAO();

		if(!bacnetSettingDAO.recordExists()) {
			bacnetSettingDAO = new BacnetSettingsDAO();
		
			BacnetSettings bacnetSettings = new BacnetSettings(InitBacnetServerConfig.ipaddress,
					                                           InitBacnetServerConfig.subnetMask,
					                                           InitBacnetServerConfig.networkPrefix,
					                                           InitBacnetServerConfig.instance,
					                                           InitBacnetServerConfig.bacnetPort,
					                                           InitBacnetServerConfig.deviceName);

			bacnetSettingDAO.addOrUpdateBacnetSettings(bacnetSettings);		
		}
	}
	
	//Initializes Bacnet Variables 
	public static void initBacnetVariables() {
		BacnetSettingsDAO bacnetSettingDAO = new BacnetSettingsDAO();

		if(!bacnetSettingDAO.recordExists()){
			setDefaultBacnetVariables();
		}
		else {
			List<BacnetSettings> bacnetSettings=bacnetSettingDAO.getAllBacnetSettings();
			BacnetSettings bacnetSetting=bacnetSettings.get(0);
			System.out.println("Get Bacnet Settings");
			InitBacnetServerConfig.ipaddress= bacnetSetting.getIpAddress();
			InitBacnetServerConfig.subnetMask=bacnetSetting.getSubmask();
			InitBacnetServerConfig.networkPrefix=bacnetSetting.getBacnetPort();
			InitBacnetServerConfig.instance=bacnetSetting.getInstanceId();
			InitBacnetServerConfig.bacnetPort=bacnetSetting.getBacnetPort();
			InitBacnetServerConfig.deviceName=bacnetSetting.getBacnetServerName();
			System.out.println(InitBacnetServerConfig.deviceName);
		}
		
		
	}

}
