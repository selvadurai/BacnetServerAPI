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
 * Date: May 7 2024
 */



package com.memory;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dao.BroadcastBacnetDAO;
import com.pojo.BroadcastBacnet;

public class BacnetBroadcastMapOperations {
	
    //Maximum Bacnet Range 
	final static int maxBacNetRange=4194302;	

	
	//Checks if device in BacnetBroadcastMap
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
	
	//Generate Random Bacnet Instance Number
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
	
	
	//Returns List of all Bacnet Objects in Map
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
