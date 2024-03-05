package server;

import java.util.List;

import com.dao.BacnetSettingsDAO;
import com.pojo.BacnetSettings;

public class InitBacnetServerConfig {
	
	//Default Values
	public static String ipaddress="192.168.0.11";
	public static String subnetMask="255.255.255.255";
	public static int networkPrefix=24;
	public static int instance=1111;
	public static int bacnetPort=47808;
	public static String deviceName="BacnetAPI Server";
	
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
