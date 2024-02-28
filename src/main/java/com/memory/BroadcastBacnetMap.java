package com.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dao.BroadcastBacnetDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pojo.BacnetObject;
import com.pojo.BroadcastBacnet;
import com.pojo.DeviceTemplate;
import com.pojo.TemplateBacnetListObject;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.obj.AnalogInputObject;
import com.serotonin.bacnet4j.obj.AnalogOutputObject;
import com.serotonin.bacnet4j.obj.AnalogValueObject;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.obj.BinaryInputObject;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.EngineeringUnits;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;

import server.BacnetServerObject;

public class BroadcastBacnetMap {
	
	
   private ConcurrentHashMap<String,BACnetObject> broadcastMap;
   List<BACnetObject> bacNetList;
   BacnetServerObject server=BacnetServerObject.getInstance();
   List<String> deviceLoadList ;
   
   
   private static final BroadcastBacnetMap INSTANCE = new BroadcastBacnetMap();


   private BroadcastBacnetMap() {
	   bacNetList= new CopyOnWriteArrayList<>();
	   broadcastMap =new ConcurrentHashMap<>();
	   deviceLoadList= new CopyOnWriteArrayList<>();
	  //InitHash.initDeviceTempID();
	  //InitHash.initTempBac();

   }
   
   public static BroadcastBacnetMap getInstance() {
       return INSTANCE;
   }
   
   
   
   
   public boolean isDeviceLoaded(String deviceName) {
	   return deviceLoadList.contains(deviceName);
	}
   
   
   
   
   public void updateBacnetObject(String jsonPayload,String deviceName) {
	   Cache.deviceJsonPayloadMap.put(deviceName, jsonPayload);
	   int templateID=Cache.deviceTemplateIDMap.get(deviceName);
	   TemplateBacnetListObject templateBacnetList=Cache.templateBacMap.get(templateID);
	   
	   
	   
	   
	   
	   List<BacnetObject> baclist =templateBacnetList.getBacnetObjectList();
	   JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();

	   
	   for(int i=0;i<baclist.size();i++) {
		   String name=baclist.get(i).getName();;
		   String bacnetObjectName=deviceName+baclist.get(i).getName();
		   float value= jsonObject.get(name).getAsFloat();
		   broadcastMap.get(bacnetObjectName).writePropertyInternal(PropertyIdentifier.presentValue, new Real(value));
		   
	   }
	   
   }
   
   
   
   
   
