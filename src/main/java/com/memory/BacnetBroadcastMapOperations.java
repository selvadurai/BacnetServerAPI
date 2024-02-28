package com.memory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dao.BroadcastBacnetDAO;
import com.pojo.BroadcastBacnet;

public class BacnetBroadcastMapOperations {
	
	final static int maxBacNetRange=4194302;	

	
	
	public static Boolean containsDevice(String deviceName) {
		            
        Iterator<Map.Entry<Integer, BroadcastBacnet>> iterator = Cache.deviceBacnetBroadcastMap.entrySet().iterator();
        
        while (iterator.hasNext()) {
            Map.Entry<Integer, BroadcastBacnet> entry = iterator.next();
            BroadcastBacnet bacnetBroadcastObject = entry.getValue();
            String broadcastDeviceName=bacnetBroadcastObject.getDeviceName();
            
            if(broadcastDeviceName.contains(deviceName)) {
            	return true;
            }
            
        }

		
		return false;
	}
	
	public static int generateBacnetInstanceNum() {
		   boolean instanceExist=true; 
		   while(instanceExist) {
		     int instanceNum = (int)(Math.random() * maxBacNetRange);
		     if(Cache.deviceBacnetBroadcastMap.containsKey(instanceNum)==false) { 
		    	 System.out.println("Check For broadcasted Numbers "+instanceNum);
		    	 instanceExist=true;	 
		         return instanceNum; 
		     }    
		   }
		   
		   return -1;
	}
	
	
	
     public static List<BroadcastBacnet>  getAllDeviceBroadcastList(String deviceName) {
		
    	 List<BroadcastBacnet> aux = new CopyOnWriteArrayList<>();
         Iterator<Map.Entry<Integer, BroadcastBacnet>> iterator = Cache.deviceBacnetBroadcastMap.entrySet().iterator();
         
         while (iterator.hasNext()) {
             Map.Entry<Integer, BroadcastBacnet> entry = iterator.next();
             BroadcastBacnet bacnetBroadcastObject = entry.getValue();
             String broadcastDeviceName=bacnetBroadcastObject.getDeviceName();
             System.out.println(broadcastDeviceName+"   "+bacnetBroadcastObject.getObjectName()+" "+entry.getKey());
             if(broadcastDeviceName.equals(deviceName)) {
            	 aux.add(bacnetBroadcastObject);
             }
             
         }

		
		
		return aux;
	}
	
	

}
