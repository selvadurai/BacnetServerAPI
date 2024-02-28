package com.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.dao.BacnetObjectDAO;
import com.dao.DeviceTempDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.memory.Cache;
import com.memory.InitHash;
import com.pojo.DeviceTemplate;

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
	
	
	

}
