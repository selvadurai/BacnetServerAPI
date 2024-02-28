package com.memory;

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
	

	
	
	
	
	
	
	
	
	

	public static  void initDeviceTempID () {
		
		 DeviceDAO deviceDAO = new DeviceDAO();  
		 List<Device> deviceList=deviceDAO.getAllDevices();
		 
		 
		 for(int i=0; i<deviceList.size();i++) {
			 Cache.deviceTemplateIDMap.put(deviceList.get(i).getName(),deviceList.get(i).getDevTempId());
		 }
 
				
   }
	
	public static  void initTempBac () {
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
			  
			  
			  Cache.templateBacMap.put(deviceTempID,new TemplateBacnetListObject(deviceTemp,bacnetObjSrchList )) ;
			  
			  
		  }
		
				
    }
	
	
	public static void initDeviceBacnetBroadcastMap() {
		BroadcastBacnetDAO bbDAO  = new BroadcastBacnetDAO();
		List<BroadcastBacnet> broadcastList=bbDAO.getAllBroadcastBacnets();
		
		for(int i=0; i<broadcastList.size();i++) {
		   Cache.deviceBacnetBroadcastMap.put(broadcastList.get(i).getInstanceNum(), broadcastList.get(i));
		}
	}
	
	
	
	

}
