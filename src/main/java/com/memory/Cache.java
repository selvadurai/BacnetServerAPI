package com.memory;

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



import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pojo.BroadcastBacnet;
import com.pojo.TemplateBacnetListObject;

public interface Cache {
	
   
   /*
   * templateBacMap 
   * 
   * Stores Device Template ID and TemplateBacnetListObject 
   * 
   * class contains TemplateBacnetListObject
   *   DeviceTemplate deviceTemplate
   *   BacnetObjectList bacnetObjectList[]
   * 
   * 
   * Example
   * 
   * <1,[deviceTemplate[id,jciTemplate],bacnetObjectList[anlogInput:humidity,analogInput:temp]>
   * 	
   */
   public ConcurrentHashMap<Integer, TemplateBacnetListObject> templateBacMap = new ConcurrentHashMap<>();
   
   
   /*
     stores <deviceName,device_template_id>
     
     example:
     <"JCI1",1>
     1 is the JCI device template ID
   
   */
   public ConcurrentHashMap<String, Integer> deviceTemplateIDMap = new ConcurrentHashMap<>();
   
   
   
   /*
   *  Stores <instanceNumber,BroadcastBacnet Object>
   *  
   *  Class BroadcastBacnet:
   *      int instanceNum;
   *      String objectName;
   *      String objectType;
   *      String deviceName;
   *      String keyName;
   *      
   *      
   *  Example:
   *  
   *      <11,[11,jci1temp,anlogInput,jci1,temp]>
   */
   
   public ConcurrentHashMap<Integer, BroadcastBacnet> deviceBacnetBroadcastMap = new ConcurrentHashMap<>();
      
   
   /*
    * map<DeviceName,Payload> 
    * 
    * deviceJsonPayloadMap is used to cache the device payload, and it updated everytime the payload for the device
    * is changed. 
    *
    * deviceJsonPayloadMap stores the payload in POST and retrieves in GET 
    *
    * Example 
    * 
    * ["jci1","{temp:21,hum:19}"]
    *
    */
   public ConcurrentHashMap<String,String> deviceJsonPayloadMap = new ConcurrentHashMap<>();

  
   /*
    * activeApisMap<deviceName,timeStamp>
    * Used to track the device latest active time. 
    * Example:
    * [JCI,March 28,2024,11:08:08]
    * 
    */
   public ConcurrentHashMap<String, String> activeApisMap = new ConcurrentHashMap<>();

   


}
