package com.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.memory.InitHash;
import com.pojo.BacnetObject;
import com.pojo.Device;
import com.pojo.DeviceTemplate;
import com.pojo.TemplateBacnetListObject;

public class LoadDeviceObject {

	public static void main(String[] args) {
	  
		
		 ConcurrentHashMap<Integer,TemplateBacnetListObject> templateHashMap  = InitHash.buildTemplateBacMap();
		 
		 ConcurrentHashMap<String,Integer> deviceTemplateIdHashMap  = InitHash.buildDeviceTemplateIDMap();

	    deviceTemplateIdHashMap.forEach((key,value) -> {
	    	System.out.println("Key: " + key + ", DeviceObject: " + value);
	    });
	    
		 
	
		 /*templateHashMap.forEach( (key,value) -> {
	            System.out.println("Key: " + key + ", DeviceObject: " + value.getDeviceTemplate().getName() +" BacnetObject"+ value.getBacnetObjectList());
			 
		 });*/
		 
		 
		
	  
		

	}

}
