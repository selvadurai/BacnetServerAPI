package com.memory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pojo.BroadcastBacnet;
import com.pojo.TemplateBacnetListObject;

public interface Cache {
	
   public ConcurrentHashMap<Integer, TemplateBacnetListObject> templateBacMap = new ConcurrentHashMap<>();
   public ConcurrentHashMap<String, Integer> deviceTemplateIDMap = new ConcurrentHashMap<>();
   
  // public List<BroadcastBacnet> broadcastlist =  new CopyOnWriteArrayList<>();
   public ConcurrentHashMap<Integer, BroadcastBacnet> deviceBacnetBroadcastMap = new ConcurrentHashMap<>();
   
   //map<DeviceName,Payload> 
   public ConcurrentHashMap<String,String> deviceJsonPayloadMap = new ConcurrentHashMap<>();

  
   //Active API
   public ConcurrentHashMap<String, String> activeApisMap = new ConcurrentHashMap<>();

   


}
