package bacnetServer.bacnetServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.controller.ApiController;
import com.dao.DeviceDAO;
import com.dao.DeviceTempDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.memory.BacnetBroadcastMapOperations;
import com.memory.BroadcastBacnetMap;
import com.memory.Cache;
import com.memory.InitHash;
import com.pojo.BacnetObject;
import com.pojo.Device;
import com.pojo.TemplateBacnetListObject;
import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.obj.AnalogInputObject;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.obj.BinaryInputObject;
import com.serotonin.bacnet4j.obj.CharacterStringObject;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.EngineeringUnits;
import com.serotonin.bacnet4j.type.enumerated.Polarity;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.Real;


import io.javalin.Javalin;
import server.BacnetServerObject;


public class RestServer {
	
	List<BACnetObject> bacNetList;
	BacnetServerObject server=BacnetServerObject.getInstance();
	   
	   
	public RestServer() {
		 bacNetList= new CopyOnWriteArrayList<>();
		 
		 InitHash.initDeviceTempID();
		 InitHash.initTempBac();
	}   


	public static void main(String[] args) throws Exception {
	
	  InitHash.initDeviceTempID();
	  InitHash.initTempBac();
	  InitHash.initDeviceBacnetBroadcastMap();
	   
	  //BacnetBroadcastOperations.initBroadcastList();
		
	  BacnetServerObject server = BacnetServerObject.getInstance();
	  server.eventListener();
	  server.start();
	  BroadcastBacnetMap bbm = BroadcastBacnetMap.getInstance();
	  
	 // RestServer bbm = new RestServer();
	  
	  //BroadcastBacnetMap bbm = BroadcastBacnetMap.getInstance();
	  
	  // Test
	  
	 /* 
	  String pay = "{"
               + "\"Temp\": 28.323,"
               + "\"Humidity\": 88.545453"
               + "}";
	  
	  
 		
 String jsonPayload11 = "{"
         + "\"Temp\": 11,"
         + "\"Humidity\":989"
         + "}";
         
         */
 			   
	//  broadcast.addBacnetObject(pay, "JCISensor");
	//   broadcast.updateBacnetObject(jsonPayload11, "JCISensor");

	 
	  
	  /*
	// Create BinaryPV (Binary Present Value)
	  BinaryPV presentValue = BinaryPV.inactive; // You can choose the appropriate value here.

	  // Set instance number, name, and other parameters accordingly.
	  int instanceNumber = 1;
	  String binaryName = "BinaryInput1";
	  boolean outOfService = false;
	  Polarity polarity = Polarity.normal; // You can choose the appropriate polarity.
	  
	  new BinaryInputObject(server.getLocalDevice(), instanceNumber,binaryName , presentValue, outOfService, polarity);
	  
	  */
	  
	  
	  // End of Test
	  
	  //addBacnetObject(,);
	 
	 // LocalDevice device =server.getLocalDevice();
	  
	  //bacnetObjectList.add(new AnalogInputObject(device, 0, "inputTest0", 10, EngineeringUnits.noUnits, java.lang.Boolean.TRUE) );
		
		
	 // Javalin app = Javalin.create().start(7009);
	  Javalin app = Javalin.create(config -> {
          // Enable CORS for all routes
          config.enableCorsForAllOrigins();
      });
	  
	  app.start(7007);

	  
      // Define a route with a filter
      app.before(ctx -> {
          // Extract the token from the query parameter
      
           
      });

      app.get("/" , ctx ->{
    	  
      });
      
      
      app.post("/addDevice",ctx->{
   		 String jsonPayload = ctx.body();
  		 JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
  		 
         Gson gson = new Gson();
         
  	    // Convert the JSON string to a Java object
         Device deviceObject = gson.fromJson(jsonObject, Device.class);
         System.out.println(jsonPayload);
         
         DeviceDAO deviceDAO = new DeviceDAO();
         deviceDAO.addDevice(deviceObject);
         
	
	  });
      
      app.post("/addDeviceTemplate",ctx->{
    	String jsonPayload = ctx.body();
 		ApiController apiController = new ApiController(); 
 		apiController.addTemplate(jsonPayload); 	
 	  });
      
      app.get("/listAllDeviceTemplate", ctx->{
         ApiController apiController = new ApiController();
         ctx.result(apiController.templateDeviceJsonString());	  
	   });
      
      app.get("/listAllTemplateDevices", ctx->{
;
    	  //InitHash.initDeviceBacnetBroadcastMap();
    	  
          ApiController apiController = new ApiController();
          ctx.result(apiController.templateDeviceBacNetList());
      });
      
      app.post("/deleteTemplates",ctx->{
      	String jsonPayload = ctx.body();
   		ApiController apiController = new ApiController(); 
   		apiController.deleteTemplates(jsonPayload); 
  	    InitHash.initTempBac();
        InitHash.initDeviceTempID();
  	    InitHash.initDeviceBacnetBroadcastMap();
  	
   	  });
      
      

      
      app.post("/device/{deviceName}", ctx->{
    	  
    	  InitHash.initDeviceTempID();
    	  InitHash.initTempBac();   
    	  //BacnetBroadcastOperations.initBroadcastList();
    	  
    	  
    	  
    	  String deviceName=ctx.pathParam("deviceName");
    	  String jsonPayload = ctx.body();
    	  System.out.println(jsonPayload);
    	  
    	  System.out.println(Cache.deviceTemplateIDMap.keySet());
    	  System.out.println( "Contains key "+Cache.deviceTemplateIDMap.containsKey(deviceName));
    	  
    	  
    	  System.out.println(deviceName+" "+bbm.isDeviceLoaded(deviceName));
    	  
    	  if(bbm.isDeviceLoaded(deviceName)) {
    		 bbm.updateBacnetObject(jsonPayload, deviceName);
    		 System.out.println("Update");
    	  }
    	  else {
    		  if(BacnetBroadcastMapOperations.containsDevice(deviceName)) {
    			 System.out.println("Load Device "); 
    			 bbm.loadBacnetObject(jsonPayload,deviceName);  
    		  }else {
    		     bbm.addBacnetObject(jsonPayload, deviceName);
    			 System.out.println("Add Device "); 

    		  }
    	  }
      });
      
      
      app.get("/device/{deviceName}", ctx->{
    	  String deviceName=ctx.pathParam("deviceName");
    	  if( Cache.deviceJsonPayloadMap.containsKey(deviceName)) 
              ctx.result(Cache.deviceJsonPayloadMap.get(deviceName));
    	  else
              ctx.result("Device Doesn't Exist");

    	  
      });
      
      
     app.post("/findBacnetObjects" , ctx ->{
    	
    	  String jsonPayload = ctx.formParam("jsonPayload");

    	  JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
          ArrayList<String> arrayList = new ArrayList<>();


    	   Set<java.util.Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            for (java.util.Map.Entry<String, JsonElement> entry : entrySet) {
               System.out.println("Key: " + entry.getKey());
            	arrayList.add(entry.getKey());
            }
 	  
          // Send a response
          ctx.result("JSON Payload Received Successfully");

      });
              
          
    
       /* 
      app.post("/api", ctx -> {
          // Retrieve the JSON payload from the request
          String jsonPayload = ctx.body();

          // Process the JSON payload (you can deserialize it into an object if needed)
          System.out.println("Received JSON Payload: " + jsonPayload);
          JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
          
          int temperature = jsonObject.get("temperature").getAsInt();

          
          System.out.println("Temperatue is "+temperature);
          bacnetObjectList.get(0).writePropertyInternal(PropertyIdentifier.presentValue, new Real(temperature));
          
          
          // Send a response
          ctx.result("JSON Payload Received Successfully");
      });
      */
      
      
      
      app.get("/api", ctx -> {
           ctx.result("JSON Payload Received Successfully");
      });
      
      
      
      
      
		
	}
	
	
	 
	 
	   
	   
	   
	   
	  

}
