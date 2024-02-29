package com.memory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pojo.BroadcastBacnet;

public class JsonPayloadOperations {
	
	public static void writePayload(int instanceNum,String value) {
		
		System.out.println("Writing Payload "+instanceNum+" value "+value);
		 
		InitHash.initDeviceBacnetBroadcastMap();
		System.out.println("Device Exist"+Cache.deviceBacnetBroadcastMap.containsKey(instanceNum));
		if(Cache.deviceBacnetBroadcastMap.containsKey(instanceNum)) {
			BroadcastBacnet  broadcastBac=Cache.deviceBacnetBroadcastMap.get(instanceNum);
			String deviceName=broadcastBac.getDeviceName();
			String keyName=broadcastBac.getKeyName();
			
			String  objectType=broadcastBac.getObjectType();
			
			String jsonPayload=Cache.deviceJsonPayloadMap.get(deviceName);
			
		    JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
  	  ;


		    switch(objectType.charAt(0)) {
		     
		     case 'A':
		    	       
		    	      jsonObject.addProperty(keyName,Float.parseFloat(value));
		    	      break;
		     case 'B':
		    	 
	    	          jsonObject.addProperty(keyName,Boolean.parseBoolean(value));
		              break;
		    }   
		    

		    //System.out.println("Writing Object test "+new Gson().toJson(jsonObject) );  
	        Cache.deviceJsonPayloadMap.put(deviceName,new Gson().toJson(jsonObject));
		    
		}
		
		
		
	}
	
	
	

}
