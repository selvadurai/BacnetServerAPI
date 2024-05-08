package com.dao;

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
