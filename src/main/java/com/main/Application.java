package com.main;

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


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


import com.authentication.ApiTokenOperations;
import com.authentication.UserOperations;
import com.controller.ApiController;
import com.dao.DeviceDAO;
import com.dao.SQLiteDB;
import com.diagonistics.Diagonstics;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.libraries.DateTimeAPI;
import com.memory.BacnetBroadcastMapOperations;
import com.memory.BroadcastBacnetMap;
import com.memory.Cache;
import com.memory.InitHash;
import com.pojo.Device;
import com.serotonin.bacnet4j.obj.BACnetObject;


import io.javalin.Javalin;
import server.BacnetServerObject;
import server.InitBacnetServerConfig;


public class Application {
	
	
	List<BACnetObject> bacNetList;
	BacnetServerObject server=BacnetServerObject.getInstance();
	   
	   
	public Application() {
		
		 //Create bacNetList
		 bacNetList= new CopyOnWriteArrayList<>();
		 
		 //Initialize
		 InitHash.initDeviceTempID();
		 InitHash.initTempBac();
	}   


	public static void main(String[] args) throws Exception {
	  
	  //Get the sqlLite url from args[0]
	  System.out.println(args[0]);
	  SQLiteDB.url=args[0];

	  //Initialize Device Temp
	  InitHash.initDeviceTempID();
	  InitHash.initTempBac();
	  InitHash.initDeviceBacnetBroadcastMap();
	  
	  InitBacnetServerConfig.initBacnetVariables();
	  
	  
	  /*************************************************************************************************
	   * If no username or password in the database then set it to default password and username
	   *
	   * Default username and password
	   * username:admin
	   * password:admin
	   *
	   *************************************************************************************************/
	  UserOperations.factoryReset();
	  
	  
	  //If no token then generate a random token
	  ApiTokenOperations.initToken();
	   
	  //Instantiate bacnetServer and start listening on port 4087
	  BacnetServerObject  server = BacnetServerObject.getInstance();
	  server.eventListener();
	  server.start();
	  
	  
	  
	  //BroadcastBacnet broadcasts and stores the bacnet  objects in memory 
	  BroadcastBacnetMap bbm = BroadcastBacnetMap.getInstance();
	
	  

		
	 // Javalin app = Javalin.create().start(7009);
	  Javalin app = Javalin.create(config -> {
          // Enable CORS for all routes
          config.enableCorsForAllOrigins();
      });
	  
	  //application start on port 7007 
	  app.start(7007);
	  
	 

	  
      // Define a route with a filter
      app.before(ctx -> {
          // Extract the token from the query parameter
      
           
      });

      
      //Empty Route and Main route
      app.get("/" , ctx ->{
    	  
      });
      
      //Route adds iot device to the database
      app.post("/addDevice",ctx->{
    	 
   		 String jsonPayload = ctx.body();
   		 
   		 //Parses JSON  Device Object 
  		 JsonObject jsonObject= JsonParser.parseString(jsonPayload).getAsJsonObject();
  		 
         Gson gson = new Gson();
         
  	    // Convert the JSON string to a Java object
         Device deviceObject = gson.fromJson(jsonObject, Device.class);
         System.out.println(jsonPayload);
         
         //Access DAO object and adds it to the database
         DeviceDAO deviceDAO = new DeviceDAO();
         deviceDAO.addDevice(deviceObject);
         
	
	  });
      
      //Route adds Template  payload to database
      app.post("/addDeviceTemplate",ctx->{
    	String jsonPayload = ctx.body();
 		ApiController apiController = new ApiController(); 
 		apiController.addTemplate(jsonPayload); 	
 	  });
      
      
      //Route Returns a list of all templates and devices
      app.get("/listAllDeviceTemplate", ctx->{
         ApiController apiController = new ApiController();
         ctx.result(apiController.templateDeviceJsonString());	  
	   });
      
      //Route returns a list of all template devices
      app.get("/listAllTemplateDevices", ctx->{

    	  //InitHash.initDeviceBacnetBroadcastMap();
    	  
          ApiController apiController = new ApiController();
          ctx.result(apiController.templateDeviceBacNetList());
      });
      
      //Routes Deletes templates from database
      app.post("/deleteTemplates",ctx->{
      	String jsonPayload = ctx.body();
   		ApiController apiController = new ApiController(); 
   		apiController.deleteTemplates(jsonPayload);
   		
   		//Reload in-memory data
  	    InitHash.initTempBac();
        InitHash.initDeviceTempID();
  	    InitHash.initDeviceBacnetBroadcastMap();
  	
   	  });
      
      //Route returns a list of all devices
      app.get("/listAllDevices", ctx->{    	  
          ApiController apiController = new ApiController();
          ctx.result(apiController.deviceList());
      });
      
      
      //Route delete devices in the database
      app.post("/deleteDevices", ctx->{
    	  
    	  String jsonPayload = ctx.body();
          System.out.println(jsonPayload);
          ApiController apiController = new ApiController();
          apiController.deleteDevices(jsonPayload);
          
          //Reload the data in-memory
          InitHash.initTempBac();
          InitHash.initDeviceTempID();
    	  InitHash.initDeviceBacnetBroadcastMap();
    	
      });
      

      
      
     /*ROUTE. Updates or Create device broadcasting bacnet Object
       Turns the JSON payload data to BacNet Objects
       For Example 
        
       localhost:7000/device/jci1?token="sddfwerfwds"
       
       jci1 payload
       {
        "temp":32,
         "hum":36
       } 
       
       
       Creates a bacnet objects for elements in the payload 
     
     */
      app.post("/device/{deviceName}", ctx->{

    	  //Reloads data into memory
    	  InitHash.initDeviceTempID();
    	  InitHash.initTempBac();   
    	  
    	//Parses token  
    	String token = ctx.queryParam("token");
    	  
        //Check if token is valid 	
    	if(ApiTokenOperations.isTokenValid(token)) {
    	  
	    	      String deviceName=ctx.pathParam("deviceName").trim();
	    	  
	    	  	  String jsonPayload = ctx.body();
			    	 
			      //Validate JSON	  
			      if(bbm.validateJson(jsonPayload, deviceName)) {  
			    	  
			    	  System.out.println("Valid payload");
			    	  
			    	  //check is the is broadcasted
			    	  //Updates broadcasted device object
			    	  if(bbm.isDeviceLoaded(deviceName)) {
			    		 bbm.updateBacnetObject(jsonPayload, deviceName);
			    		 System.out.println("Update");
			    	  }
			    	  else {
			    		  //If device was previously broadcasted since the last restarts
			    		  //load it
			    		  if(BacnetBroadcastMapOperations.containsDevice(deviceName)) {
			    			 System.out.println("Load Device "); 
			    			 bbm.loadBacnetObject(jsonPayload,deviceName);  
			    		     }else {
			    		       //If device hasn't been broadcasted load it	 
			    		       bbm.addBacnetObject(jsonPayload, deviceName);
			    			   System.out.println("Add Device "); 
			    		     }
			    	  }
			    	
			    	//Cache device active 
			        Cache.activeApisMap.put(deviceName, DateTimeAPI.getCurrentTimeStamp());
		    	  }else {
		     		ctx.result("Invalid Payload");
 
		    	   }
    	
    	}
    	else {
    		
    		ctx.result("Invalid Token");
    	}
	      
	      
	      
    	       
      });
      
      
      /*ROUTE. Returns any updates changes that happened in the BAS or sensor. 
      
      
      localhost:7000/device/jci1?token="sddfwerfwds"
      
      returns the latest paylaod
      
       jci1 payload
       {
        "temp":32,
         "hum":68
       } 
       
      
      
      */
      app.get("/device/{deviceName}", ctx->{
    	  String deviceName=ctx.pathParam("deviceName");
          String token = ctx.queryParam("token").trim();
    	  
    	 if(ApiTokenOperations.isTokenValid(token)) {
    	    
	    	  if( Cache.deviceJsonPayloadMap.containsKey(deviceName)) {
	             ctx.result(Cache.deviceJsonPayloadMap.get(deviceName));
	             Cache.activeApisMap.put(deviceName, DateTimeAPI.getCurrentTimeStamp());
	    	  }    
	    	  else 
	    	  {
	             ctx.result("Device Doesn't Exist");
	    	  }
    	 }
    	 else {
    		 ctx.result("Invalid Token!");
    	 } 	  
    	  
      });
      
      
      //ROUTE Returns a list of all active APIS
      app.get("/listAllActiveApis" , ctx ->{
          ApiController apiController = new ApiController();
          ctx.result(apiController.listAllActiveApis());  
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
      
     //Route returns in json format the BacNet Format
     app.get("/getbacnetConfig", ctx -> {
         ApiController apiController = new ApiController();
         ctx.result( apiController.getBacnetConfig());
     });
     
     
     //Route updates the bacnetConfig and makes changes to database
     app.post("/updateBacnetConfig", ctx -> {
         String jsonPayload = ctx.body();
         System.out.println(jsonPayload);
   	     ApiController apiController = new ApiController();
   	     apiController.updateBacnetConfig(jsonPayload);
        // ctx.result( apiController.getBacnetConfig());
     });
     
     
      
      app.get("/api", ctx -> {
    	  //ctx.result(LoggerFactory.getILoggerFactory().getClass().toGenericString());
           ctx.result("JSON Payload Received Successfully");
      });
      
      //Route returns all bacnet objects broadcasting
      app.get("/bacnetbroadcastlist", ctx -> {
          ApiController apiController = new ApiController();
          ctx.result(apiController.bacnetBroadcastList());

      });
      
      
      //Route returns a list of cache objects that are in the cache database
      app.get("/cacheInstanceBacnetObjectList", ctx->{
          ApiController apiController = new ApiController();
          ctx.result(apiController.cacheInstanceBacnetObjectList()); 
      });
      
      
 
      
      //Deletes all cache bacnet objects
      app.post("/clearBacnetBroadcastCache", ctx -> {
    	  ApiController apiController = new ApiController();
    	  apiController.deletingAllBacnetBroadcastObjects();
          //bbm.clearBacnetBroadcast();
         
      });
      
      //Route Returns Digonstic information in JSON format
      app.get("/Diagonistic",ctx->{  
    	 ctx.result( Diagonstics.jsonDiagnosticsStats());   
      });
      
      //Route gets the API TOKENS
      app.get("/getApiToken",ctx->{  
     	 ctx.result(ApiTokenOperations.ApiToken);   
      });
      
      //Route generates a new API token
      app.post("/generateNewTApiToken",ctx->{  
    	  ApiTokenOperations.generateNewToken();
      });
      
      //Updates or Create User
      app.post("/userUpdateOrCreate",ctx->{
    	  ApiController apiController = new ApiController();
          String jsonPayload = ctx.body();
          System.out.println(jsonPayload);
          apiController.userUpdateOrCreate(jsonPayload);
	  });
      
      app.post("/authenticate",ctx->{
    	  String jsonPayload = ctx.body();
    	  ApiController apiController = new ApiController();
          if(apiController.isAuthValid(jsonPayload)!=null) {
        	  ctx.result(apiController.isAuthValid(jsonPayload)); 
          }
          else 
          {
              ctx.status(404).result("Not Valid");  
          }
    		  
  	  });
      
      
      
      

      
      
	
	}
	
	
	 
	   
	   
	   
	   
	  

}
