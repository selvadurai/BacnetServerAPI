package com.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dao.BacnetObjectDAO;
import com.dao.BroadcastBacnetDAO;
import com.dao.DeviceDAO;
import com.dao.DeviceTempDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.memory.Cache;
import com.memory.InitHash;
import com.pojo.BroadcastBacnet;
import com.pojo.Device;
import com.pojo.DeviceTemplate;
import com.pojo.TemplateBacnetListObject;

public class ApiController {
	
	public ApiController() {
		
	}
	
	public void addTemplate(String jsonPayload) throws InterruptedException {
 		JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
     
 		Gson gson = new Gson();
        JsonObject outerJsonObject = gson.fromJson(jsonObject, JsonObject.class);

        // Extracting the inner JSON string
        String innerJsonString = outerJsonObject.get("data").getAsString();

        // Parsing the inner JSON string
        JsonObject innerJsonObject = gson.fromJson(innerJsonString, JsonObject.class);

        String templateName = innerJsonObject.get("templateName").getAsString();
        
        
        JsonArray bacnetNameList = innerJsonObject.getAsJsonArray("name").getAsJsonArray();
        JsonArray  bacnetObjectTypeList = innerJsonObject.getAsJsonArray("bacnetObjectType");

      
        DeviceTempDAO   deviceDoa = new DeviceTempDAO();
        BacnetObjectDAO bacnetObjectDAO = new BacnetObjectDAO();
        
        deviceDoa.addDeviceTemplate(templateName, " ");
        
		
        InitHash.initDeviceTempID();
		InitHash.initTempBac();
		
		ConcurrentHashMap<String, Integer> map=deviceDoa.getNameIDMap();
		
		int templateID=map.get(templateName);
		
		for(int i=0;i<bacnetNameList.size();i++) {
			System.out.println("Creating Bacnet Objects");
			String bacnetObjType=bacnetObjectTypeList.get(i).getAsString();
			//int instanceNum=instanceNumberList.get(i).getAsInt();
			//int reqDefault=reqDefaultList.get(i).getAsInt();
            String name = bacnetNameList.get(i).getAsString();
			bacnetObjectDAO.addBacnetObject(templateID, bacnetObjType, name);
		}
		
		System.out.println("Template Created");

		
	}
	
	
	
	public String templateDeviceJsonString(){
		 DeviceTempDAO deviceTempDAO = new DeviceTempDAO(); 
		 List<DeviceTemplate> deviceTempList= deviceTempDAO.getAllDeviceTemplates();
	     Gson gson = new Gson();
	     return gson.toJson(deviceTempList);
	 }
	
	public String templateDeviceBacNetList() {
		
		 InitHash.initTempBac();

		 Iterator<Entry<Integer, TemplateBacnetListObject>> iterator = Cache.templateBacMap.entrySet().iterator();
         List<TemplateBacnetListObject> tempBacList =  new CopyOnWriteArrayList<>();
	        while (iterator.hasNext()) {
	            Entry<Integer, TemplateBacnetListObject> entry = iterator.next();
	         
	            tempBacList.add(entry.getValue());
	            //System.out.println("Key: " + key + ", Value: " + value);
	        }
		//Cache.templateBacMap.
		
		
		  Gson gson = new Gson();
		  return gson.toJson(tempBacList);
		
	}
	
   public void deleteTemplates(String jsonPayload) {
		Gson gson = new Gson();

	    JsonObject jsonObject = gson.fromJson(jsonPayload, JsonObject.class);
	    JsonArray markedDevicesArray = jsonObject.getAsJsonArray("markedDevices");
	    
	    System.out.println(markedDevicesArray.size());
	    
	    BacnetObjectDAO bacObjDAO = new BacnetObjectDAO();
	    DeviceDAO  deviceDAO = new DeviceDAO();
	    BroadcastBacnetDAO broadcastDAO = new BroadcastBacnetDAO();
	    DeviceTempDAO deviceTempDAO = new DeviceTempDAO();
	    
	    
        if (markedDevicesArray != null) {
            for (JsonElement markedDeviceElement : markedDevicesArray) {
            	JsonObject markedDeviceObject = markedDeviceElement.getAsJsonObject();

                JsonObject deviceTemplate = markedDeviceObject.getAsJsonObject("deviceTemplate");
                if (deviceTemplate != null) {
                    int id = deviceTemplate.get("id").getAsInt();
                    System.out.println("Device template id is "+id);
                    
                    bacObjDAO.deleteBacnetObjectTemplateId(id);
                  
                    List<Device> deviceList=deviceDAO.getAllDevices();
                    
            		List<BroadcastBacnet> broadcastList=broadcastDAO.getAllBroadcastBacnets();
            		
            
                    
                    for(int i=0;i<deviceList.size();i++) {
                    	
                    	if(deviceList.get(i).getDevTempId()==id) {
                    		if(Cache.deviceJsonPayloadMap.containsKey(deviceList.get(i).getName())){
                    			System.out.println("DeviceList ");
                    			Cache.deviceJsonPayloadMap.remove(deviceList.get(i).getName());
                    		}
                    		
                    		broadcastDAO.deleteBroadcastBacnetName(deviceList.get(i).getName()); 
                         }
                    }
                    
                    deviceDAO.deleteDeviceByDevTempId(id);
                    deviceTempDAO.deleteDeviceTemplate(id);
                    //Cache.templateBacMap.remove(id);

                }
                 
                    
                    
     	
                }
        	
           }
        }

	    
		//System.out.println(jsonArray.size());
		//DeviceTemplate deviceTemplate=gson.fromJson(jsonArray.get(0).g, DeviceTemplate.class);
		//System.out.println(deviceTemplate.getId());
		
	    //JsonObject outerJsonObject = gson.fromJson(jsonObject, DeviceTemplate.class);
   }	
	
	
	