   public void addBacnetObject(String jsonPayload,String deviceName) {
	   Cache.deviceJsonPayloadMap.put(deviceName, jsonPayload);
	   
	   int templateID=Cache.deviceTemplateIDMap.get(deviceName);
	   TemplateBacnetListObject templateBacnetList=Cache.templateBacMap.get(templateID);
	   
	   
	   JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
	   
	   
	   
	  
	   List<BacnetObject> baclist =templateBacnetList.getBacnetObjectList();
	  
	   try {	
		  //Looping Bacnet List 
	      for(int i=0;i<baclist.size();i++) {
			   System.out.println("Writing to bacnet");
			   String name=baclist.get(i).getName();;
			   String bacnetObjectName=deviceName+baclist.get(i).getName();
			   String bacnetObjType=baclist.get(i).getBacObjType();
			  
			   
			   int instanceNum =BacnetBroadcastMapOperations.generateBacnetInstanceNum();

			   
			   if(bacnetObjType.equals("AnalogInput")) {
				   float  value=jsonObject.get(name).getAsFloat();
				     
				   broadcastMap.put(bacnetObjectName, 
						            new AnalogInputObject(server.getLocalDevice(), 
                                    instanceNum, 
                                    bacnetObjectName, 
                                    value, 
                                    EngineeringUnits.noUnits, 
                                    java.lang.Boolean.TRUE));
				     
				         	     
			  }
			 else if (bacnetObjType.equals("AnalogOutput")) {
				   float  value=jsonObject.get(name).getAsFloat();
				   //int    req=jsonObject.get("reqDefault").getAsInt();


				   broadcastMap.put(bacnetObjectName, 
				           new AnalogOutputObject(server.getLocalDevice(), 
				        		   				instanceNum, 
				        		   				bacnetObjectName, 
				        		   				value, 
				        		   				EngineeringUnits.noUnits, 
				        		   				java.lang.Boolean.TRUE, 
				        		   				0));
		     
				 
			 } 
			 else if (bacnetObjType.equals("AnalogValue")) {
				   float  value=jsonObject.get(name).getAsFloat();
				   broadcastMap.put(bacnetObjectName, 
				                    new AnalogValueObject(server.getLocalDevice(), 
				        		   	instanceNum, 
				        		   	bacnetObjectName, 
				        		   	value, 
				        		   	EngineeringUnits.noUnits, 
				        		   	java.lang.Boolean.TRUE));
				 
			 }
			   
			   
			   BroadcastBacnetDAO broadcastDAO = new BroadcastBacnetDAO() ;
		       broadcastDAO.insertBroadcastBacnet(instanceNum, bacnetObjectName,bacnetObjType, deviceName, name);
				 
					
					 
		    }
	      
	      
	     
	      
	        
	      
	        deviceLoadList.add(deviceName);
	      
	      
	      }
			catch (BACnetServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   
	       catch (JsonSyntaxException  e) {
			   // TODO Auto-generated catch block
			    e.printStackTrace();
		   } 
	  
		  
	   
		   	   
   }
   
   
   public void loadBacnetObject(String jsonPayload,String deviceName) {
	   //int templateID=Cache.deviceTemplateIDMap.get(deviceName);
	   
	   Cache.deviceJsonPayloadMap.put(deviceName, jsonPayload);

	   JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
	   
	   
	   
	  // BroadcastBacnetDAO  dao = new BroadcastBacnetDAO();
	   //List<BroadcastBacnet> baclist =dao.getAllBroadcastBacnets();
	   List<BroadcastBacnet> baclist =  BacnetBroadcastMapOperations.getAllDeviceBroadcastList(deviceName);
	   
	   try {	
		  //Looping Bacnet List 
	      for(int i=0;i<baclist.size();i++) {
			   System.out.println("loading to bacnet");
			   int instanceNum=baclist.get(i).getInstanceNum();
			   String name= baclist.get(i).getKeyName();

			   String bacnetObjectName=baclist.get(i).getObjectName();
			   String bacnetObjType=baclist.get(i).getObjectType();
			  
			   //System.out.println(name + " "+ bacnetObjectName);


			   
			   if(bacnetObjType.equals("AnalogInput")) {
				   float  value=jsonObject.get(name).getAsFloat();
				     
				   broadcastMap.put(bacnetObjectName, 
						            new AnalogInputObject(server.getLocalDevice(), 
                                    instanceNum, 
                                    bacnetObjectName, 
                                    value, 
                                    EngineeringUnits.noUnits, 
                                    java.lang.Boolean.TRUE));
				     
				         	     
			  }
			 else if (bacnetObjType.equals("AnalogOutput")) {
				   float  value=jsonObject.get(name).getAsFloat();
				   //int    req=jsonObject.get("reqDefault").getAsInt();


				   broadcastMap.put(bacnetObjectName, 
				           new AnalogOutputObject(server.getLocalDevice(), 
				        		   				instanceNum, 
				        		   				bacnetObjectName, 
				        		   				value, 
				        		   				EngineeringUnits.noUnits, 
				        		   				java.lang.Boolean.TRUE, 
				        		   				0));
		     
				 
			 } 
			 else if (bacnetObjType.equals("AnalogValue")) {
				   float  value=jsonObject.get(name).getAsFloat();
				   broadcastMap.put(bacnetObjectName, 
				                    new AnalogValueObject(server.getLocalDevice(), 
				        		   	instanceNum, 
				        		   	bacnetObjectName, 
				        		   	value, 
				        		   	EngineeringUnits.noUnits, 
				        		   	java.lang.Boolean.TRUE));
				 
			 }
			   
			   
			   
				 
					
					 
		    }
	      
	      
	      
	        deviceLoadList.add(deviceName);
	      
	      
	      }
			catch (BACnetServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   
	       catch (JsonSyntaxException  e) {
			   // TODO Auto-generated catch block
			    e.printStackTrace();
		   } 
	  
		  
	   
		   	   
   }
   
   
   
   
   
   
   
   
   
  
    /*
   public static void main(String [] args) {
	   String jsonPayload = "{"
               + "\"Temp\": 28.323,"
               + "\"Humidity\": 88.545453"
               + "}";
	   		
	   String jsonPayload11 = "{"
               + "\"Temp\": 11,"
               + "\"Humidity\":989"
               + "}";
	   			   
			   
			  
	   BroadcastBacnetMap broadcast = new BroadcastBacnetMap();
	   broadcast.addBacnetObject(jsonPayload, "JCISensor");
	   broadcast.updateBacnetObject(jsonPayload11, "JCISensor");
	   
	   
	   
	   //System.out.println(broadcast.isDeviceLoaded("JCISensor"));
   }
	*/

}