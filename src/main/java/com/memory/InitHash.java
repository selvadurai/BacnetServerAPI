package com.memory;

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
 * Date: March 25 2024
 */



import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import com.dao.BacnetObjectDAO;
import com.dao.BroadcastBacnetDAO;
import com.dao.DeviceDAO;
import com.dao.DeviceTempDAO;
import com.pojo.BacnetObject;
import com.pojo.BroadcastBacnet;
import com.pojo.Device;
import com.pojo.DeviceTemplate;
import com.pojo.TemplateBacnetListObject;




public class InitHash {
	
	public static  ConcurrentHashMap<Integer,TemplateBacnetListObject> buildTemplateBacMap () {
		
		  DeviceTempDAO deviceTempDAO = new DeviceTempDAO();
	      List<DeviceTemplate> deviceTempList=deviceTempDAO.getAllDeviceTemplates();
			
		  BacnetObjectDAO bacnetObjectDAO = new BacnetObjectDAO();
		  List<BacnetObject> bacnetObjectList =bacnetObjectDAO.getAllBacnetObjects();
		  
		  ConcurrentHashMap<Integer,TemplateBacnetListObject> templateHashMap  = new ConcurrentHashMap<>();

		  
		  
		  for(int i=0;i<deviceTempList.size();i++) {
			  DeviceTemplate deviceTemp=deviceTempList.get(i);
			  int deviceTempID=deviceTemp.getId();
			  List<BacnetObject> bacnetObjSrchList=  new CopyOnWriteArrayList<>();


			  
			  for(int x=0;x<bacnetObjectList.size();x++) {
				  BacnetObject bacObj = bacnetObjectList.get(x);

				  if(bacObj.getDevTempId()==deviceTempID) {
					  bacnetObjSrchList.add(bacObj);
				  }
				  
			  }
			  
			  
			  templateHashMap.put(deviceTempID,new TemplateBacnetListObject(deviceTemp,bacnetObjSrchList )) ;
			  
			  
		  }
		
		return templateHashMap;
	}
	
	
	public static  ConcurrentHashMap<String,Integer> buildDeviceTemplateIDMap () {
		
		
			 DeviceDAO deviceDAO = new DeviceDAO();  
			 List<Device> deviceList=deviceDAO.getAllDevices();
			 
			 ConcurrentHashMap<String,Integer> deviceTempIdMap  = new ConcurrentHashMap<>();
			 
			 for(int i=0; i<deviceList.size();i++) {
				 System.out.println(deviceList.get(i).getName());
				 deviceTempIdMap.put(deviceList.get(i).getName(),deviceList.get(i).getDevTempId());
			 }
		 

		
		
		return deviceTempIdMap;
		
   }
	

	
	
	
	
	
	
	
	
	/* 
	 * Loads  ["JCI1",1] from  database.
	 * loads deviceName and template id into deviceTemplateIDMap
	 * */
	public static  void initDeviceTempID () {
		 Cache.deviceTemplateIDMap.clear();
		
		 DeviceDAO deviceDAO = new DeviceDAO();  
		 List<Device> deviceList=deviceDAO.getAllDevices();
		 
		 
		 for(int i=0; i<deviceList.size();i++) {
			 Cache.deviceTemplateIDMap.put(deviceList.get(i).getName(),deviceList.get(i).getDevTempId());
		 }
 
				
   }
	
	
    /*  
	* Loads  Template ID and TemplateBacnetListObject into templateBacMap
    * 
    * */
   public static  void initTempBac () {
		
		  Cache.templateBacMap.clear();
		
		
		  DeviceTempDAO deviceTempDAO = new DeviceTempDAO();
	      List<DeviceTemplate> deviceTempList=deviceTempDAO.getAllDeviceTemplates();
			
		  BacnetObjectDAO bacnetObjectDAO = new BacnetObjectDAO();
		  List<BacnetObject> bacnetObjectList =bacnetObjectDAO.getAllBacnetObjects();
		  
		//  ConcurrentHashMap<Integer,TemplateBacnetListObject> templateHashMap  = new ConcurrentHashMap<>();

		  
		  
		  for(int i=0;i<deviceTempList.size();i++) {
			  DeviceTemplate deviceTemp=deviceTempList.get(i);
			  int deviceTempID=deviceTemp.getId();
			  List<BacnetObject> bacnetObjSrchList=  new CopyOnWriteArrayList<>();


			  
			  for(int x=0;x<bacnetObjectList.size();x++) {
				  BacnetObject bacObj = bacnetObjectList.get(x);

				  if(bacObj.getDevTempId()==deviceTempID) {
					  bacnetObjSrchList.add(bacObj);
				  }
				  
			  }
			  
			  
			  Cache.templateBacMap.put(deviceTempID,new TemplateBacnetListObject(deviceTemp,bacnetObjSrchList )) ;
			  
			  
		  }
		
				
    }
	
	//loads [instanceNumber,BroadcastBacnet Object] into deviceBacnetBroadcastMap
	public static void initDeviceBacnetBroadcastMap() {
		Cache.deviceBacnetBroadcastMap.clear();
		
		BroadcastBacnetDAO bbDAO  = new BroadcastBacnetDAO();
		List<BroadcastBacnet> broadcastList=bbDAO.getAllBroadcastBacnets();
		
		for(int i=0; i<broadcastList.size();i++) {
		   Cache.deviceBacnetBroadcastMap.put(broadcastList.get(i).getInstanceNum(), broadcastList.get(i));
		}
	}
	
	
	
	

}
